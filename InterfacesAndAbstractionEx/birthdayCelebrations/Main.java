package birthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        List<Birthable> birthableList=new ArrayList<>();
        String command=scanner.nextLine();

        while (!command.equals("End")){
            String [] data=command.split("\\s+");
            Birthable birthable;
            if(data.length==5){
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String id = data[3];
                String birthday = data[4];
                birthable=new Citizen(name, age, id, birthday);
                birthableList.add(birthable);
            }else if(data.length==3){
                if(data[0].equals("Pet")){
                    birthable=new Pet(data[1],data[2]);
                    birthableList.add(birthable);
                }
            }
            command=scanner.nextLine();
        }
        String year=scanner.nextLine();

            birthableList.stream()
                    .map(Birthable::getBirthDate)
                    .filter(e -> e.endsWith(year))
                    .forEach(System.out::println);

//        for (Birthable birthable : birthableList) {
//            if(birthable.getBirthDate().endsWith(year)){
//                System.out.println(birthable.getBirthDate());
//            }
//        }
    }
}

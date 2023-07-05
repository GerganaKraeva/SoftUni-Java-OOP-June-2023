package birthdayCelebrations;

import multipleImplementation.Person;

import java.util.ArrayList;
import java.util.Collections;
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
                    birthable=new Pets(data[1],data[2]);
                    birthableList.add(birthable);
                }
            }
            command=scanner.nextLine();
        }
        String year=scanner.nextLine();
        List<String>result=new ArrayList<>();
        if(!birthableList.isEmpty()) {
            birthableList.stream()
                    .map(Birthable::getBirthDate)
                    .forEach(e -> {
                        String[] data = e.split("\\/");
                        if (Integer.parseInt(data[2]) == Integer.parseInt(year)) {
                            result.add(e);
                        }
                    });
        }
        if(result.isEmpty()){
            System.out.println("<no output>");
        }else{
            System.out.println(String.join(System.lineSeparator(),result));
        }
    }
}

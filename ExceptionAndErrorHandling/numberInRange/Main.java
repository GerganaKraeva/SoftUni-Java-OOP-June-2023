package numberInRange;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String range=scanner.nextLine();
        int minNumber=Integer.parseInt(range.split(" ")[0]);
        int maxNumber=Integer.parseInt(range.split(" ")[1]);
        System.out.printf("Range: [%d...%d]\n",minNumber,maxNumber);

        while (true){
            String userInput=scanner.nextLine();
            try {
                int number = Integer.parseInt(userInput);
                if(number>=minNumber && number<=maxNumber){
                    System.out.println("Valid number: "+number);
                    break;
                }else{
                    throw new IllegalArgumentException("The number is out of range.");
                }
            }catch (  IllegalArgumentException e ){
                System.out.printf("Invalid number: %s\n",userInput);
            }
        }
    }
}

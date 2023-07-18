package squareRoot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String userInput=scanner.nextLine();

        try{
            int number=Integer.parseInt(userInput);
            if (number<0){
                throw  new ArithmeticException("SQRT is invalid for negative number");
            }else{
                System.out.printf("%.2f\n",Math.sqrt(number));
            }
        }catch ( ArithmeticException | NumberFormatException ex){
            System.out.println("Invalid");
        }finally {
            System.out.println("Goodbye");
        }
    }
}

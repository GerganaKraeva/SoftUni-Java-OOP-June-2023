package cardRank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        CardRanks [] ranks=CardRanks.values();
        String input=scanner.nextLine();
        System.out.println(input+":");
        for (CardRanks rank : ranks) {
            System.out.printf("Ordinal value: %d; Name value: %s\n",rank.ordinal(),rank.name());
        }
    }
}

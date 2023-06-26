package cardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rankString = scanner.nextLine();
        String suitString = scanner.nextLine();
        RankPowers rank = RankPowers.valueOf(rankString);
        SuitPowers suit = SuitPowers.valueOf(suitString);

        Cards cards = new Cards(rank, suit);
        System.out.printf("Card name: %s of %s; Card power: %d\n", rankString, suitString, cards.calculate());
    }


}

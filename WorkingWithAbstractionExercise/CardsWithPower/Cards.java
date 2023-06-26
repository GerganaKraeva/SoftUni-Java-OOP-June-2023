package cardsWithPower;

public class Cards {
    RankPowers rankPowers;
    SuitPowers suitPowers;

    public Cards(RankPowers rankPowers, SuitPowers suitPowers) {
        this.rankPowers = rankPowers;
        this.suitPowers = suitPowers;
    }
    public int calculate(){
        return this.rankPowers.getPower()+this.suitPowers.getPowerOfSuits();
    }
 

   
}

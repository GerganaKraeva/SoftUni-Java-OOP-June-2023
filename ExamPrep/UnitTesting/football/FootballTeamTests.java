package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootballTeamTests {
    private Footballer footballer;
    private FootballTeam footballTeam;
    private static final String NEW_FOOTBALLER = "Gosho";
    private static final int VACANT_POSITION = 1;
    private static final String NAME_TEAM = "Pirin";

    @Before
    public void prepare() {
        this.footballer = new Footballer(NEW_FOOTBALLER);
        this.footballTeam = new FootballTeam(NAME_TEAM, VACANT_POSITION);
    }

    @Test
    public void testFootballTeamCreatesCorrectTeam() {
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(VACANT_POSITION, footballTeam.getVacantPositions());
        Assert.assertEquals(NEW_FOOTBALLER, footballer.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testCreatingTeamWithNullNameShouldFail() {
        FootballTeam footballTeam1 = new FootballTeam(null, 1);
    }
    @Test(expected = NullPointerException.class)
    public void testCreatingTeamWithEmptyNameShouldFail() {
        FootballTeam footballTeam1 = new FootballTeam(" ", 1);
    }
    @Test
    public void testCreatingTeamWithName() {
        FootballTeam footballTeam1 = new FootballTeam("Vihren", 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetVacantPositionsIfNegative() throws IllegalArgumentException {
        int vacantPosition = -1;
        FootballTeam footballTeam1 = new FootballTeam(NAME_TEAM, vacantPosition);
    }

    @Test
    public void testGetVacantPositionsIfZeroOrPositive() {
        int vacantPosition = 0;
        FootballTeam footballTeam1 = new FootballTeam(NAME_TEAM, vacantPosition);
        vacantPosition = 20;
        FootballTeam footballTeam2 = new FootballTeam(NAME_TEAM, vacantPosition);
    }

    @Test
    public void testAddFootballerShouldIncreaseTeamMembersCount() {
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(1,footballTeam.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddFootballerShouldFailWhenTeamIsFull() {
        footballTeam.addFootballer(footballer);
        Footballer footballer1=new Footballer("Pepi");
        footballTeam.addFootballer(footballer1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrowsExceptionIfNameNull()  {
        footballTeam.removeFootballer(null);
    }

    @Test
    public void testRemoveFootballerIfIsInTeam() {
        footballTeam.addFootballer(footballer);
        footballTeam.removeFootballer(NEW_FOOTBALLER);
        Assert.assertEquals(0,footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFootballerShouldFailWhenNoSuchPlayer() {
        footballTeam.footballerForSale("abv");
    }

    @Test
    public void testFootballerForSaleShouldDeactivatePlayer() {
        footballTeam.addFootballer(footballer);
        footballTeam.footballerForSale(NEW_FOOTBALLER);
        Assert.assertFalse(footballer.isActive());
    }

}

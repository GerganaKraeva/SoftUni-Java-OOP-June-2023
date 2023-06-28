package footballTeamGenerator;

public class Player {
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        this.setName(name);
        this.setEndurance(endurance);
        this.setSprint(sprint);
        this.setDribble(dribble);
        this.setPassing(passing);
        this.setShooting(shooting);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    private void setEndurance(int endurance) {
       validateStat(endurance, "Endurance");
            this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        validateStat(sprint, "Sprint");
            this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        validateStat(dribble, "Dribble");
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        validateStat(passing, "Passing");
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        validateStat(shooting, "Shooting");
        this.shooting = shooting;
    }

    public double overallSkillLevel() {
        return (this.endurance + this.dribble + this.passing + this.sprint + this.shooting) / 5.0;
    }

    public void validateStat(int statValue, String statName) {
        if (statValue > 100 || statValue < 0) {
            String message = String.format("%s should be between 0 and 100.", statName);
            throw new IllegalArgumentException(message);
        }
    }
}

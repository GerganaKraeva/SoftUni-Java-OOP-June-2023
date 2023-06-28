package footballTeamGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Team> teams = new LinkedHashMap<>();
        String command = scanner.nextLine();



        while (!"END".equals(command)) {
            String[] line = command.split(";");
            String newCommand = line[0];
            String nameOfTeam = line[1];
            try {
                switch (newCommand) {
                    case "Team":
                        teams.putIfAbsent(nameOfTeam, new Team(nameOfTeam));
                        break;
                    case "Add":
                        if (teams.containsKey(nameOfTeam)) {
                            String playerName = line[2];
                            int endurance = Integer.parseInt(line[3]);
                            int sprint = Integer.parseInt(line[4]);
                            int dribble = Integer.parseInt(line[5]);
                            int passing = Integer.parseInt(line[6]);
                            int shooting = Integer.parseInt(line[7]);
                            Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                            teams.get(nameOfTeam).addPlayer(player);
                        } else {
                            System.out.printf("Team %s does not exist.\n", nameOfTeam);
                        }
                        break;
                    case "Remove":
                        if (teams.containsKey(nameOfTeam)) {
                            String nameOfPlayer = line[2];
                            teams.get(nameOfTeam).removePlayer(nameOfPlayer);
                        } else {
                            System.out.printf("Team %s does not exist.\n", nameOfTeam);
                        }
                        break;
                    case "Rating":
                        if (teams.containsKey(nameOfTeam)) {
                            System.out.printf("%s - %d\n", nameOfTeam, Math.round(teams.get(nameOfTeam).getRating()));
                        } else {
                            System.out.printf("Team %s does not exist.\n", nameOfTeam);
                        }
                        break;
                }
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }

            command = scanner.nextLine();
        }

    }
}

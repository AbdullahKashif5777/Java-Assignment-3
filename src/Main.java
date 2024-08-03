import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: User Input
        System.out.print("Enter the name of Team 1: ");
        String team1Name = scanner.nextLine();
        System.out.print("Enter the name of Team 2: ");
        String team2Name = scanner.nextLine();

        // Create players for both teams
        Player[] team1Players = new Player[11];
        Player[] team2Players = new Player[11];
        for (int i = 0; i < 11; i++) {
            team1Players[i] = new Player("Player" + (i + 1) + "_T1");
            team2Players[i] = new Player("Player" + (i + 1) + "_T2");
        }

        // Create team objects
        Team team1 = new Team(team1Name, team1Players);
        Team team2 = new Team(team2Name, team2Players);

        // Set the number of overs
        final int overs = 20;

        // Create a cricket match and simulate it
        Match match = new Match(team1, team2, overs);
        match.simulateToss();
        match.simulateInnings(match.firstInningsTeam, null);
        match.displayScorecard(match.firstInningsTeam);
        match.simulateInnings(match.secondInningsTeam, match.firstInningsTeam);
        match.displayScorecard(match.secondInningsTeam);
        match.determineWinner();
    }
}
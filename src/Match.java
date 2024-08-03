import java.util.Random;


class Match {
    Team team1;
    Team team2;
    Team firstInningsTeam;
    Team secondInningsTeam;
    int overs;
    Random random = new Random();
    boolean chasingTeamWon = false;

    public Match(Team team1, Team team2, int overs) {
        this.team1 = team1;
        this.team2 = team2;
        this.overs = overs;
    }

    public void simulateToss() {
        if (random.nextBoolean()) {
            firstInningsTeam = team1;
            secondInningsTeam = team2;
        } else {
            firstInningsTeam = team2;
            secondInningsTeam = team1;
        }
        System.out.println(firstInningsTeam.name + " won the toss and elected to bat first.");
    }

    public void simulateInnings(Team battingTeam, Team bowlingTeam) {
        int totalBalls = overs * 6;
        for (int i = 0; i < totalBalls && battingTeam.wickets < 10; i++) {
            Player currentPlayer = battingTeam.players[battingTeam.wickets];
            int run = random.nextInt(8); // Random runs between 0 and 7
            if (run == 5) run = 1; // Convert 5 to 1 (rare case)
            currentPlayer.ballsFaced++;
            battingTeam.ballsFaced++;
            if (run == 7) {
                battingTeam.wickets++;
                currentPlayer.isOut = true;
                System.out.println(currentPlayer.name + " is OUT.");
            } else {
                currentPlayer.runs += run;
                battingTeam.totalRuns += run;
            }

            // Stop innings if chasing team reaches the target
            if (bowlingTeam != null && battingTeam.totalRuns > bowlingTeam.totalRuns) {
                chasingTeamWon = true;
                System.out.println(battingTeam.name + " has chased the target successfully.");
                break;
            }
        }
    }

    public void displayScorecard(Team team) {
        System.out.println("\nScorecard for " + team.name + ":");
        System.out.println("Player\t\tRuns\tBalls\tStrike Rate\tStatus");

        for (Player player : team.players) {
            String status = player.isOut ? "Out" : "Not Out";
            System.out.printf("%s\t%d\t%d\t%.2f\t%s\n", player.name, player.runs, player.ballsFaced, player.getStrikeRate(), status);
        }

        int oversBowled = team.ballsFaced / 6;
        int remainingBalls = team.ballsFaced % 6;
        System.out.printf("Total score: %d/%d in %d.%d overs\n", team.totalRuns, team.wickets, oversBowled, remainingBalls);
    }

    public void determineWinner() {
        if (chasingTeamWon) {
            int wicketsLeft = 10 - secondInningsTeam.wickets;
            System.out.println(secondInningsTeam.name + " wins by " + wicketsLeft + " wickets.");
        } else {
            if (firstInningsTeam.totalRuns > secondInningsTeam.totalRuns) {
                System.out.println(firstInningsTeam.name + " wins by " + (firstInningsTeam.totalRuns - secondInningsTeam.totalRuns) + " runs.");
            } else if (secondInningsTeam.totalRuns > firstInningsTeam.totalRuns) {
                System.out.println(secondInningsTeam.name + " wins by " + (secondInningsTeam.totalRuns - firstInningsTeam.totalRuns) + " runs.");
            } else {
                System.out.println("The match is a tie.");
            }
        }
    }
}
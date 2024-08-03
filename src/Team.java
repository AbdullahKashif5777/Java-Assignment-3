class Team {
    String name;
    Player[] players;
    int totalRuns;
    int wickets;
    int ballsFaced;

    public Team(String name, Player[] players) {
        this.name = name;
        this.players = players;
        this.totalRuns = 0;
        this.wickets = 0;
        this.ballsFaced = 0;
    }
}
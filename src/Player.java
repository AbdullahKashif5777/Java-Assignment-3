class Player {
    String name;
    int runs;
    int ballsFaced;
    boolean isOut;

    Player(String name) {
        this.name = name;
        this.runs = 0;
        this.ballsFaced = 0;
        this.isOut = false;
    }

    double getStrikeRate() {
        return (ballsFaced == 0) ? 0 : (runs * 100.0) / ballsFaced;
    }
}
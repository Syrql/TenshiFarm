package fr.syrql.farming.data.profile;

public class ProfileData {

    private String playerName;
    private double points;

    public ProfileData(String playerName, double points) {
        this.playerName = playerName;
        this.points = points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}

package sample;

public class Player {
    private String playerName;
    private Team teamName;



    public Player(String playerName, Team teamName){
        this.playerName = playerName;
        this.teamName = teamName;
    }

    public String getPlayerName() { return this.playerName;}

    public void setPlayerName(String playerName){this.playerName = playerName; }

    public Team getTeamName(){ return this.teamName;}

    public void setTeamName(Team team){  this.teamName = teamName; }

    @Override
    public String toString() {  return getPlayerName(); }

}

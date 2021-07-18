package sample;

import java.util.ArrayList;

public class Team {

    String teamName ;
    ArrayList<Team> teamList  = new ArrayList();
    ArrayList<String> playerList = new ArrayList<String>();
//    private TeamStats teamStats = new TeamStats (this);




    Team(String teamName){this.teamName = teamName; }

    public  String getTeamName (){return teamName;}

    public void setTeamName(String teamname){
        this.teamName = teamname;
    }

    public void addPlayer (String playerName){
        playerList.add(playerName);
    }

    public void removePlayer (String playerName){
        playerList.remove(playerName);
    }

    public ArrayList<String> getPlayers(Team team){
        return team.playerList;
    }

    @Override
    public String toString() {
        return getTeamName();
    }

}

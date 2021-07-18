package sample;

import java.util.ArrayList;

public class League {

    private static League instance = new League();
//    private Match  fixtures  = new Match ();
    private ArrayList<Team> teamList = new ArrayList<>();
    private ArrayList<Match> fixtureList = new ArrayList<>();

    private League (){};

    private static League getInstance(){return instance;}

    public boolean createTeam(String teamName){

        boolean duplicateFound = false;
        for (Team t : teamList){
            if (t.getTeamName() == teamName){
                duplicateFound = true;
                break;
            }
        }
        if (!duplicateFound){
            Team Team = new Team(teamName);
            teamList.add(Team);
            return true;
        }
        return false;
    }

    public void removeTeam(Team Team){teamList.remove(Team); }

    public ArrayList getAllTeams (){ return teamList;  }

    public void addTeamPlayer(Team Team, String playerName){
        Team.playerList.add(playerName);
    }

    public void removeTeamPlayer(Team Team, String playerName){
        Team.playerList.remove(playerName);
    }

    public ArrayList getAllTeamMembers(Team Team){
        return Team.playerList;
    }



}

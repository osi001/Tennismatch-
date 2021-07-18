package sample;

import java.util.List;
import static java.lang.Thread.sleep;

public class MatchThread implements Runnable{
    List<Team> teamList;
    List<Match> matchList;

    final int delay = 100000;

    public MatchThread (List<Match> matchList, List<Team> teamList){
        this.teamList = teamList;
        this.matchList = matchList;
    }

    public int matchesPlayed(Team teamname) {
        int matches = 0;
        for(Match x: matchList){
            if(teamname.equals(x.getTeamA()) || teamname.equals(x.getTeamB())){
                matches += 1;
            }
        }
        return matches;

    }

    public int matchesWon(Team teamname){
        int matches = 0;
        for(Match x: matchList){
            if(teamname.equals(x.getTeamA())){
                matches+= x.isMatchWinHome(teamname);
            }
            if(teamname.equals(x.getTeamB())){
                matches+= x.isMatchWinAway(teamname);
            }
        }
        return matches;
    }

    public int setsWon(Team teamname){
        int sets = 0;
        for(Match x: matchList){
            if(teamname.equals(x.getTeamA())){
                sets += x.setWonHome(teamname);
            }
            if(teamname.equals(x.getTeamB())){
                sets+= x.setWonAway(teamname);
            }
        }
        return sets;
    }



    @Override
    public void run(){
        try{
            while(true){
                for(int i = 0; i < teamList.size(); i++){
                    matchesPlayed(teamList.get(i));
                    setsWon(teamList.get(i));
                    matchesWon(teamList.get(i));
                }
                System.out.println("Generated statistics");
                sleep(delay);
            }
        }
        catch(InterruptedException e){
            return;
        }
    }
}

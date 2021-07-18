package sample;

public class Score {

    private int homeTeamScore;
    private int awayTeamScore;

    public Score (int homeTeamScore, int awayTeamScore){
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public void setHomeTeamScore(int score){ homeTeamScore = score;}

    public void setAwayTeamScore(int score){ awayTeamScore = score;}

    public int getHomeTeamScore (){return homeTeamScore;}

    public int getAwayTeamScore(){return awayTeamScore;}

    public String getTeamWithBestScore (){
        if(homeTeamScore>awayTeamScore){
            return "Home" ;
        }
        else {
            return "away";
        }
    }

}

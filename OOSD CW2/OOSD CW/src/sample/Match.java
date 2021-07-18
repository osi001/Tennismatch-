package sample;

import static java.lang.Thread.sleep;

public class Match {
    int HomeCount;
    int AwayCount;
    Team teamNameA;
    Team teamNameB;
    Set set1;
    Set set2;
    Set set3;
    Set set4;
    DSet doubleSet;

    public Match(Team teamNameA, Team teamNameB, Set set1, Set set2, Set set3, Set set4, DSet doubleSet) {
        this.teamNameA = teamNameA;
        this.teamNameB = teamNameB;
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.doubleSet = doubleSet;
    }

    public Team getTeamA() { return this.teamNameA;}

    public void setTeamNameA(Team teamNameA){ this.teamNameA = teamNameA; }


    public Team getTeamB() { return this.teamNameB; }

    public void setTeamNameB(Team teamNameB){  this.teamNameB = teamNameB;}

    public Set getSet1() { return this.set1;}

    public void setSet1(Set set1) { this.set1 = set1;}

    public Set getSet2() {return this.set2; }

    public void setSet2(Set set2) { this.set2 = set2;}


    public Set getSet3() { return this.set3;}


    public void setSet3(Set set3) { this.set2 = set3;}

    public Set getSet4() {return this.set4;}


    public void setSet4(Set set4) { this.set2 = set4; }

    public DSet getDouble() { return this.doubleSet;}


    public void setDoubleSet(DSet doubleSet) { this.doubleSet = doubleSet; }



    public String matchScoreStr(){
        int home = 0;
        int away = 0;
        if(set1.calculateHomeScore() > set1.calculateAwayScore()){home += 1; }
        else{ away += 1;}

        if(set2.calculateHomeScore() > set2.calculateAwayScore()){home += 1;}
        else{  away += 1;}

        if(set3.calculateHomeScore() > set3.calculateAwayScore()){  home += 1;}
        else{ away += 1;}

        if(set4.calculateHomeScore() > set4.calculateAwayScore()){ home += 1;}
        else{ away += 1;}

        if(doubleSet.calculateHomeScore() > doubleSet.calculateAwayScore()){home += 1;}
        else{ away += 1;}


        String Str = home + ":" + away;
        HomeCount = home;
        AwayCount = away;
        return  Str;
    }

    public int isMatchWinHome(Team teamNameA){
        matchScoreStr();
        if (HomeCount > AwayCount){ return 1; }
        else{  return 0;}

    }

    public int isMatchWinAway(Team teamNameB){
        matchScoreStr();
        if (HomeCount < AwayCount){ return 1;}
        else{ return 0; }
    }

    public int setWonHome(Team teamNameA){
        int winCount = 0;
        if(set1.calculateHomeScore() > set1.calculateAwayScore()){  winCount += 1;}
        if(set2.calculateHomeScore() > set2.calculateAwayScore()){winCount += 1;}
        if(set3.calculateHomeScore() > set3.calculateAwayScore()){ winCount += 1; }
        if(set4.calculateHomeScore() > set4.calculateAwayScore()){  winCount += 1;}
        if(doubleSet.calculateHomeScore() > doubleSet.calculateAwayScore()){ winCount += 1;}
        return winCount;
    }

    public int setWonAway(Team teamNameB){
        int winCount = 0;
        if(set1.calculateHomeScore() < set1.calculateAwayScore()){ winCount += 1;}
        if(set2.calculateHomeScore() < set2.calculateAwayScore()){ winCount += 1;}
        if(set3.calculateHomeScore() < set3.calculateAwayScore()){ winCount += 1;}
        if(set4.calculateHomeScore() < set4.calculateAwayScore()){ winCount += 1;}
        if(doubleSet.calculateHomeScore() < doubleSet.calculateAwayScore()){winCount += 1;}
        return winCount;
    }

    public String toString() {
        return "Match: " + getTeamA() + " vs " + getTeamB() +
                "\n SingleSets: "
                + "\nSet{" + getSet1() + "}"
                + "\nSet{" + getSet2() + "} "
                + "\nSet{" + getSet3() + "} "
                + "\nSet{" + getSet4() + "} "
                + "\nDouble set{" + getDouble() + "}";
    }

}

package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DSet {
    int score1a;
    int score1b;
    int score2a;
    int score2b;
    int score3a;
    int score3b;

    public DSet(int score1a, int score1b, int score2a, int score2b, int score3a, int score3b){
        this.score1a = score1a;
        this.score1b = score1b;
        this.score2a = score2a;
        this.score2b = score2b;
        this.score3a = score3a;
        this.score3b = score3b;
    }

    public int getScore1a(){  return this.score1a; }

    public int getScore1b(){
        return this.score1b;
    }
    public int getScore2a(){
        return this.score2a;
    }

    public int getScore2b(){
        return this.score2b;
    }
    public int getScore3a(){
        return this.score3a;
    }
    public int getScore3b(){
        return this.score3b;
    }
    public List getPoints() {
        List<Set> setPoints = new ArrayList(Arrays.asList(getScore1a(), getScore1b(), getScore2a(), getScore2b(), getScore3a(), getScore3b()));

        return setPoints;}



    public int calculateHomeScore(){
        int a = 0;
        if (getScore1a() > getScore1b()){
            a += 1;
        }
        if (getScore2a() > getScore2b()){
            a += 1;
        }
        if (getScore3a() > getScore3b()){
            a += 1;
        }

        return a;
    }

    public int calculateAwayScore(){
        int b = 0 ;
        if  (getScore1a() < getScore1b()){
            b += 1;
        }
        if (getScore2a() < getScore2b()){
            b += 1;
        }
        if (getScore3a() < getScore3b()){
            b += 1;
        }

        return b;
    }

    public String toString() {
        return null + " vs " + null + " = " + getScore1a() +":"+ getScore1b()+","+ getScore2a()+":"+ getScore2b()+","+ getScore3a()+":"+ getScore3b();
    }
}


package sample;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class GenerateClass {
    private ObservableList<Team> teamList = FXCollections.observableArrayList();
    private ObservableList<Match> matchList = FXCollections.observableArrayList();


    public GenerateClass(ObservableList<Team> teamList, ObservableList<Match> matchList){
        this.teamList = teamList;
        this.matchList = matchList;
    }

    public void setTeamList(ObservableList<Team> teamList){
        this.teamList = teamList;
    }

    public void setMatchList(ObservableList<Match> matchList){
        this.matchList = matchList;
    }


    public String[][] Fixtures() {
        //     matchList.clear();
        String separator = " - ";
        String[][] grid = new String[teamList.size()+1][teamList.size()+1];
        grid[0][0] = separator;

        for (int i = 0; i < teamList.size(); i++) {
            grid[0][i+1] = teamList.get(i).toString();

        }

        for (int x = 1; x < teamList.size()+1; x++) {
            grid[x][0] = teamList.get(x-1).toString();
            grid[x][x] = separator;
        }

        for (int x = 1; x < teamList.size()+1; x++) {
            for (int y = 1; y < teamList.size()+1; y++) {
                if (grid[y][x] != " - " && grid[y][x] == null) {
                    String homeTeam = grid[x][0];
                    String awayTeam = grid[y][0];
                    for(int l = 0; l < matchList.size(); l++){
                        if (matchList.get(l).getTeamA().getTeamName()  == homeTeam) {
                            if (matchList.get(l).getTeamB().getTeamName() == awayTeam) {
                                grid[y][x] = matchList.get(l).matchScoreStr();
                                break;
                            }
                        }
                        else {
                            grid[y][x] = " np ";
                        }
                    }

                }
            }
        }
        return grid;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(Fixtures()).replace("], ", "\n").replace("[", " ").replace("]]", " ");
    }

}


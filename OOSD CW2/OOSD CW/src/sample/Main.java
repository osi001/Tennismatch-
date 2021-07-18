package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.util.*;

public class Main extends Application {
    // teams
    Team team1 = new Team("Filton");
    Team team2 = new Team("UWE");
    Team team3 = new Team("KCC");
    Team team4 = new Team("Page");

    // players
    Player player1 = new Player("Alex", team1);
    Player player2 = new Player("Brian", team1);
    Player player3 = new Player("Jin", team2);
    Player player4 = new Player("Julia", team2);
    Player player5 = new Player("Stewart", team2);
    Player player6 = new Player("Chris", team3);
    Player player7 = new Player("Ryan", team3);
    Player player8 = new Player("Peter", team4);
    Player player9 = new Player("Phil", team4);


    //Filton vs UWE
    Set set1Match1 = new Set(player1, player3, 11, 2, 3, 11, 11, 5);
    Set set2Match1 = new Set(player1, player4, 1,11,5,11,11,6);
    Set set3Match1 = new Set(player2, player3, 11,9,11,1,11,1);
    Set set4Match1 = new Set(player2, player4, 11,2,3,11,11,5);
    DSet doubleMatch1 = new DSet(0,11,1,11,2,11);
    Match match1 = new Match(team1, team2, set1Match1, set2Match1, set3Match1, set4Match1, doubleMatch1);

    //UWE vs Page
    Set set1Match2 = new Set(player3, player8, 11,2,3,11,11,5);
    Set set2Match2 = new Set(player3, player9, 11, 1, 5, 11, 11, 6);
    Set set3Match2 = new Set(player4, player8, 11,9,11,1,11,1);
    Set set4Match2 = new Set(player4, player9, 11,2,3,11,11,5);
    DSet doubleMatch2 = new DSet(0,11,1,11,2,11);
    Match match2 = new Match(team2, team4, set1Match2, set2Match2, set3Match2, set4Match2, doubleMatch2);

    List<Player> playerList = new ArrayList<>(Arrays.asList(player1, player2, player3, player4, player5, player6, player7, player8 ,player9));
    ObservableList<Player> watchPlayer = FXCollections.observableArrayList();
    ObservableList<Team> teamList = FXCollections.observableArrayList();
    ObservableList<Match> matchList = FXCollections.observableArrayList(match1, match2);
    GenerateClass g = new GenerateClass(teamList, matchList);
    MatchThread threading = new MatchThread(matchList, teamList);
    Thread t = new Thread (threading);
    private boolean modifySheet = false;
    @Override

    public void start(Stage primaryStage) throws Exception {
        t.start();
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(AdminTab("Admin Page"), ViewerTab("Viewer Page"), ScoreSheetTab("Score sheet"));
        primaryStage.setScene(new Scene(tabPane, 700, 480, Color.LIGHTGRAY));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Tab AdminTab(String text) {


        Tab admin = new Tab(text);

        admin.setClosable(false);
        Label label = new Label("Enter a new team:");
        label.setLayoutX(4);
        label.setTranslateY(20);
        label.setAlignment(Pos.CENTER);


        TextField newTeamTextfield = new TextField();
        newTeamTextfield.setTranslateY(20);
        newTeamTextfield.setTranslateX(40);

        Button addteamBut = new Button("Add team");
        addteamBut.setTranslateX(60);
        addteamBut.setTranslateY(20);
        addteamBut.setPadding(new Insets(10,20,10,20));


        HBox enterteamBox = new HBox(label, newTeamTextfield, addteamBut);
        enterteamBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        enterteamBox.setTranslateX(14);
        enterteamBox.setTranslateY(17);
        enterteamBox.setMaxWidth(530);
        enterteamBox.setMinHeight(81);


        Label label2 = new Label("Enter the name of the new player:");
        label2.setTranslateY(20);
        label2.setAlignment(Pos.CENTER);

        ComboBox<Team> teamComboBox = new ComboBox<>();
        teamComboBox.setPromptText("Select Team");
        teamComboBox.setItems(teamList);
        teamList.addAll(
                team1,
                team2,
                team3,
                team4

        );
        teamComboBox.setTranslateX(50);


        addteamBut.setOnAction(e -> {
            String teamname = newTeamTextfield.getText();
            newTeamTextfield.clear();
            Team newTeam = new Team(teamname);
            teamList.add(newTeam);
            g.setTeamList(teamList);
        });

        teamComboBox.setTranslateX(20);
        teamComboBox.setTranslateY(20);

        TextField newPlayerTextField = new TextField();
        newPlayerTextField.setTranslateY(20);
        newPlayerTextField.setTranslateX(20);

        Button addplayerBut = new Button("Register player");
        addplayerBut.setTranslateX(500);
        addplayerBut.setTranslateY(-7);
        addplayerBut.setOnAction(e -> {
            String name = newPlayerTextField.getText();
            newPlayerTextField.clear();
            Team ob = teamComboBox.getValue();
            Player newPlayer = new Player(name, ob);
            watchPlayer.add(newPlayer);
        });
        HBox enterplayerBox = new HBox(label2, newPlayerTextField, teamComboBox);
        enterplayerBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        enterplayerBox.setTranslateX(20);
        enterplayerBox.setTranslateY(28);
        enterplayerBox.setMaxWidth(530);
        enterplayerBox.setMinHeight(86);


        Label labelBox3 = new Label("This will generate a match between all teams     Warning!! All pre-existing match data will be erased ");
        labelBox3.setMaxWidth(280);
        labelBox3.setTranslateY(12);
        labelBox3.setWrapText(true);
        labelBox3.setAlignment(Pos.CENTER);

        Button generateFixtureBut = new Button("Generate fixtures");
        generateFixtureBut.setTranslateX(80);
        generateFixtureBut.setTranslateY(15);
        HBox generatefixtures = new HBox(labelBox3, generateFixtureBut);
        generatefixtures.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        generatefixtures.setTranslateX(20);
        generatefixtures.setTranslateY(10);
        generatefixtures.setMaxWidth(530);
        generatefixtures.setMinHeight(86);
        generateFixtureBut.setOnAction(e -> {
            g.setMatchList(matchList);
        });


        Label labelBox4 = new Label("The stats report will be generated every 30 seconds. You can also click the button");
        labelBox4.setWrapText(true);
        labelBox4.setMaxWidth(280);
        labelBox4.setTranslateY(20);
        labelBox4.setTranslateX(12);
        labelBox4.setWrapText(true);
        labelBox4.setAlignment(Pos.CENTER);

        Button generateTeamStatsBut = new Button("Generate team stats");
        generateTeamStatsBut.setTranslateX(55);
        generateTeamStatsBut.setTranslateY(30);

        HBox generatestatsBox = new HBox(labelBox4, generateTeamStatsBut);
        generatestatsBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        generatestatsBox.setTranslateX(14);
        generatestatsBox.setTranslateY(28);
        generatestatsBox.setMaxWidth(530);
        generatestatsBox.setMinHeight(86);

        generateTeamStatsBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                t.interrupt();
                // System.out.println(t.isAlive());
                // MatchThreading threading2 = new MatchThreading(matchList, teamList);
                Thread t = new Thread (threading);
                t.start();
            }
        });


        AnchorPane anchorPane = new AnchorPane();
        SplitPane sp = new SplitPane();

        VBox vbox = new VBox(5, sp, anchorPane, enterteamBox, enterplayerBox, addplayerBut, generatefixtures, generatestatsBox);
        vbox.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        admin.setContent(vbox);


        return admin;
    }

    public Tab ViewerTab(String text) {


        List<String> field = new ArrayList();
        List<String> fieldSort = new ArrayList<>();
        Tab viewer = new Tab(text);
        viewer.setClosable(false);
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(50, 0, 20, 40));
        Button viewchart = new Button("View fixture and result chart");
        Button showstats = new Button("Show all team stats");
        Button showranks = new Button("Show all team ranking");
        Button viewscores = new Button("View match scores");



        TextArea textfield = new TextArea();
        textfield.setMinHeight(350);
        textfield.setMaxWidth(400);
        textfield.setTranslateX(190);
        textfield.setTranslateY(-90);

        for(int x = 0; x < teamList.size(); x ++){

            int matchesPlayed = threading.matchesPlayed(teamList.get(x));
            int matchesWon = threading.matchesWon(teamList.get(x));
            int setsWon = threading.setsWon(teamList.get(x));
            field.add(teamList.get(x).toString() + ": Matches played = " + matchesPlayed + ", Matches won = "+ matchesWon +
                    ", Sets won = " + setsWon + "\n");

        }

        showstats.setOnAction(e -> {
            textfield.clear();
            if(field.size() < teamList.size()){
                for(int x = field.size(); x < teamList.size(); x ++){
                    int matchesPlayed = threading.matchesPlayed(teamList.get(x));
                    int matchesWon = threading.matchesWon(teamList.get(x));
                    int setsWon = threading.setsWon(teamList.get(x));
                    field.add(teamList.get(x).toString() + ": Matches played = " + matchesPlayed + ", Matches won = "+ matchesWon +
                            ", Sets won = " + setsWon + "\n");

                }
            }
            textfield.setText(field.toString().replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")
                    .trim());
        });

        for (int i = 0; i < teamList.size(); i++) {
            if (i != 0) {
                int setsWon = threading.setsWon(teamList.get(i));
                int prevSet = threading.setsWon(teamList.get(i - 1));
                if (setsWon > prevSet) {
                    String temp = fieldSort.get(i - 1);
                    fieldSort.set(i - 1, field.get(i));
                    fieldSort.add(temp);
                } else {
                    fieldSort.add(field.get(i));
                }
            } else {
                fieldSort.add(field.get(i));
            }
        }

        showranks.setOnAction(e -> {
            textfield.clear();
            if(fieldSort.size() < teamList.size()){
                for(int x = field.size(); x < teamList.size(); x ++){
                    int matchesPlayed = threading.matchesPlayed(teamList.get(x));
                    int matchesWon = threading.matchesWon(teamList.get(x));
                    int setsWon = threading.setsWon(teamList.get(x));
                    field.add(teamList.get(x).toString() + ": Matches played = " + matchesPlayed + ", Matches won = "+ matchesWon +
                            ", Sets won = " + setsWon + "\n");

                }
                for (int i = fieldSort.size(); i < teamList.size(); i++) {
                    if (i != 0) {
                        int setsWon = threading.setsWon(teamList.get(i));
                        int prevSet = threading.setsWon(teamList.get(i - 1));
                        if (setsWon > prevSet) {
                            String temp = fieldSort.get(i - 1);
                            fieldSort.set(i - 1, field.get(i));
                            fieldSort.add(temp);
                        } else {
                            fieldSort.add(field.get(i));
                        }
                    } else {
                        fieldSort.add(field.get(i));
                    }
                }
            }

            textfield.setText(fieldSort.toString().replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")
                    .trim());
        });
        viewchart.setTranslateX(4);
        viewchart.setTranslateY(30);

        viewchart.setOnAction(e -> {
            final Stage dialog = new Stage();
            AnchorPane anchorPane = new AnchorPane();

            Label l = new Label(g.toString());
            anchorPane.getChildren().add(l);
            Scene dialogScene = new Scene(anchorPane, 300, 320);
            dialog.setScene(dialogScene);
            dialog.show();
        });



        viewscores.setOnAction(e -> {
            final Stage dialog = new Stage();
            Label home = new Label("Select a home team");
            home.setMaxWidth(280);
            home.setTranslateY(12);
            home.setTranslateX(12);
            home.setWrapText(true);
            home.setAlignment(Pos.CENTER);

            ComboBox hometeam = new ComboBox();
            hometeam.setItems(teamList);
            hometeam.setLayoutX(40);
            hometeam.setLayoutY(40);
            hometeam.setPrefHeight(25);
            hometeam.setPrefWidth(70);

            Label away = new Label("Select an away team");
            away.setMaxWidth(280);
            away.setTranslateY(12);
            away.setTranslateX(150);
            away.setWrapText(true);
            away.setAlignment(Pos.CENTER);

            ComboBox awayteam = new ComboBox();
            awayteam.setItems(teamList);
            awayteam.setLayoutX(170);
            awayteam.setLayoutY(40);
            awayteam.setPrefHeight(25);
            awayteam.setPrefWidth(70);

            Button getChoices = new Button("Get Match");
            getChoices.setLayoutX(70);
            getChoices.setLayoutY(120);
            getChoices.setPrefWidth(150);
            getChoices.setPrefHeight(30);

            getChoices.setOnAction(x -> {
                dialog.close();
                Object teamHome = hometeam.getValue();
                Object teamAway = awayteam.getValue();

                for (Match i : matchList) {
                    if (teamHome.equals(i.getTeamA()) && teamAway.equals(i.getTeamB()) ){
                        textfield.setText(i.toString());
                        break;
                    }else{
                        textfield.setText("Match not played");
                    }
                }




            });

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.getChildren().addAll(hometeam, awayteam, home, getChoices, away);
            Scene dialogScene = new Scene(anchorPane, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();

        });
        showranks.setTranslateX(4);
        showranks.setTranslateY(60);

        showstats.setTranslateX(4);
        showstats.setTranslateY(90);

        viewscores.setTranslateX(4);
        viewscores.setTranslateY(120);



        VBox view = new VBox(5, viewchart, showranks, showstats, viewscores, textfield);
        viewer.setContent(view);

        return viewer;
    }

    public Tab ScoreSheetTab(String text) {
        Tab score = new Tab(text);
        score.setClosable(false);


        TextField textfield = new TextField();
        textfield.setMinHeight(12);
        textfield.setMaxHeight(21);
        textfield.setPrefWidth(149);
        textfield.setLayoutX(231);
        textfield.setLayoutY(135);
        textfield.setText(0 + ":" + 0);


        TextField text1field = new TextField();
        text1field.setMinHeight(12);
        text1field.setMaxHeight(21);
        text1field.setPrefWidth(149);
        text1field.setLayoutX(429);
        text1field.setLayoutY(135);
        text1field.setText(0 + ":" + 0);

        TextField textfield1 = new TextField();
        textfield1.setMinHeight(12);
        textfield1.setMaxHeight(21);
        textfield1.setPrefWidth(149);
        textfield1.setLayoutX(231);
        textfield1.setLayoutY(165);
        textfield1.setText(0 + ":" + 0);

        TextField text2field = new TextField();
        text2field.setMinHeight(12);
        text2field.setMaxHeight(21);
        text2field.setPrefWidth(149);
        text2field.setLayoutX(429);
        text2field.setLayoutY(165);
        text2field.setText(0 + ":" + 0);

        TextField textfield2 = new TextField();
        textfield2.setMinHeight(12);
        textfield2.setMaxHeight(21);
        textfield2.setPrefWidth(149);
        textfield2.setLayoutX(231);
        textfield2.setLayoutY(195);
        textfield2.setText(0 + ":" + 0);

        TextField text3field = new TextField();
        text3field.setMinHeight(12);
        text3field.setMaxHeight(21);
        text3field.setPrefWidth(149);
        text3field.setLayoutX(429);
        text3field.setLayoutY(195);
        text3field.setText(0 + ":" + 0);

        TextField textfield3 = new TextField();
        textfield3.setMinHeight(12);
        textfield3.setMaxHeight(21);
        textfield3.setPrefWidth(149);
        textfield3.setLayoutX(231);
        textfield3.setLayoutY(225);
        textfield3.setText(0 + ":" + 0);

        TextField text4field = new TextField();
        text4field.setMinHeight(12);
        text4field.setMaxHeight(21);
        text4field.setPrefWidth(149);
        text4field.setLayoutX(429);
        text4field.setLayoutY(225);
        text4field.setText(0 + ":" + 0);

        TextField textfield4 = new TextField();
        textfield4.setMinHeight(12);
        textfield4.setMaxHeight(21);
        textfield4.setPrefWidth(149);
        textfield4.setLayoutX(231);
        textfield4.setLayoutY(255);
        textfield4.setText(0 + ":" + 0);

        TextField text5field = new TextField();
        text5field.setMinHeight(12);
        text5field.setMaxHeight(21);
        text5field.setPrefWidth(149);
        text5field.setLayoutX(429);
        text5field.setLayoutY(255);
        text5field.setText(0 + ":" + 0);

        TextField textfield5 = new TextField();
        textfield5.setMinHeight(12);
        textfield5.setMaxHeight(21);
        textfield5.setPrefWidth(149);
        textfield5.setLayoutX(231);
        textfield5.setLayoutY(285);
        textfield5.setText(0 + ":" + 0);

        TextField text6field = new TextField();
        text6field.setMinHeight(12);
        text6field.setMaxHeight(21);
        text6field.setPrefWidth(149);
        text6field.setLayoutX(429);
        text6field.setLayoutY(285);
        text6field.setText(0 + ":" + 0);

        TextField textfield6 = new TextField();
        textfield6.setMinHeight(12);
        textfield6.setMaxHeight(21);
        textfield6.setPrefWidth(149);
        textfield6.setLayoutX(231);
        textfield6.setLayoutY(315);
        textfield6.setText(0 + ":" + 0);


        TextArea textArea = new TextArea();
        textArea.setMinHeight(74);
        textArea.setMaxHeight(74);
        textArea.setPrefWidth(149);
        textArea.setLayoutX(429);
        textArea.setLayoutY(325);
        textArea.setText(0 + ":" + 0);


        TextField textfield7 = new TextField();
        textfield7.setMinHeight(12);
        textfield7.setMaxHeight(21);
        textfield7.setPrefWidth(149);
        textfield7.setLayoutX(231);
        textfield7.setLayoutY(345);
        textfield7.setText(0 + ":" + 0);


        TextField textfield8 = new TextField();
        textfield8.setMinHeight(12);
        textfield8.setMaxHeight(21);
        textfield8.setPrefWidth(149);
        textfield8.setLayoutX(231);
        textfield8.setLayoutY(375);
        textfield8.setText(0 + ":" + 0);



        ComboBox homep1 = new ComboBox();
        homep1.setPromptText("Home Player");
        homep1.getItems().addAll(watchPlayer);
        homep1.getItems().addAll(playerList);
        homep1.setLayoutX(40);
        homep1.setLayoutY(177);
        homep1.setPrefHeight(35);
        homep1.setPrefWidth(155);


        ComboBox homep2 = new ComboBox();
        homep2.setPromptText("Home Player");
        homep2.getItems().addAll(playerList);
        homep2.getItems().addAll(watchPlayer);
        homep2.setLayoutX(40);
        homep2.setLayoutY(244);
        homep2.setPrefHeight(35);
        homep2.setPrefWidth(155);

        Label singleSet = new Label("Single Sets");
        singleSet.setMaxWidth(500);
        singleSet.setLayoutX(70);
        singleSet.setLayoutY(110);
        singleSet.setFont(new Font(20));


        Label doubleSet = new Label("Double Set");
        doubleSet.setMaxWidth(500);
        doubleSet.setLayoutX(70);
        doubleSet.setLayoutY(320);
        doubleSet.setFont(new Font(20));



        Button calculate = new Button();
        calculate.setText("Calculate & Submit");
        calculate.setLayoutX(280);
        calculate.setLayoutY(405);
        calculate.setPrefHeight(35);
        calculate.setPrefWidth(250);


        ComboBox awayp1 = new ComboBox();
        awayp1.setPromptText("Away Player");
        awayp1.getItems().addAll(playerList);
        awayp1.getItems().addAll(watchPlayer);
        awayp1.setLayoutX(419);
        awayp1.setLayoutY(83);
        awayp1.setPrefHeight(35);
        awayp1.setPrefWidth(155);


        ComboBox awayp2 = new ComboBox();
        awayp2.setPromptText("Away Player");
        awayp2.getItems().addAll(playerList);
        awayp2.getItems().addAll(watchPlayer);
        awayp2.setLayoutX(224);
        awayp2.setLayoutY(83);
        awayp2.setPrefHeight(35);
        awayp2.setPrefWidth(155);


        ComboBox<Team> homeTeam= new ComboBox();
        ComboBox awayTeam = new ComboBox();

        //    homeTeam.getItems().addAll(teamList);
        homeTeam.setItems(teamList);
        homeTeam.setLayoutX(234);
        homeTeam.setLayoutY(45);
        homeTeam.setPrefHeight(26);
        homeTeam.setPrefWidth(134);
        homeTeam.setPromptText("Home Team");
        homeTeam.setOnAction(e -> {
            homep1.getItems().removeAll(homep1.getItems());
            homep2.getItems().removeAll(homep2.getItems());

            for(int x =0; x < watchPlayer.size(); x++){
                Player p = watchPlayer.get(x);
                if(p.getTeamName().equals(homeTeam.getValue())){

                    homep1.getItems().addAll(p);
                    homep2.getItems().addAll(p);
                    break;
                }
            }

            for(Player p: playerList){
                if(p.getTeamName().equals(homeTeam.getValue())){
                    homep1.getItems().addAll(p);
                    homep2.getItems().addAll(p);
                }
            }
            if(modifySheet == true && awayTeam.getValue() != null){
                textfield.setText(getScore(homeTeam, awayTeam, 0, 1));
                textfield1.setText(getScore(homeTeam, awayTeam, 2, 1));
                textfield2.setText(getScore(homeTeam, awayTeam, 4, 1));

                textfield3.setText(getScore(homeTeam, awayTeam, 0, 3));
                textfield4.setText(getScore(homeTeam, awayTeam, 2, 3));
                textfield5.setText(getScore(homeTeam, awayTeam, 4, 3));

                text1field.setText(getScore(homeTeam, awayTeam, 0, 2));
                text2field.setText(getScore(homeTeam, awayTeam, 2, 2));
                text3field.setText(getScore(homeTeam, awayTeam, 4, 2));

                text4field.setText(getScore(homeTeam, awayTeam, 0, 4));
                text5field.setText(getScore(homeTeam, awayTeam, 2, 4));
                text6field.setText(getScore(homeTeam, awayTeam, 4, 4));

                textfield6.setText(getScore(homeTeam, awayTeam, 0, 5));
                textfield7.setText(getScore(homeTeam, awayTeam, 2, 5));
                textfield8.setText(getScore(homeTeam, awayTeam, 4, 5));

                textArea.setText(getScore(homeTeam, awayTeam, 0, 6));

                for (Match match: matchList){
                    if(homeTeam.getValue().equals(match.getTeamA())) {
                        if (awayTeam.getValue().equals(match.getTeamB())) {
                            homep1.setValue(match.set1.getPlayerA());
                            homep2.setValue(match.set4.getPlayerA());
                            awayp1.setValue(match.set2.getPlayerB());
                            awayp2.setValue(match.set1.getPlayerB());
                        }
                    }
                }
            }
        });




        awayTeam.setItems(teamList);
        awayTeam.setLayoutX(429);
        awayTeam.setLayoutY(45);
        awayTeam.setPrefHeight(26);
        awayTeam.setPrefWidth(134);
        awayTeam.setPromptText("Away Team");

        awayTeam.setOnAction(e -> {
            awayp1.getItems().removeAll(awayp1.getItems());
            awayp2.getItems().removeAll(awayp2.getItems());

            for(int x =0; x < watchPlayer.size(); x++){
                Player p = watchPlayer.get(x);
                if(p.getTeamName().equals(awayTeam.getValue())){
                    awayp1.getItems().addAll(p);
                    awayp2.getItems().addAll(p);
                    break;
                }
            }

            for(Player p: playerList){
                if(p.getTeamName().equals(awayTeam.getValue())){
                    awayp1.getItems().addAll(p);
                    awayp2.getItems().addAll(p);

                }
            }
            if(modifySheet == true && homeTeam.getValue() != null){
                textfield.setText(getScore(homeTeam, awayTeam, 0, 1));
                textfield1.setText(getScore(homeTeam, awayTeam, 2, 1));
                textfield2.setText(getScore(homeTeam, awayTeam, 4, 1));

                textfield3.setText(getScore(homeTeam, awayTeam, 0, 3));
                textfield4.setText(getScore(homeTeam, awayTeam, 2, 3));
                textfield5.setText(getScore(homeTeam, awayTeam, 4, 3));

                text1field.setText(getScore(homeTeam, awayTeam, 0, 2));
                text2field.setText(getScore(homeTeam, awayTeam, 2, 2));
                text3field.setText(getScore(homeTeam, awayTeam, 4, 2));

                text4field.setText(getScore(homeTeam, awayTeam, 0, 4));
                text5field.setText(getScore(homeTeam, awayTeam, 2, 4));
                text6field.setText(getScore(homeTeam, awayTeam, 4, 4));

                textfield6.setText(getScore(homeTeam, awayTeam, 0, 5));
                textfield7.setText(getScore(homeTeam, awayTeam, 2, 5));
                textfield8.setText(getScore(homeTeam, awayTeam, 4, 5));

                textArea.setText(getScore(homeTeam, awayTeam, 0, 6));

                for (Match match: matchList){
                    if(homeTeam.getValue().equals(match.getTeamA())) {
                        if (awayTeam.getValue().equals(match.getTeamB())) {
                            homep1.setValue(match.set1.getPlayerA());
                            homep2.setValue(match.set4.getPlayerA());
                            awayp1.setValue(match.set2.getPlayerB());
                            awayp2.setValue(match.set1.getPlayerB());
                        }
                    }
                }


            }
        });


        Button modifysheet = new Button("Modify sheet");
        modifysheet.setOnAction(e -> {
            modifySheet = true;
            if(awayTeam.getValue() != null && homeTeam.getValue() != null){
                textfield.setText(getScore(homeTeam, awayTeam, 0, 1));
                textfield1.setText(getScore(homeTeam, awayTeam, 2, 1));
                textfield2.setText(getScore(homeTeam, awayTeam, 4, 1));

                textfield3.setText(getScore(homeTeam, awayTeam, 0, 3));
                textfield4.setText(getScore(homeTeam, awayTeam, 2, 3));
                textfield5.setText(getScore(homeTeam, awayTeam, 4, 3));

                text1field.setText(getScore(homeTeam, awayTeam, 0, 2));
                text2field.setText(getScore(homeTeam, awayTeam, 2, 2));
                text3field.setText(getScore(homeTeam, awayTeam, 4, 2));

                text4field.setText(getScore(homeTeam, awayTeam, 0, 4));
                text5field.setText(getScore(homeTeam, awayTeam, 2, 4));
                text6field.setText(getScore(homeTeam, awayTeam, 4, 4));

                textfield6.setText(getScore(homeTeam, awayTeam, 0, 5));
                textfield7.setText(getScore(homeTeam, awayTeam, 2, 5));
                textfield8.setText(getScore(homeTeam, awayTeam, 4, 5));

                textArea.setText(getScore(homeTeam, awayTeam, 0, 6));
                for (Match match: matchList){
                    if(homeTeam.getValue().equals(match.getTeamA())) {
                        if (awayTeam.getValue().equals(match.getTeamB())) {
                            homep1.setValue(match.set1.getPlayerA());
                            homep2.setValue(match.set4.getPlayerA());
                        }
                    }
                }

            }
        });


        modifysheet.setPrefWidth(140);
        modifysheet.setPrefHeight(25);
        modifysheet.setLayoutX(252);
        modifysheet.setLayoutY(8);

        AnchorPane anchorPane = new AnchorPane();

        Button newsheet = new Button("New sheet");
        newsheet.setOnAction(e -> {
            modifySheet = false;
            if(modifySheet == false) {
                homeTeam.setValue(null);
                homeTeam.setPromptText("Home Team");
                awayTeam.setValue(null);
                awayTeam.setPromptText("Away Team");
                for (Node node : anchorPane.getChildren()) {
                    if (node instanceof TextField) {
                        // clear
                        ((TextField) node).setText(0 + ":" + 0);
                    }

                }
                textArea.setText(0 + ":" + 0);
            }


        });
        newsheet.setLayoutX(55);
        newsheet.setLayoutY(7);
        newsheet.setPrefWidth(140);
        newsheet.setPrefHeight(25);

        calculate.setOnAction(e -> {

            Player p1 = null;
            Player p2 = null;
            Player p3 = null;
            Player p4 = null;
            for(Player p: playerList){
                if(homep1.getValue().equals(p)){
                    p1 = p;
                }
                else if(homep2.getValue().equals(p)){
                    p2 = p;
                }
                else if(awayp1.getValue().equals(p)){
                    p3 = p;
                }
                else if(awayp2.getValue().equals(p)){
                    p4 = p;
                }
            }

            Team t1 = null;
            Team t2 = null;
            for(Team t: teamList){
                if(homeTeam.getValue().equals(t)){
                    t1 = t;
                }
                else if(awayTeam.getValue().equals(t)){
                    t2 = t;
                }
            }

            Set set1Match = new Set(p1, p4, Integer.parseInt(textfield.getText().split(":")[0]), Integer.parseInt(textfield.getText().split(":")[1]), Integer.parseInt(textfield1.getText().split(":")[0]), Integer.parseInt(textfield1.getText().split(":")[1]), Integer.parseInt(textfield2.getText().split(":")[0]),Integer.parseInt(textfield2.getText().split(":")[1]));
            Set set2Match = new Set(p1, p3, Integer.parseInt(text1field.getText().split(":")[0]),Integer.parseInt(text1field.getText().split(":")[1]),Integer.parseInt(text2field.getText().split(":")[0]),Integer.parseInt(text2field.getText().split(":")[1]),Integer.parseInt(text3field.getText().split(":")[0]),Integer.parseInt(text3field.getText().split(":")[1]));
            Set set3Match = new Set(p2, p4,Integer.parseInt(textfield3.getText().split(":")[0]),Integer.parseInt(textfield3.getText().split(":")[1]),Integer.parseInt(textfield4.getText().split(":")[0]),Integer.parseInt(textfield4.getText().split(":")[1]),Integer.parseInt(textfield5.getText().split(":")[0]),Integer.parseInt(textfield5.getText().split(":")[1]));
            Set set4Match = new Set(p2, p3, Integer.parseInt(text4field.getText().split(":")[0]),Integer.parseInt(text4field.getText().split(":")[1]),Integer.parseInt(text5field.getText().split(":")[0]),Integer.parseInt(text5field.getText().split(":")[1]),Integer.parseInt(text6field.getText().split(":")[0]),Integer.parseInt(text6field.getText().split(":")[1]));
            DSet doubleMatch = new DSet(Integer.parseInt(textfield6.getText().split(":")[0]),Integer.parseInt(textfield6.getText().split(":")[1]),Integer.parseInt(textfield7.getText().split(":")[0]),Integer.parseInt(textfield7.getText().split(":")[1]),Integer.parseInt(textfield8.getText().split(":")[0]),Integer.parseInt(textfield8.getText().split(":")[1]));

            if(modifySheet == false){

                Match match = new Match(t1, t2, set1Match, set2Match, set3Match, set4Match, doubleMatch);
                matchList.add(match);
            }

            if (modifySheet == true){
                for (Match match: matchList){
                    if(homeTeam.getValue().equals(match.getTeamA())){
                        if(awayTeam.getValue().equals(match.getTeamB())){
                            match.set1.setPlayerA(p1);
                            match.set1.setPlayerB(p3);
                            match.set2.setPlayerA(p1);
                            match.set2.setPlayerB(p4);
                            match.set3.setPlayerA(p2);
                            match.set3.setPlayerB(p3);
                            match.set4.setPlayerA(p2);
                            match.set4.setPlayerB(p4);
                            match.setTeamNameB(t2);
                            match.setSet1(set1Match);
                            match.setSet2(set2Match);
                            match.setSet3(set3Match);
                            match.setSet4(set4Match);
                            match.setDoubleSet(doubleMatch);
                        }
                    }
                }
            }
        });

        anchorPane.getChildren().addAll(doubleSet, singleSet, calculate, awayp1, awayp2, homep1, homep2, newsheet, modifysheet, awayTeam, homeTeam, textArea, text1field, text2field, text3field, text4field, text5field, text6field, textfield, textfield1, textfield2, textfield3, textfield4, textfield5, textfield6, textfield7, textfield8);
        anchorPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        score.setContent(anchorPane);



        return score;
    }






    public String getScore(ComboBox homeTeam, ComboBox awayTeam, int setPoint, Integer set){

        if(modifySheet == true) {
            Object teamHome = homeTeam.getValue();
            Object teamAway = awayTeam.getValue();
            for (Match i : matchList) {
                if (teamHome.equals(i.getTeamA()) && teamAway.equals(i.getTeamB())) {
                    if (set.equals(1)) {
                        Object point1 = i.set1.getPoints().get(setPoint);
                        Object point2 = i.set1.getPoints().get(setPoint + 1);
                        return point1.toString() + ":" + point2.toString();
                    }
                    else if(set.equals(2)){
                        Object point1 = i.set2.getPoints().get(setPoint);
                        Object point2 = i.set2.getPoints().get(setPoint + 1);
                        return point1.toString() + ":" + point2.toString();
                    }
                    else if(set.equals(3)){
                        Object point1 = i.set3.getPoints().get(setPoint);
                        Object point2 = i.set3.getPoints().get(setPoint + 1);
                        return point1.toString() + ":" + point2.toString();
                    }
                    else if(set.equals(4)) {
                        Object point1 = i.set4.getPoints().get(setPoint);
                        Object point2 = i.set4.getPoints().get(setPoint + 1);
                        return point1.toString() + ":" + point2.toString();
                    }
                    else if(set.equals(5)){
                        Object point1 = i.doubleSet.getPoints().get(setPoint);
                        Object point2 = i.doubleSet.getPoints().get(setPoint + 1);
                        return point1.toString() + ":" + point2.toString();
                    }

                    else if(set.equals(6)){
                        return i.matchScoreStr();
                    }

                }
            }

        }
        return "0:0";
    }

    public static void main(String[] args) {



        launch(args);
    }


}


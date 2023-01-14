package com.thomasriotapi;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    final private String SCENE_TITLE = "SCENE TITLE";
    final private String DEFAULTSUMNAME = "Enter summoner name here";

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene0 = new Scene(root,400,600, Color.SEAGREEN);

        //Image appIcon = new Image("some icon path");

        Text summonerName = new Text(50,50, DEFAULTSUMNAME);

        root.getChildren().add(summonerName);


        stage.setTitle(SCENE_TITLE);
        stage.setScene(scene0);
        stage.show();
    }

    public static void main(String[] args) {
        //RiotApiExemples.getChampionExemple("lordofallganjas");
        launch(args);
    }
}
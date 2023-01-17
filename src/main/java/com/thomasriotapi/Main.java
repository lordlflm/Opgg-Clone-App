package com.thomasriotapi;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.awt.*;

import static java.awt.Font.BOLD;

public class Main extends Application {

    //javafx variables
    private TextField sumNameField;
    private final Insets insets = new Insets(10,10,10,10);
    private final Label emptyLabel = new Label("");
    private ChoiceBox<String> regionMenu;
    private Label sumNameLabel;
    private Label level;

    //riot variables
    String sumName;
    String region;
    @Override
    public void start(Stage stage) throws Exception {

        //SEARCH LAYER NODE
        HBox searchLayer = new HBox();
        searchLayer.setSpacing(10);
        searchLayer.setAlignment(Pos.TOP_CENTER);
        searchLayer.setPadding(insets);
        sumNameField = new TextField();
        Button submitButton = new Button("Submit");
        regionMenu = new ChoiceBox<>();
        regionMenu.getItems().addAll( "NA", "KR", "EUW", "EUNE", "LAN", "LAS", "OCE", "JP", "RU");
        regionMenu.setValue("Region");
        searchLayer.getChildren().addAll(regionMenu, sumNameField, submitButton);



        //RANK INFO LAYER NODE
        HBox rankInfoLayer = new HBox();
        rankInfoLayer.setSpacing(30);
        rankInfoLayer.setAlignment(Pos.TOP_CENTER);
        rankInfoLayer.setPadding(insets);
        //winrates
        VBox winrateLayer = new VBox();
        winrateLayer.setSpacing(10);
        winrateLayer.setAlignment(Pos.CENTER_LEFT);
        winrateLayer.setPadding(insets);
        Label rankNodeLabel = new Label("Rank profile");
        rankNodeLabel.setAlignment(Pos.TOP_LEFT);
        rankNodeLabel.setFont(new Font("Consolas", 13));
        rankNodeLabel.setStyle("-fx-font-weight: bold");
        Label seasonWinrateLabel = new Label("Season winrate : ");
        Label monthWinrateLabel = new Label("Month winrate : ");
        winrateLayer.getChildren().addAll(rankNodeLabel, seasonWinrateLabel, monthWinrateLabel);
        //middle summoner info layer
        VBox middleLayer = new VBox();
        middleLayer.setSpacing(10);
        middleLayer.setAlignment(Pos.CENTER);
        middleLayer.setPadding(insets);
        sumNameLabel = new Label();
        //Image rankImage = new Image();
        //Image sumIcon = new Image();
        level = new Label("Level : ");
        Label rankLabel = new Label("Rank : ");
        Label ladderLabel = new Label("Ladder : ");
        middleLayer.getChildren().addAll(sumNameLabel, level, rankLabel, ladderLabel);
        //game played layer
        VBox gamesPlayedLayer = new VBox();
        gamesPlayedLayer.setSpacing(10);
        gamesPlayedLayer.setAlignment(Pos.CENTER_RIGHT);
        gamesPlayedLayer.setPadding(insets);
        Label seasonGamePlayed = new Label("Season games played : ");
        Label monthGamePlayed = new Label("Month games played : ");
        gamesPlayedLayer.getChildren().addAll(emptyLabel, seasonGamePlayed, monthGamePlayed);

        rankInfoLayer.getChildren().addAll(winrateLayer, middleLayer, gamesPlayedLayer);



        //CHAMPION INFO LAYER NODE
        VBox championInfoLayer = new VBox();

        championInfoLayer.getChildren().addAll();



        //MATCH HISTORY LAYER NODE



        //EVENTS HANDLING
        //button pressed
        submitButton.setOnAction(event -> {
            getData();
            loadSummonerData(sumName, region);
        });
        //Enter pressed
        sumNameField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                getData();
                loadSummonerData(sumName, region);
            }
        });



        //ROOT NODE
        VBox root = new VBox();
        root.getChildren().addAll(searchLayer, rankInfoLayer, championInfoLayer);



        //SCENE SETUP
        Scene scene = new Scene(root,600,600);
        //constants
        String SCENE_TITLE = "SCENE TITLE";
        stage.setTitle(SCENE_TITLE); //window title
        //Image appIcon = new Image("some icon path");
        stage.setScene(scene);
        stage.show();
    }

    public void loadSummonerData(String sumName, String region) {
        String id = OriannaHandler.getId(sumName,region);
        if (id == null || id.equals("")) {
            sumNameLabel.setText("No summoner found");

        } else {
            sumNameLabel.setText(sumName.toUpperCase());
            level.setText("Level : " + OriannaHandler.levelToString(sumName,region));
        }
    }

    private void getData() {
        sumName = sumNameField.getText();
        region = regionMenu.getValue();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
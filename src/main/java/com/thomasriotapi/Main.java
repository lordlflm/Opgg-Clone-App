package com.thomasriotapi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.sound.sampled.Clip;

public class Main extends Application {

    //javafx variables
    private TextField sumNameField;
    private final Insets insets = new Insets(10,10,10,10);
    private final Label emptyLabel = new Label("");
    private ChoiceBox<String> regionMenu;
    private Label sumNameLabel;
    private Label level;
    private Label rankNodeLabel;
    private Label seasonWinrateLabel;
    private Label monthWinrateLabel;
    private ImageView sumIconView;
    private ImageView rankImageView;
    private Label rankLabel;
    private Label seasonGamePlayed;

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
        rankNodeLabel = new Label();
        rankNodeLabel.setAlignment(Pos.TOP_LEFT);
        rankNodeLabel.setFont(new Font("Consolas", 13));
        rankNodeLabel.setStyle("-fx-font-weight: bold");
        seasonWinrateLabel = new Label();
        monthWinrateLabel = new Label();
        winrateLayer.getChildren().addAll(rankNodeLabel, seasonWinrateLabel, monthWinrateLabel);
        //middle summoner info layer
        VBox middleLayer = new VBox();
        middleLayer.setSpacing(10);
        middleLayer.setAlignment(Pos.CENTER);
        middleLayer.setPadding(insets);
        sumNameLabel = new Label("Enter Summoner name and region");
        sumNameLabel.setAlignment(Pos.TOP_CENTER);

        //sum icon
        sumIconView = new ImageView();
        sumIconView.setFitHeight(60);
        sumIconView.setFitWidth(60);
        Circle clip = new Circle(sumIconView.getFitHeight()/2);
        clip.setCenterX(30);
        clip.setCenterY(30);
        sumIconView.setClip(clip);

        rankImageView = new ImageView();
        //Image rankImage = new Image();



        level = new Label();
        rankLabel = new Label();
        Label ladderLabel = new Label();
        middleLayer.getChildren().addAll(sumNameLabel, sumIconView, rankImageView, level, rankLabel, ladderLabel);
        //game played layer
        VBox gamesPlayedLayer = new VBox();
        gamesPlayedLayer.setSpacing(10);
        gamesPlayedLayer.setAlignment(Pos.CENTER_RIGHT);
        gamesPlayedLayer.setPadding(insets);
        seasonGamePlayed = new Label();
        Label monthGamePlayed = new Label();
        gamesPlayedLayer.getChildren().addAll(emptyLabel, seasonGamePlayed, monthGamePlayed);

        rankInfoLayer.getChildren().addAll(winrateLayer, middleLayer, gamesPlayedLayer);



        //CHAMPION INFO LAYER NODE
        VBox championInfoLayer = new VBox();

        championInfoLayer.getChildren().addAll();



        //MATCH HISTORY LAYER NODE



        //EVENTS HANDLING
        //button pressed
        submitButton.setOnAction(event -> {
            getInput();
            loadSummonerData(sumName, region);
        });
        //Enter pressed
        sumNameField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                getInput();
                loadSummonerData(sumName, region);
            }
        });



        //ROOT NODE
        VBox root = new VBox();
        root.getChildren().addAll(searchLayer, rankInfoLayer, championInfoLayer);



        //SCENE SETUP
        Scene scene = new Scene(root,600,600);
        //constants
        String SCENE_TITLE = "League of Legends java companion";
        stage.setTitle(SCENE_TITLE); //window title
        //Image appIcon = new Image("some icon path");
        stage.setScene(scene);
        stage.show();
    }

    public void loadSummonerData(String sumName, String regionString) {
        String id = OriannaHandler.getId(sumName,regionString);
        //no summoner found
        if (id == null || id.equals("")) {
            rankNodeLabel.setText("");
            sumNameLabel.setText("No summoner found\nEnter summoner name and region");
            sumNameLabel.setTextFill(Color.RED);
            level.setText("");
            rankLabel.setText("");
            seasonWinrateLabel.setText("");
            seasonGamePlayed.setText("");
        //summoner found
        } else {
            rankNodeLabel.setText("Rank profile");
            sumNameLabel.setText(sumName.toUpperCase());
            sumNameLabel.setTextFill(Color.BLACK);
            level.setText("level : " + OriannaHandler.levelToString(sumName,regionString));
            rankLabel.setText("rank : " + OriannaHandler.getRank(sumName, regionString));
            seasonGamePlayed.setText("season games played : " + OriannaHandler.getSeasonGamesPlayed(sumName, regionString));
            seasonWinrateLabel.setText("season winrate : " + OriannaHandler.getSeasonWinrate(sumName, regionString));
            sumIconView.setImage(new Image(OriannaHandler.profileIcon(sumName,regionString)));
            try {
                rankImageView.setImage(new Image(OriannaHandler.rankIcon(sumName, regionString)));
            } catch (NullPointerException e) {
                return;
            }
        }
    }

    private void getInput() {
        sumName = sumNameField.getText();
        region = regionMenu.getValue();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
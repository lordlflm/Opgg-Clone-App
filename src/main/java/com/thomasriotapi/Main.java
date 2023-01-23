package com.thomasriotapi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
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
    private Label champ1MastScore;
    private Label champ2MastScore;
    private Label champ3MastScore;
    private Label champ1Name;
    private Label champ2Name;
    private Label champ3Name;
    private Label champNodeLabel;
    private ImageView champ1icon;
    private ImageView champ2icon;
    private ImageView champ3icon;
    private HBox champTitle;
    private HBox rankTitle;

    //riot variables
    String sumName;
    String region;
    @Override
    public void start(Stage stage) throws Exception {

        //SEARCH LAYER
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

        //title layer
        rankTitle = new HBox();
        rankTitle.setAlignment(Pos.TOP_CENTER);
        rankTitle.setSpacing(10);
        rankTitle.setPadding(insets);
        rankNodeLabel = new Label();
        rankNodeLabel.setAlignment(Pos.CENTER_LEFT);
        rankNodeLabel.setFont(new Font("Consolas", 13));
        rankNodeLabel.setStyle("-fx-font-weight: bold");
        rankTitle.getChildren().addAll(rankNodeLabel);

        //RANK INFO LAYER
        HBox rankInfoLayer = new HBox();
        rankInfoLayer.setSpacing(30);
        rankInfoLayer.setAlignment(Pos.TOP_CENTER);
        rankInfoLayer.setPadding(insets);
        //winrates
        VBox winrateLayer = new VBox();
        winrateLayer.setSpacing(10);
        winrateLayer.setAlignment(Pos.CENTER_LEFT);
        winrateLayer.setPadding(insets);
        seasonWinrateLabel = new Label();
        monthWinrateLabel = new Label();
        winrateLayer.getChildren().addAll(seasonWinrateLabel, monthWinrateLabel);

        //middle summoner info layer
        VBox middleLayer = new VBox();
        middleLayer.setSpacing(0);
        middleLayer.setAlignment(Pos.CENTER);
        middleLayer.setPadding(insets);
        sumNameLabel = new Label("Enter Summoner name and region");
        sumNameLabel.setAlignment(Pos.TOP_CENTER);
        //sum icon
        StackPane imageStackPane = new StackPane();
        sumIconView = new ImageView();
        sumIconView.setFitHeight(60);
        sumIconView.setFitWidth(60);
        Circle clip = new Circle(sumIconView.getFitHeight()/2);
        clip.setCenterX(30);
        clip.setCenterY(30);
        sumIconView.setClip(clip);
        //rank icon
        rankImageView = new ImageView();
        rankImageView.setFitHeight(125);
        rankImageView.setFitWidth(125);
        imageStackPane.getChildren().addAll(sumIconView, rankImageView);
        level = new Label();
        rankLabel = new Label();
        Label ladderLabel = new Label();
        middleLayer.getChildren().addAll(sumNameLabel, level, imageStackPane, rankLabel, ladderLabel);

        //game played layer
        VBox gamesPlayedLayer = new VBox();
        gamesPlayedLayer.setSpacing(10);
        gamesPlayedLayer.setAlignment(Pos.CENTER_RIGHT);
        gamesPlayedLayer.setPadding(insets);
        seasonGamePlayed = new Label();
        Label monthGamePlayed = new Label();
        gamesPlayedLayer.getChildren().addAll(seasonGamePlayed, monthGamePlayed);

        rankInfoLayer.getChildren().addAll(winrateLayer, middleLayer, gamesPlayedLayer);

        //title layer
        champTitle = new HBox();
        champTitle.setAlignment(Pos.TOP_CENTER);
        champTitle.setSpacing(10);
        champTitle.setPadding(insets);
        champNodeLabel = new Label();
        champNodeLabel.setAlignment(Pos.CENTER_LEFT);
        champNodeLabel.setFont(new Font("Consolas", 13));
        champNodeLabel.setStyle("-fx-font-weight: bold");
        champTitle.getChildren().addAll(champNodeLabel);

        //CHAMPION INFO LAYER
        HBox championInfoLayer = new HBox();
        championInfoLayer.setSpacing(20);
        championInfoLayer.setAlignment(Pos.CENTER);
        championInfoLayer.setPadding(insets);
        //Most masteries champ
        VBox firstChampBox = new VBox();
        firstChampBox.setSpacing(20);
        firstChampBox.setAlignment(Pos.CENTER_LEFT);
        firstChampBox.setPadding(insets);
        champ1MastScore = new Label();
        champ1MastScore.setAlignment(Pos.CENTER);
        champ1Name = new Label();
        champ1Name.setAlignment(Pos.CENTER);
        champ1icon = new ImageView();
        champ1icon.setFitHeight(60);
        champ1icon.setFitWidth(60);

        firstChampBox.getChildren().addAll(champ1Name, champ1icon, champ1MastScore);
        //2nd most masteries champ
        VBox secondChampBox = new VBox();
        secondChampBox.setSpacing(20);
        secondChampBox.setAlignment(Pos.CENTER_LEFT);
        secondChampBox.setPadding(insets);
        champ2MastScore = new Label();
        champ2MastScore.setAlignment(Pos.CENTER);
        champ2Name= new Label();
        champ2Name.setAlignment(Pos.CENTER);
        champ2icon = new ImageView();
        champ2icon.setFitHeight(60);
        champ2icon.setFitWidth(60);

        secondChampBox.getChildren().addAll(champ2Name, champ2icon, champ2MastScore);
        //3rd most mastery champ
        VBox thirdChampBox = new VBox();
        thirdChampBox.setSpacing(20);
        thirdChampBox.setAlignment(Pos.CENTER_LEFT);
        thirdChampBox.setPadding(insets);
        champ3MastScore = new Label();
        champ3MastScore.setAlignment(Pos.CENTER);
        champ3Name= new Label();
        champ3Name.setAlignment(Pos.CENTER);
        champ3icon = new ImageView();
        champ3icon.setFitHeight(60);
        champ3icon.setFitWidth(60);
        thirdChampBox.getChildren().addAll(champ3Name, champ3icon, champ3MastScore);

        championInfoLayer.getChildren().addAll(firstChampBox,secondChampBox,thirdChampBox);



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
        root.getChildren().addAll(searchLayer, rankTitle, rankInfoLayer, champTitle, championInfoLayer);

        //SCENE SETUP
        Scene scene = new Scene(root,600,600);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(182,212,219),null,null)));
        //constants
        final String SCENE_TITLE = "League of Legends Java Companion";
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
            rankTitle.setBackground(null);
            champTitle.setBackground(null);
            champNodeLabel.setText("");
            sumNameLabel.setText("No summoner found");
            sumNameLabel.setTextFill(Color.RED);
            level.setText("Enter summoner name and region");
            level.setTextFill(Color.RED);
            rankLabel.setText("");
            seasonWinrateLabel.setText("");
            seasonGamePlayed.setText("");
            rankImageView.setImage(null);
            sumIconView.setImage(null);

        //summoner found
        } else {

            //summoner profile
            rankNodeLabel.setText("Rank profile");
            rankTitle.setBackground(new Background(new BackgroundFill(Color.GREY,null,null)));
            champNodeLabel.setText("Champions profile");
            champTitle.setBackground(new Background(new BackgroundFill(Color.GREY,null,null)));
            sumNameLabel.setText(sumName.toUpperCase());
            sumNameLabel.setTextFill(Color.BLACK);
            level.setText("level : " + OriannaHandler.levelToString(sumName,regionString));
            level.setTextFill(Color.BLACK);
            rankLabel.setText("rank : " + OriannaHandler.getRank(sumName, regionString));
            seasonGamePlayed.setText("season games played : " + OriannaHandler.getSeasonGamesPlayed(sumName, regionString));
            seasonWinrateLabel.setText("season winrate : " + OriannaHandler.getSeasonWinrate(sumName, regionString));
            sumIconView.setImage(new Image(OriannaHandler.profileIcon(sumName,regionString)));
            try {
                rankImageView.setImage(new Image(OriannaHandler.rankIcon(sumName, regionString)));
            } catch (NullPointerException e) {
                rankImageView.setImage(null);
            }

            //champions profile
            champ1MastScore.setText("score : " + OriannaHandler.getMasteriesPoints(sumName,regionString)[0].toString());
            champ2MastScore.setText("score : " + OriannaHandler.getMasteriesPoints(sumName,regionString)[1].toString());
            champ3MastScore.setText("score : " + OriannaHandler.getMasteriesPoints(sumName,regionString)[2].toString());
            champ1Name.setText(OriannaHandler.getNamesFromIds(sumName,regionString)[0]);
            champ2Name.setText(OriannaHandler.getNamesFromIds(sumName,regionString)[1]);
            champ3Name.setText(OriannaHandler.getNamesFromIds(sumName,regionString)[2]);
            champ1icon.setImage(new Image("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/"
                    + OriannaHandler.getMasteryChampIds(sumName,regionString)[0].toString() + ".png"));
            champ2icon.setImage(new Image("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/"
                    + OriannaHandler.getMasteryChampIds(sumName,regionString)[1].toString() + ".png"));
            champ3icon.setImage(new Image("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/"
                    + OriannaHandler.getMasteryChampIds(sumName,regionString)[2].toString() + ".png"));

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
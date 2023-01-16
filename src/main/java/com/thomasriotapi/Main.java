package com.thomasriotapi;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
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
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Main extends Application {

    //javafx variables
    private TextField sumNameField;
    private final Insets insets = new Insets(10,10,10,10);
    private ChoiceBox<String> regionMenu;
    private Label sumNameLabel;

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
        //Image sumIcon = new Image();
        searchLayer.getChildren().addAll(regionMenu, sumNameField, submitButton);



        //RANK INFO LAYER NODE
        VBox rankInfoLayer = new VBox();
        rankInfoLayer.setSpacing(10);
        rankInfoLayer.setAlignment(Pos.TOP_CENTER);
        rankInfoLayer.setPadding(insets);
        //Image rankImage = new Image();
        sumNameLabel = new Label();
        Label rankLabel = new Label();
        Label ladderLabel = new Label();
        Label seasonWinrateLabel = new Label();
        Label monthWinrateLabel = new Label();
        Label seasonGamePlayed = new Label();
        Label monthGamePlayed = new Label();
        rankInfoLayer.getChildren().addAll(sumNameLabel, rankLabel, ladderLabel, seasonWinrateLabel, monthWinrateLabel, seasonGamePlayed, monthGamePlayed);



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
        Scene scene = new Scene(root,400,600, Color.SEAGREEN);
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
            sumNameLabel.setText(sumName);
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
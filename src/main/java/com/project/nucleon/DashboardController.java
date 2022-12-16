package com.project.nucleon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable{
    @FXML
    private Button BuComputerInfo;

    @FXML
    private Button BuHome;

    @FXML
    private Button BuNfsLauncher;

    @FXML
    private Button BuStatsGame;

    @FXML
    private ImageView ImComptePhoto;

    @FXML
    private ImageView ImNucleonLogo;

    @FXML
    private Label LbCompteMail;

    @FXML
    private Label LbTitleLeftMenu;

    @FXML
    private StackPane content;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("db_home.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void home(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("db_home.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    public void games(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("db_games.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    public void statistics(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("db_statistics.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    public void deviceInfo(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("db_device.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }
}

package com.project.nucleon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class MainController {
    public void confirmLicenceKey(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Licence validé");
        alert.setHeaderText("Merci d'avoir acheté une licence");
        alert.setContentText("Hésitez pas à rejoindre le serveur discord pour être au courant des mises à jours et dernières news");

        alert.showAndWait();
    }
}
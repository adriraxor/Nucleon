package com.project.nucleon;

import com.almasb.fxgl.entity.action.Action;
import com.project.nucleon.Database.NucleonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenceController {

    @FXML
    private TextField TfLicenceKey;

    private boolean isValid = false;

    public void confirmLicenceKey(ActionEvent actionEvent) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if(NucleonDAO.getInstance().isActive()){

            ResultSet resultSet = NucleonDAO.getInstance().selectRequest("SELECT * FROM Licence WHERE licence_key='" + TfLicenceKey.getText() + "'");

            while(resultSet.next()){
                alert.setTitle("Licence valide");
                alert.setHeaderText("Merci d'avoir acheté une licence");
                alert.setContentText("Hésitez pas à rejoindre le serveur discord pour être au courant des mises à jours et dernières news");

                alert.showAndWait();
                isValid = true;
            }

            if(!isValid){
                alert.setTitle("Licence invalide");
                alert.setHeaderText("Achetez une licence auprès de Adriraxor#3458 sur discord.");
                alert.setContentText("N'oubliez que Adriraxor#3458 est le seul et l'unique qui peut fournir des clés. Ayez à l'esprit que nous je ne démarche personne.");

                alert.showAndWait();
            } else {
                goToDashboard(actionEvent);
            }
        }
    }

    private void goToDashboard(ActionEvent event) throws IOException {
        Parent licence = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene licenceScene = new Scene(licence);
        appStage.setResizable(false);

        appStage.setScene(licenceScene);
        appStage.show();

    }
}
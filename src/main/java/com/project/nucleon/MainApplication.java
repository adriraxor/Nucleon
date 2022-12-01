package com.project.nucleon;

import com.project.nucleon.Database.NucleonDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 200);
        stage.setTitle("Nucleon");
        stage.setScene(scene);
        stage.show();

        if(NucleonDAO.getInstance() != null) {

            System.out.println("Connexion à la base de données réussi");
        } else {
            System.out.println("Connexion échoué");
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
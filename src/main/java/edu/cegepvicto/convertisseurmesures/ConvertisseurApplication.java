package edu.cegepvicto.convertisseurmesures;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConvertisseurApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConvertisseurApplication.class.getResource("vue-convertisseur.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 600);
        stage.setTitle("Convertisseur");
        stage.setScene(scene);
        stage.show();
    }
}

package test;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Activite/AfficherActivite.fxml"));
            Parent root = loader.load();

            root.getStylesheets().add(getClass().getResource("/style/Activite/Activite.css").toExternalForm());


            //set scene et définir the stage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            //titre dyel fenetre
            primaryStage.setTitle("liste des Activites ");

            //affichage fenetre
            primaryStage.show();



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
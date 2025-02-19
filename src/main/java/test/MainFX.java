package test;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD
=======
import javafx.scene.layout.AnchorPane;
>>>>>>> 90781c80dac62ee0115c83aef63989fd27e397da
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

<<<<<<< HEAD
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Activite/AfficherActivite.fxml"));
            Parent root = loader.load();

            root.getStylesheets().add(getClass().getResource("/style/Activite/Activite.css").toExternalForm());
=======
            /*
            // load the file de fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterPatient.fxml"));
            Parent root = loader.load();

            root.getStylesheets().add(getClass().getResource("/styleSheets/ajouterPatient.css").toExternalForm());
>>>>>>> 90781c80dac62ee0115c83aef63989fd27e397da


            //set scene et définir the stage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            //titre dyel fenetre
<<<<<<< HEAD
            primaryStage.setTitle("liste des Activites ");

            //affichage fenetre
=======
            primaryStage.setTitle("Ajouter Patient");

            //affichage said fenetre
            primaryStage.show();


             */
            // load the file de fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SuiviFXML/ajouterDossier.fxml"));
            Parent root = loader.load();

            root.getStylesheets().add(getClass().getResource("/style/ajouterPatient.css").toExternalForm());


            //set scene et définir the stage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            //titre dyel fenetre
            primaryStage.setTitle("Ajouter Dossier Medical");

            //affichage said fenetre
>>>>>>> 90781c80dac62ee0115c83aef63989fd27e397da
            primaryStage.show();



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


<<<<<<< HEAD
}
=======
}
>>>>>>> 90781c80dac62ee0115c83aef63989fd27e397da

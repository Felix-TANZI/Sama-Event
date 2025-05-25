package javafxtest.event;

/*
   Projet : Sama-Event
   @Auteur : NZIKO Felix Andre
   Email : tanzifelix@gmail.com
   version : beta 1.0

   Instagram : felix.tanzi
   GitHub : Felix-TANZI
   Linkedin : Felix TANZI
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private TableColumn<?, ?> actionsTableColonne;

    @FXML
    private Button boutonAjoutEvenement;

    @FXML
    private Button boutonEvenements;

    @FXML
    private Button boutonNotifications;

    @FXML
    private Button boutonOrganisateurs;

    @FXML
    private Button boutonParticipants;

    @FXML
    private Button boutonRapports;

    @FXML
    private Button boutonTableau;

    @FXML
    private TableColumn<?, ?> dateTableColonne;

    @FXML
    private TableColumn<?, ?> lieuTableColonne;

    @FXML
    private TableColumn<?, ?> nomTableColonne;

    @FXML
    private TableView<?> tableViewEvenement;

    @FXML
    void AjoutEvenement(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutEvenement.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Ajouter un participant");
            stage.setScene(new Scene(root));
            stage.setResizable(false); // optionnel
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void Notifications(ActionEvent event) {

    }

    @FXML
    void Organisateurs(ActionEvent event) {

    }

    @FXML
    void evenements(ActionEvent event) {

    }

    @FXML
    void participants(ActionEvent event) {

    }

    @FXML
    void rapports(ActionEvent event) {

    }

    @FXML
    void tableau(ActionEvent event) {

    }

}

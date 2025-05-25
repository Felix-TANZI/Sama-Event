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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class OrganisateurController {

    @FXML
    private Button boutonAjoutOrganisateur;

    @FXML
    private Button boutonEvenement;

    @FXML
    private Button boutonNotification;

    @FXML
    private Button boutonOrganisateurs;

    @FXML
    private Button boutonParticipants;

    @FXML
    private Button boutonRapport;

    @FXML
    private Button boutonTableau;

    @FXML
    private ComboBox<?> comBoxSelectionEvenement;

    @FXML
    private TextField textFieldRechercher;

    @FXML
    void Organisateurs(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrganisateurUI.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) boutonOrganisateurs.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Organisateurs");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void ajoutOrganisateur(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutOrganisateurUI.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Ajouter un organisateur");
            stage.setScene(new Scene(root));
            stage.setResizable(false); // optionnel
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void evenement(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) boutonEvenement.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Événements");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void notification(ActionEvent event) {

    }

    @FXML
    void participants(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ParticipantUI.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) boutonParticipants.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Participants");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void rapport(ActionEvent event) {

    }

    @FXML
    void tableau(ActionEvent event) {

    }

}

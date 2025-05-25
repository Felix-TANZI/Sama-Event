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


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ParticipantController {

    @FXML
    private Button boutonOrganisateurs;

    @FXML
    private Button boutonAjoutParticipant;

    @FXML
    private Button boutonEvenement;

    @FXML
    private Button boutonNotification;

    @FXML
    private Button boutonParticipants;

    @FXML
    private Button boutonRapport;

    @FXML
    private Button boutonTableau;

    @FXML
    private ComboBox<?> comboBoxEvenement;

    @FXML
    private TableColumn<?, ?> emailTableColonne;

    @FXML
    private TableColumn<?, ?> idTableColonne;

    @FXML
    private TableColumn<?, ?> nomTableColonne;

    @FXML
    private TableView<?> tableview;

    @FXML
    private TextField textfieldRecherche;

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

    @FXML
    void AjoutParticipant(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutParticipantUI.fxml"));
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
}

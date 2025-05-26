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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Evenement> tableViewEvenement;

    @FXML
    public void initialize() {
        // 1. Configurer les colonnes de la TableView
        nomTableColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dateTableColonne.setCellValueFactory(new PropertyValueFactory<>("date"));
        lieuTableColonne.setCellValueFactory(new PropertyValueFactory<>("lieu"));

        // 2. Charger les données au démarrage
        chargerDonnees();
    }

    private void chargerDonnees() {
        try {
            // 3. Charger depuis le fichier JSON
            GestionEvenements.getInstance().chargement("evenements.json");

            // 4. Remplir la TableView
            ObservableList<Evenement> events = FXCollections.observableArrayList(
                    GestionEvenements.getInstance().getEvenements().values()
            );
            tableViewEvenement.setItems(events);

        } catch (Exception e) {
            System.err.println("Erreur lors du chargement: " + e.getMessage());

        }
    }

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
    void evenements(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) boutonEvenements.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Evenement");
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
    void rapports(ActionEvent event) {

    }

    @FXML
    void tableau(ActionEvent event) {

    }

}

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
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class AjoutEvenementController {

    @FXML
    private Button Bouton_Ajouter;

    @FXML
    private TextField LieuEvenement;

    @FXML
    private Button bouton_Annuler;

    @FXML
    private Button bouton_Enregistrer;

    @FXML
    private Button bouton_Modifier;

    @FXML
    private Button bouton_Supprimer;

    @FXML
    private TextField capMax;

    @FXML
    private ComboBox<?> comboBoxGenre;

    @FXML
    private ComboBox<String> comboBoxType;

    @FXML
    private VBox concertFields;

    @FXML
    private VBox conferenceFields;

    @FXML
    private DatePicker dateEvenement;

    @FXML
    private ComboBox<?> HeureEvenement;

    @FXML
    private TableColumn<?, ?> idTableColonne;

    @FXML
    private TextField nomArtiste;

    @FXML
    private TextField nomEvenement;

    @FXML
    private TableColumn<?, ?> nomTableColonne;

    @FXML
    private TableColumn<?, ?> specialiteTableColonne;

    @FXML
    private TableView<?> tableViewIntervenant;

    @FXML
    private TextField themeConference;

    @FXML
    void ajouter(ActionEvent event) {

    }

    // Lorque le bouton annuler est selectionne lorsqu'on a saisi un des champs, une boite de dialogue s'ouvre automatiquement pour avoir la confirmaton si on ferme ou pas.
    @FXML
    void annuler(ActionEvent event) {
        // Verification des champs remplis
        boolean champsRemplis = !nomEvenement.getText().isEmpty()
                || (dateEvenement.getValue() != null)
                || !LieuEvenement.getText().isEmpty()
                || !capMax.getText().isEmpty()
                || (comboBoxType.getValue() != null);

        if (champsRemplis) {
            // Affichage d'une boite de dialogue pour une confirmation si des champs sont remplis
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation d'annulation");
            confirmation.setHeaderText("Données non enregistrées");
            confirmation.setContentText("Êtes-vous sûr de vouloir annuler ? Les données saisies seront perdues.");

            ButtonType result = confirmation.showAndWait().orElse(ButtonType.CANCEL);

            if (result != ButtonType.OK) {
                return; // Ne ferme pas si l'utilisateur annule
            }
        }

        // Fermeture de la fenêtre
        Stage stage = (Stage) bouton_Annuler.getScene().getWindow();
        stage.close();
    }

    @FXML
    void enregistrer(ActionEvent event) {
        try {
            // Créer l'événement selon le type sélectionné
            Evenement nouvelEvenement;
            String type = comboBoxType.getValue();

            if ("Concert".equals(type)) {
                nouvelEvenement = new Concert(
                        generateId(), // Méthode à implémenter pour générer un ID unique
                        nomEvenement.getText(),
                        LocalDateTime.of(dateEvenement.getValue(), LocalTime.now()),
                        LieuEvenement.getText(),
                        Integer.parseInt(capMax.getText()),
                        nomArtiste.getText(),
                        comboBoxGenre.getValue().toString()
                );
            } else {
                nouvelEvenement = new Conference(
                        generateId(),
                        nomEvenement.getText(),
                        LocalDateTime.of(dateEvenement.getValue(), LocalTime.now()),
                        LieuEvenement.getText(),
                        Integer.parseInt(capMax.getText()),
                        themeConference.getText(),
                        new ArrayList<>() // Liste vide d'intervenants
                );
            }

            // Ajouter à la gestion
            GestionEvenements.getInstance().AjoutEvenement(nouvelEvenement);

            // Sauvegarder
            GestionEvenements.getInstance().sauvegarde("evenements.json");

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Événement enregistré");

            // Fermer la fenêtre
            ((Stage) bouton_Enregistrer.getScene().getWindow()).close();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
        }
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    @FXML
    void modifier(ActionEvent event) {

    }

    @FXML
    void supprimer(ActionEvent event) {

    }

    @FXML
    void charger(ActionEvent event) {
        try {
            // Charger depuis un fichier JSON
            GestionEvenements.getInstance().chargement("evenements.json");

            // Afficher un message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chargement réussi");
            alert.setHeaderText(null);
            alert.setContentText("Les événements ont été chargés avec succès!");
            alert.showAndWait();
        } catch (Exception e) {
            // Gérer les erreurs
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de chargement");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors du chargement: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void initialize() {
        // Masquer les deux sections au démarrage
        conferenceFields.setVisible(false);
        concertFields.setVisible(false);

        // Chargement d'informations supplementaire en focntion de si l'utilisateur choisir un concert ou une conference comme evenement.
        comboBoxType.setOnAction(event -> {
            String selectedType = comboBoxType.getValue();

            if ("Conférence".equals(selectedType)) {
                conferenceFields.setVisible(true);
                concertFields.setVisible(false);
            } else if ("Concert".equals(selectedType)) {
                concertFields.setVisible(true);
                conferenceFields.setVisible(false);
            } else {
                // Aucun des deux selectionner, rien est affiche.
                concertFields.setVisible(false);
                conferenceFields.setVisible(false);
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

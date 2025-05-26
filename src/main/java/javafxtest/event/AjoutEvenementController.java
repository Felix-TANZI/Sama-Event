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

    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void enregistrer(ActionEvent event) {
        try {
            // Sauvegarder dans un fichier JSON
            GestionEvenements.getInstance().sauvegarde("evenements.json");

            // Afficher un message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sauvegarde réussie");
            alert.setHeaderText(null);
            alert.setContentText("Les événements ont été sauvegardés avec succès!");
            alert.showAndWait();
        } catch (Exception e) {
            // Gérer les erreurs
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sauvegarde");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de la sauvegarde: " + e.getMessage());
            alert.showAndWait();
        }

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

}

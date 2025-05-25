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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    }

    @FXML
    void modifier(ActionEvent event) {

    }

    @FXML
    void supprimer(ActionEvent event) {

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

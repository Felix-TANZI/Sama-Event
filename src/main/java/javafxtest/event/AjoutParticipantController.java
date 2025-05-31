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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.UUID;

public class AjoutParticipantController {

    @FXML
    private Button boutonAnnuler;

    @FXML
    private Button boutonInscrire;

    @FXML
    private CheckBox checkBoxNotif;

    @FXML
    private ComboBox<String> comboBoxSelectEvnent;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField idTextFiel;

    @FXML
    private TextField nomTextFiiel;

    @FXML
    private RadioButton organisateurRadioBouton;

    @FXML
    private RadioButton participantRadioBouton;

    @FXML
    private ToggleGroup typeParticipant;

    @FXML
    void bouton_annuler(ActionEvent event) {

    }

    @FXML
    void bouton_inscrire(ActionEvent event) {

    }

    @FXML
    void notif_checkBox(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        // Configuration des RadioButtons
        typeParticipant = new ToggleGroup();
        participantRadioBouton.setToggleGroup(typeParticipant);
        organisateurRadioBouton.setToggleGroup(typeParticipant);
        participantRadioBouton.setSelected(true);

        // Charger les événements disponibles
        chargementEvenements();

        // Générer un ID automatiquement
        genererNouvelId();
    }

    private void chargementEvenements() {
        try {
            var evenements = GestionEvenements.getInstance().getEvenements().values();
            var nomsEvenements = evenements.stream()
                    .map(e -> e.getNom() + " (" + e.getLieu() + ")")
                    .toList();

            comboBoxSelectEvnent.setItems(FXCollections.observableArrayList(nomsEvenements));
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des événements: " + e.getMessage());
        }
    }

    private void genererNouvelId() {
        String prefix = organisateurRadioBouton.isSelected() ? "ORG" : "P";
        String nouvelId = prefix + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        idTextFiel.setText(nouvelId);
    }

    @FXML
    void inscrire(ActionEvent event) {
        try {
            // Validation des champs
            if (!validerChamps()) return;

            // Vérifier les doublons
            var gestion = GestionEvenements.getInstance();
            if (gestion.participantExiste(idTextFiel.getText())) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Un participant avec cet ID existe déjà!");
                return;
            }

            if (gestion.emailDejaUtilise(emailTextField.getText())) {
                showAlert(Alert.AlertType.WARNING, "Attention", "Cet email est déjà utilisé!");
                return;
            }

            // Créer le participant
            Participant nouveauParticipant;
            if (organisateurRadioBouton.isSelected()) {
                nouveauParticipant = new Organisateur(
                        idTextFiel.getText(),
                        nomTextFiiel.getText(),
                        emailTextField.getText()
                );
            } else {
                nouveauParticipant = new Participant(
                        idTextFiel.getText(),
                        nomTextFiiel.getText(),
                        emailTextField.getText()
                );
            }

            // Ajouter le participant
            gestion.ajouterParticipant(nouveauParticipant);

            // Inscrire à l'événement si sélectionné
            if (comboBoxSelectEvnent.getValue() != null) {
                inscrireEvenement(nouveauParticipant);
            }

            // Sauvegarder tout
            gestion.sauvegarde("evenements.json");

            showAlert(Alert.AlertType.INFORMATION, "Succès",
                    "Participant " + (organisateurRadioBouton.isSelected() ? "(Organisateur) " : "") +
                            "inscrit avec succès!");

            fermerFenetre();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
        }
    }

    private boolean validerChamps() {
        if (idTextFiel.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation", "L'ID est requis!");
            return false;
        }
        if (nomTextFiiel.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Le nom est requis!");
            return false;
        }
        if (emailTextField.getText().trim().isEmpty() || !emailTextField.getText().contains("@")) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Email valide requis!");
            return false;
        }
        return true;
    }

    private void inscrireEvenement(Participant participant) {
        try {
            String evenementSelectionne = comboBoxSelectEvnent.getValue();
            if (evenementSelectionne != null) {
                String nomEvenement = evenementSelectionne.split(" \\(")[0];

                var evenement = GestionEvenements.getInstance().getEvenements().values()
                        .stream()
                        .filter(e -> e.getNom().equals(nomEvenement))
                        .findFirst();

                if (evenement.isPresent()) {
                    if (evenement.get().AjoutParticipant(participant)) {
                        System.out.println("Participant inscrit à l'événement: " + nomEvenement);
                    } else {
                        showAlert(Alert.AlertType.WARNING, "Attention",
                                "Capacité maximale atteinte pour cet événement!");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'inscription à l'événement: " + e.getMessage());
        }
    }

    @FXML void annuler(ActionEvent event) { fermerFenetre(); }

    @FXML void genererNouvelIdAction(ActionEvent event) { genererNouvelId(); }

    //methode pour la fermeture de la fenetre
    private void fermerFenetre() {
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}


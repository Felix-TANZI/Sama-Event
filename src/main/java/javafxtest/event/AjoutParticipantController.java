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
        fermerFenetre();

    }

    @FXML
    void bouton_inscrire(ActionEvent event) {
        inscrire(event);

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

        typeParticipant.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                genererNouvelId();
            }
        });

        // Chargement événements disponibles
        chargementEvenements();

        // Généreration ID automatiquement
        genererNouvelId();
    }

    private void chargementEvenements() {
        try {
            // Charger d'abord les données si nécessaire
            var gestion = GestionEvenements.getInstance();

            // Si pas d'événements en mémoire, essayer de charger
            if (gestion.getEvenements().isEmpty()) {
                try {
                    gestion.chargement("evenements.json");
                } catch (Exception e) {
                    System.out.println("Aucun fichier d'événements trouvé ou erreur de chargement");
                }
            }

            var evenements = gestion.getEvenements().values();
            var nomsEvenements = evenements.stream()
                    .map(e -> e.getNom() + " (" + e.getLieu() + ") - ID: " + e.getIdEvenement())
                    .toList();

            comboBoxSelectEvnent.setItems(FXCollections.observableArrayList(nomsEvenements));

            if (nomsEvenements.isEmpty()) {
                comboBoxSelectEvnent.setPromptText("Aucun événement disponible");
                comboBoxSelectEvnent.setDisable(true);
            }

        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des événements: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.WARNING, "Attention",
                    "Impossible de charger la liste des événements. Vous pouvez créer un participant sans l'inscrire à un événement.");
        }
    }

    private void genererNouvelId() {
        try {
            String prefix = (organisateurRadioBouton != null && organisateurRadioBouton.isSelected()) ? "ORG-" : "P-";
            String nouvelId = prefix + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            idTextFiel.setText(nouvelId);
        } catch (Exception e) {
            System.err.println("Erreur lors de la génération de l'ID: " + e.getMessage());
            idTextFiel.setText("ID-" + System.currentTimeMillis());
        }
    }

    @FXML
    void inscrire(ActionEvent event) {
        try {
            if (!validerChamps()) return;

            var gestion = GestionEvenements.getInstance();

            if (gestion.participantExiste(idTextFiel.getText().trim())) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Un participant avec cet ID existe déjà!");
                return;
            }

            if (gestion.emailDejaUtilise(emailTextField.getText().trim())) {
                showAlert(Alert.AlertType.WARNING, "Attention", "Cet email est déjà utilisé!");
                return;
            }

            Participant nouveauParticipant = creerParticipant();
            if (nouveauParticipant == null) return;

            gestion.ajouterParticipant(nouveauParticipant);

            boolean inscritAEvenement = false;
            if (comboBoxSelectEvnent.getValue() != null && !comboBoxSelectEvnent.getValue().isEmpty()) {
                inscrireEvenement(nouveauParticipant);
            }

            gestion.sauvegarde("evenements.json");

            String message = "Participant " +
                    (organisateurRadioBouton.isSelected() ? "(Organisateur) " : "") +
                    "inscrit avec succès!";

            if (inscritAEvenement) {
                message += "\nInscrit à l'événement sélectionné.";
            }

            showAlert(Alert.AlertType.INFORMATION, "Succès", message);
            fermerFenetre();

        } catch (EvenementDejaExistantException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur lors de l'inscription: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur inattendue: " + e.getMessage());
        }
    }

    private Participant creerParticipant() {
        try {
            String id = idTextFiel.getText().trim();
            String nom = nomTextFiiel.getText().trim();
            String email = emailTextField.getText().trim();

            if (organisateurRadioBouton.isSelected()) {
                return new Organisateur(id, nom, email);
            } else {
                return new Participant(id, nom, email);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du participant: " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de créer le participant: " + e.getMessage());
            return null;
        }
    }


    private boolean validerChamps() {

        if (idTextFiel.getText() == null || idTextFiel.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation", "L'ID est requis!");
            idTextFiel.requestFocus();
            return false;
        }

        if (nomTextFiiel.getText() == null || nomTextFiiel.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Le nom est requis!");
            nomTextFiiel.requestFocus();
            return false;
        }

        String email = emailTextField.getText();
        if (email == null || email.trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation", "L'email est requis!");
            emailTextField.requestFocus();
            return false;
        }

        if (!email.contains("@") || !email.contains(".")) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Format d'email invalide!");
            emailTextField.requestFocus();
            return false;
        }

        return true;
    }

    private boolean inscrireEvenement(Participant participant) {
        try {
            String evenementSelectionne = comboBoxSelectEvnent.getValue();
            if (evenementSelectionne == null || evenementSelectionne.isEmpty()) {
                return false;
            }

            String idEvenement = extraireIdEvenement(evenementSelectionne);
            if (idEvenement == null) return false;

            var gestion = GestionEvenements.getInstance();
            var evenement = gestion.RechercheEvenement(idEvenement);

            if (evenement != null) {
                if (evenement.AjoutParticipant(participant)) {
                    System.out.println("Participant inscrit à l'événement: " + evenement.getNom());
                    return true;
                } else {
                    showAlert(Alert.AlertType.WARNING, "Attention",
                            "Capacité maximale atteinte pour cet événement!");
                    return false;
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Événement introuvable!");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'inscription à l'événement: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private String extraireIdEvenement(String evenementAffiche) {
        try {
            // Ici dans la combo box recherche nous afficherons un evenement comme ceci: "Nom (Lieu) - ID: idEvenement"
            int indexId = evenementAffiche.lastIndexOf("ID: ");
            if (indexId != -1) {
                return evenementAffiche.substring(indexId + 4).trim();
            }

            String nomEvenement = evenementAffiche.split(" \\(")[0];
            var evenement = GestionEvenements.getInstance().getEvenements().values()
                    .stream()
                    .filter(e -> e.getNom().equals(nomEvenement))
                    .findFirst();

            return evenement.map(Evenement::getIdEvenement).orElse(null);

        } catch (Exception e) {
            System.err.println("Erreur lors de l'extraction de l'ID événement: " + e.getMessage());
            return null;
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


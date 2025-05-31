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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AjoutOrganisateurController {

    @FXML
    private Button boutonAnnuler;

    @FXML
    private Button boutonInscrire;

    @FXML
    private CheckBox checkBoxNotification;

    @FXML
    private ComboBox<?> comboBoxEvenement;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    void bouton_inscrire(ActionEvent event) {

    }

}


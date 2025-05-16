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

import java.util.ArrayList;
import java.util.List;

public class Organisateur extends Participant{
    private List<Evenement> evenementsOrganises = new ArrayList<>();

    public Organisateur(String idParticipant, String nom, String email) {
        super(idParticipant, nom, email);
    }

    public List<Evenement> getEvenementsOrganises() {
        return evenementsOrganises;
    }

    public void setEvenementsOrganises(List<Evenement> evenementsOrganises) {
        this.evenementsOrganises = evenementsOrganises;
    }
}

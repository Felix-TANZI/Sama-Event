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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName("organisateur")

public class Organisateur extends Participant{
    private List<Evenement> evenementsOrganises;

    // Constructeur par défaut pour Jackson
    public Organisateur() {
        super();
        this.evenementsOrganises = new ArrayList<>();
    }

    // Constructeur pour Jackson avec annotations
    @JsonCreator
    public Organisateur(@JsonProperty("idParticipant") String idParticipant,
                        @JsonProperty("nom") String nom,
                        @JsonProperty("email") String email,
                        @JsonProperty("evenementsOrganises") List<Evenement> evenementsOrganises) {
        super(idParticipant, nom, email);
        this.evenementsOrganises = evenementsOrganises != null ? evenementsOrganises : new ArrayList<>();
    }

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

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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Organisateur.class, name = "organisateur")
})

public class Participant {
    protected String idParticipant;
    protected String nom;
    protected String email;

    // Constructeur par défaut pour Jackson
    public Participant() {
    }

    // Constructeur pour Jackson avec annotations
    @JsonCreator
    public Participant(@JsonProperty("idParticipant") String idParticipant,
                       @JsonProperty("nom") String nom,
                       @JsonProperty("email") String email) {
        this.idParticipant = idParticipant;
        this.nom = nom;
        this.email = email;
    }

    public String getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(String idParticipant) {
        this.idParticipant = idParticipant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

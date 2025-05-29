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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Concert.class, name = "concert"),
        @JsonSubTypes.Type(value = Conference.class, name = "conference")
})

public abstract class Evenement {
    protected String idEvenement;
    protected String nom;
    protected LocalDateTime date;
    protected String lieu;
    protected int capaciteMax;
    protected List<Participant> participants = new ArrayList<>();

    // Constructeur par défaut pour Jackson
    public Evenement() {
        this.participants = new ArrayList<>();
    }


    public Evenement(String idEvenement, String nom, LocalDateTime date, String lieu, int capaciteMax){
        this.idEvenement = idEvenement;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
        this.participants = new ArrayList<>();
    }

    @JsonCreator
    public Evenement(@JsonProperty("idEvenement") String idEvenement,
                     @JsonProperty("nom") String nom,
                     @JsonProperty("date") LocalDateTime date,
                     @JsonProperty("lieu") String lieu,
                     @JsonProperty("capaciteMax") int capaciteMax,
                     @JsonProperty("participants") List<Participant> participants) {
        this.idEvenement = idEvenement;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
        this.participants = participants != null ? participants : new ArrayList<>();
    }

    public boolean AjoutParticipant(Participant participant) {
        if (participants.size() < capaciteMax) {
            participants.add(participant);
            return true;
        }
        return false;
    }

    public void annuler() {
        System.out.println("Evenement annule : " + nom);
    }

    public void AffichageDetails() {
        System.out.println("Evenement : " + nom + ", Lieu : " + lieu + ", Date : " + date);
    }

    public String getIdEvenement() {return idEvenement;}

    public void setIdEvenement(String idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

}

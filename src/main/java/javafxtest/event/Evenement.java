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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Evenement {
    protected String idEvenement;
    protected String nom;
    protected LocalDateTime date;
    protected String lieu;
    protected int capaciteMax;
    protected List<Participant> participants = new ArrayList<>();

    public Evenement(String idEvenement, String nom, LocalDateTime date, String lieu, int capaciteMax){
        this.idEvenement = idEvenement;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
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

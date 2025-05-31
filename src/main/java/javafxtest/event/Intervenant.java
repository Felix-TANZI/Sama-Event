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

public class Intervenant {
    private String id;
    private String nom;
    private String specialite;

    // Constructeur par défaut pour Jackson
    public Intervenant() {
    }

    // Constructeur pour Jackson avec annotations
    @JsonCreator
    public Intervenant(@JsonProperty("id") String id,
                       @JsonProperty("nom") String nom,
                       @JsonProperty("specialite") String specialite) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

}
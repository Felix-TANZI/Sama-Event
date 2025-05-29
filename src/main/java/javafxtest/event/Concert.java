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


import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonTypeName("concert")

public class Concert extends Evenement {
    private String artiste;
    private String genreMusical;

    // Constructeur par défaut pour Jackson
    public Concert() {
        super();
    }

    // Constructeur pour Jackson avec annotations
    @JsonCreator
    public Concert(@JsonProperty("idEvenement") String idEvenement,
                   @JsonProperty("nom") String nom,
                   @JsonProperty("date") LocalDateTime date,
                   @JsonProperty("lieu") String lieu,
                   @JsonProperty("capaciteMax") int capaciteMax,
                   @JsonProperty("artiste") String artiste,
                   @JsonProperty("genreMusical") String genreMusical) {
        super(idEvenement, nom, date, lieu, capaciteMax);
        this.artiste = artiste;
        this.genreMusical = genreMusical;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getGenreMusical() {
        return genreMusical;
    }

    public void setGenreMusical(String genreMusical) {
        this.genreMusical = genreMusical;
    }
}

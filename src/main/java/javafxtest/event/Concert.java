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

public class Concert extends Evenement {
    private String artiste;
    private String genreMusical;

    public Concert(String idEvenement,String nom, LocalDateTime date, String lieu, int capaciteMax, String artiste, String genreMusical) {
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

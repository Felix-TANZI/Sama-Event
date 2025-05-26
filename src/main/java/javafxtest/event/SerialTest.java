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

public class SerialTest {
    public static void main(String[] args) {
        GestionEvenements gestion = GestionEvenements.getInstance();

        // Créer quelques événements
        Concert concert = new Concert("1", "Concert Rock",
                LocalDateTime.now(), "Stade", 1000, "Tanzi Rock", "Rock");

        Conference conf = new Conference("2", "Conférence Tech",
                LocalDateTime.now().plusDays(1), "Salle A", 200, "Technologie", null);

        // Ajouter les événements
        gestion.AjoutEvenement(concert);
        gestion.AjoutEvenement(conf);

        // Sauvegarder
        gestion.sauvegarde("testFelix.json");

        // Charger
        GestionEvenements gestion2 = GestionEvenements.getInstance();
        gestion2.chargement("testFelix.json");

        // Vérifier
        System.out.println("Événements chargés: " + gestion2.getEvenements().size());
    }
}
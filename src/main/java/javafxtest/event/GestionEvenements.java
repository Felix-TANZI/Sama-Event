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

import java.util.HashMap;
import java.util.Map;

public class GestionEvenements {
    private static GestionEvenements instance;
    private Map<String, Evenement> evenements = new HashMap<>();

    public static synchronized GestionEvenements getInstance() {
        if (instance == null) {
            instance = new GestionEvenements();
        }
        return instance;
    }

    public Evenement SupprimeEvenement(String idEvenement){
        return evenements.remove(idEvenement);
    }

    public Evenement RechercheEvenement(String idEvenement){
        return evenements.get(idEvenement);
    }

    public boolean AjoutEvenement(Evenement evenement) {
        if (evenements.containsKey(evenement.getIdEvenement())){
            return false;
        }
        evenements.put(evenement.getIdEvenement(), evenement);
        return true;
    }

}

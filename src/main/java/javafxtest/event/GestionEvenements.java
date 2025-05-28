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


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;

public class GestionEvenements {
    private static GestionEvenements instance;
    private Map<String, Evenement> evenements = new HashMap<>();

    public Map<String, Evenement> getEvenements() {
        return evenements;
    }


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

    public void sauvegarde(String testSerial) {
        try {
            Serialisation.saveToJson(this, testSerial);
            System.out.println("Sauvegarde réussie dans " + testSerial);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde: " + e.getMessage());
        }
    }

    public void chargement(String testSerial) {
        try {
            this.evenements = Serialisation.loadFromJson(testSerial);
            System.out.println("Chargement réussi depuis " + testSerial);
        } catch (IOException e) {
            throw new RuntimeException("Échec du chargement: " + e.getMessage(), e);
        }
    }

}

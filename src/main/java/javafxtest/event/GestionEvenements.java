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
    private Map<String, Participant> participants = new HashMap<>(); // Ajout des participants
    private Map<String, Intervenant> intervenants = new HashMap<>(); // Ajout des intervenants


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
            sauvegardeParticipants("participants.json");
            sauvegardeIntervenants("intervenants.json");
            System.out.println("Sauvegarde réussie dans " + testSerial);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde: " + e.getMessage());
        }
    }

    public void chargement(String testSerial) {
        try {
            this.evenements = Serialisation.loadFromJson(testSerial);
            chargementParticipants("participants.json");
            chargementIntervenants("intervenants.json");
            System.out.println("Chargement réussi depuis " + testSerial);
        } catch (IOException e) {
            throw new RuntimeException("Échec du chargement: " + e.getMessage(), e);
        }
    }

    private void sauvegardeParticipants(String PartiSerial) throws IOException {
        ObjectMapper mapper = createObjectMapper();
        mapper.writerFor(new TypeReference<Map<String, Participant>>() {})
                .writeValue(new File(PartiSerial), participants);
    }

    private void chargementParticipants(String PartiSerial) {
        try {
            File fichier = new File(PartiSerial);
            if (!fichier.exists()) {
                System.out.println("Fichier participants n'existe pas, création d'une nouvelle collection.");
                return;
            }

            ObjectMapper mapper = createObjectMapper();
            this.participants = mapper.readValue(fichier, new TypeReference<Map<String, Participant>>() {});
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des participants: " + e.getMessage());
            this.participants = new HashMap<>();
        }
    }

    private void sauvegardeIntervenants(String InterSerial) throws IOException {
        ObjectMapper mapper = createObjectMapper();
        mapper.writerFor(new TypeReference<Map<String, Intervenant>>() {})
                .writeValue(new File(InterSerial), intervenants);
    }

    private void chargementIntervenants(String InterSerial) {
        try {
            File fichier = new File(InterSerial);
            if (!fichier.exists()) {
                System.out.println("Fichier intervenants n'existe pas, création d'une nouvelle collection.");
                return;
            }

            ObjectMapper mapper = createObjectMapper();
            this.intervenants = mapper.readValue(fichier, new TypeReference<Map<String, Intervenant>>() {});
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des intervenants: " + e.getMessage());
            this.intervenants = new HashMap<>();
        }
    }

    private ObjectMapper createObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    public Map<String, Participant> getParticipants() {
        return participants;
    }

    public boolean ajouterParticipant(Participant participant) throws EvenementDejaExistantException {
        if (participants.containsKey(participant.getIdParticipant())) {
            System.err.println("Participant avec l'ID " + participant.getIdParticipant() + " existe déjà");
            throw new EvenementDejaExistantException();
        }
        participants.put(participant.getIdParticipant(), participant);
        return true;
    }

    public Participant supprimerParticipant(String idParticipant) {
        return participants.remove(idParticipant);
    }

    public Participant rechercherParticipant(String idParticipant) {
        return participants.get(idParticipant);
    }

    public boolean participantExiste(String idParticipant) {
        return participants.containsKey(idParticipant);
    }

    public boolean emailDejaUtilise(String email) {
        return participants.values().stream()
                .anyMatch(p -> p.getEmail().equalsIgnoreCase(email));
    }

    public Map<String, Intervenant> getIntervenants() {
        return intervenants;
    }

    public boolean ajouterIntervenant(Intervenant intervenant) throws EvenementDejaExistantException {
        if (intervenants.containsKey(intervenant.getId())) {
            System.err.println("Intervenant avec l'ID " + intervenant.getId() + " existe déjà");
            throw new EvenementDejaExistantException();
        }
        intervenants.put(intervenant.getId(), intervenant);
        return true;
    }

    public Intervenant supprimerIntervenant(String idIntervenant) {
        return intervenants.remove(idIntervenant);
    }

    public Intervenant rechercherIntervenant(String idIntervenant) {
        return intervenants.get(idIntervenant);
    }


}

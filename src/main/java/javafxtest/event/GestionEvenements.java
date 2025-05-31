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

            // Reconstruire les liens entre événements et participants
            reconstruireLiensParticipants();

            System.out.println("Chargement réussi depuis " + testSerial);
        } catch (IOException e) {
            throw new RuntimeException("Échec du chargement: " + e.getMessage(), e);
        }
    }

    private void reconstruireLiensParticipants() {
        for (Evenement evenement : evenements.values()) {
            if (evenement.getParticipants() != null) {
                // Recréer les références des participants dans l'événement
                for (int i = 0; i < evenement.getParticipants().size(); i++) {
                    Participant p = evenement.getParticipants().get(i);
                    if (p != null && p.getIdParticipant() != null) {
                        // Remplacer par la référence complète du participant
                        Participant participantComplet = participants.get(p.getIdParticipant());
                        if (participantComplet != null) {
                            evenement.getParticipants().set(i, participantComplet);
                        }
                    }
                }
            }
        }
    }


    private void sauvegardeParticipants(String PartiSerial) throws IOException {
        ObjectMapper mapper = createObjectMapper();
        File fichier = new File(PartiSerial);

        if (fichier.getParentFile() != null && !fichier.getParentFile().exists()) {
            fichier.getParentFile().mkdirs();
        }

        mapper.writeValue(fichier, participants);
        System.out.println("Participants sauvegardés : " + participants.size());
    }

    private void chargementParticipants(String PartiSerial) {
        try {
            File fichier = new File(PartiSerial);
            if (!fichier.exists()) {
                System.out.println("Fichier participants n'existe pas, création d'une nouvelle collection.");
                this.participants = new HashMap<>();
                return;
            }

            if (fichier.length() == 0) {
                System.out.println("Fichier participants vide, création d'une nouvelle collection.");
                this.participants = new HashMap<>();
                return;
            }

            ObjectMapper mapper = createObjectMapper();
            this.participants = mapper.readValue(fichier, new TypeReference<Map<String, Participant>>() {});
            System.out.println("Participants chargés : " + participants.size());

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des participants: " + e.getMessage());
            e.printStackTrace();
            this.participants = new HashMap<>();
        }
    }

    private void sauvegardeIntervenants(String InterSerial) throws IOException {
        ObjectMapper mapper = createObjectMapper();
        File fichier = new File(InterSerial);

        // Créer le répertoire parent si nécessaire
        if (fichier.getParentFile() != null && !fichier.getParentFile().exists()) {
            fichier.getParentFile().mkdirs();
        }

        mapper.writeValue(fichier, intervenants);
        System.out.println("Intervenants sauvegardés : " + intervenants.size());
    }


    private void chargementIntervenants(String InterSerial) {
        try {
            File fichier = new File(InterSerial);
            if (!fichier.exists()) {
                System.out.println("Fichier intervenants n'existe pas, création d'une nouvelle collection.");
                this.intervenants = new HashMap<>();
                return;
            }

            if (fichier.length() == 0) {
                System.out.println("Fichier intervenants vide, création d'une nouvelle collection.");
                this.intervenants = new HashMap<>();
                return;
            }

            ObjectMapper mapper = createObjectMapper();
            this.intervenants = mapper.readValue(fichier, new TypeReference<Map<String, Intervenant>>() {});
            System.out.println("Intervenants chargés : " + intervenants.size());

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des intervenants: " + e.getMessage());
            e.printStackTrace();
            this.intervenants = new HashMap<>();
        }
    }


    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);

        return mapper;
    }


    public Map<String, Participant> getParticipants() {
        return participants;
    }

    public boolean ajouterParticipant(Participant participant) throws EvenementDejaExistantException {
        if (participant == null || participant.getIdParticipant() == null) {
            throw new IllegalArgumentException("Participant ou ID participant ne peut pas être null");
        }

        if (participants.containsKey(participant.getIdParticipant())) {
            System.err.println("Participant avec l'ID " + participant.getIdParticipant() + " existe déjà");
            throw new EvenementDejaExistantException();
        }

        participants.put(participant.getIdParticipant(), participant);
        System.out.println("Participant ajouté : " + participant.getNom() + " (ID: " + participant.getIdParticipant() + ")");
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
        if (intervenant == null || intervenant.getId() == null) {
            throw new IllegalArgumentException("Intervenant ou ID intervenant ne peut pas être null");
        }

        if (intervenants.containsKey(intervenant.getId())) {
            System.err.println("Intervenant avec l'ID " + intervenant.getId() + " existe déjà");
            throw new EvenementDejaExistantException();
        }

        intervenants.put(intervenant.getId(), intervenant);
        System.out.println("Intervenant ajouté : " + intervenant.getNom() + " (ID: " + intervenant.getId() + ")");
        return true;
    }

    public Intervenant supprimerIntervenant(String idIntervenant) {
        return intervenants.remove(idIntervenant);
    }

    public Intervenant rechercherIntervenant(String idIntervenant) {
        return intervenants.get(idIntervenant);
    }

    public boolean ajouterIntervenantAConference(String idConference, String idIntervenant) {
        Evenement evenement = evenements.get(idConference);
        Intervenant intervenant = intervenants.get(idIntervenant);

        if (evenement instanceof Conference && intervenant != null) {
            Conference conference = (Conference) evenement;
            if (!conference.getIntervenants().contains(intervenant)) {
                conference.getIntervenants().add(intervenant);
                System.out.println("Intervenant " + intervenant.getNom() + " ajouté à la conférence " + conference.getNom());
                return true;
            }
        }
        return false;
    }

    public boolean retirerIntervenantDeConference(String idConference, String idIntervenant) {
        Evenement evenement = evenements.get(idConference);
        Intervenant intervenant = intervenants.get(idIntervenant);

        if (evenement instanceof Conference && intervenant != null) {
            Conference conference = (Conference) evenement;
            boolean removed = conference.getIntervenants().remove(intervenant);
            if (removed) {
                System.out.println("Intervenant " + intervenant.getNom() + " retiré de la conférence " + conference.getNom());
            }
            return removed;
        }
        return false;
    }


}

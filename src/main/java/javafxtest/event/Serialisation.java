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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Serialisation {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);


    public static void saveToJson(GestionEvenements gestion, String nomFichier) throws IOException {
        File fichier = new File(nomFichier);

        // Créer le répertoire parent si nécessaire
        if (fichier.getParentFile() != null && !fichier.getParentFile().exists()) {
            fichier.getParentFile().mkdirs();
        }

        mapper.writerFor(new TypeReference<Map<String, Evenement>>() {})
                .writeValue(fichier, gestion.getEvenements());
    }

    public static Map<String, Evenement> loadFromJson(String nomFichier) throws IOException {
        File fichier = new File(nomFichier);

        // Retourner une map vide si le fichier n'existe pas
        if (!fichier.exists()) {
            System.out.println("Fichier " + nomFichier + " n'existe pas. Création d'une nouvelle collection.");
            return new HashMap<>();
        }

        // Retourne une map vide si le fichier est vide
        if (fichier.length() == 0) {
            System.out.println("Fichier " + nomFichier + " est vide. Création d'une nouvelle collection.");
            return new HashMap<>();
        }

        try {
            return mapper.readValue(fichier, new TypeReference<Map<String, Evenement>>() {});
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + nomFichier + ": " + e.getMessage());
            // En cas d'erreur, retourner une map vide plutôt que de planter l'application
            return new HashMap<>();
        }
    }
}

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Serialisation {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    public static void saveToJson(GestionEvenements gestion, String filename) throws IOException {
        mapper.writeValue(new File(filename), gestion.getEvenements());
    }

    public static Map<String, Evenement> loadFromJson(String testSerial) throws IOException {
        return mapper.readValue(new File(testSerial),
                new TypeReference<Map<String, Evenement>>() {});
    }
}

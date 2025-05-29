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
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonTypeName("conference")

public class Conference extends Evenement {
    private String theme;
    private List<Intervenant> intervenants;

    // Constructeur par défaut pour Jackson
    public Conference() {
        super();
        this.intervenants = new ArrayList<>();
    }

    // Constructeur pour Jackson avec annotations
    @JsonCreator
    public Conference(@JsonProperty("idEvenement") String idEvenement,
                      @JsonProperty("nom") String nom,
                      @JsonProperty("date") LocalDateTime date,
                      @JsonProperty("lieu") String lieu,
                      @JsonProperty("capaciteMax") int capaciteMax,
                      @JsonProperty("theme") String theme,
                      @JsonProperty("intervenants") List<Intervenant> intervenants) {
        super(idEvenement, nom, date, lieu, capaciteMax);
        this.theme = theme;
        this.intervenants = intervenants != null ? intervenants : new ArrayList<>();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<Intervenant> getIntervenants() {
        return intervenants;
    }

    public void setIntervenants(List<Intervenant> intervenants) {
        this.intervenants = intervenants;
    }
}

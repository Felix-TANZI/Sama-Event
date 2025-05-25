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


public interface EvenementObservable {
    void AjoutObservateur(ParticipantObserver observateur);
    void SupprimerObservateur(ParticipantObserver observer);
    void notifierObservateur(String message);
}

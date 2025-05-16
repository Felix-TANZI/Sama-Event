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

public interface ParticipantObserver {
    void recevoirnotification(String message, EvenementObservable source);
//Ici, 'source' represente l'observateur qui a emis la notification par contre 'message' represente le message de notification.
}

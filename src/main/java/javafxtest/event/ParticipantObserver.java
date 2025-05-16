package javafxtest.event;

public interface ParticipantObserver {
    void recevoirnotification(String message, EvenementObservable source);
//Ici, 'source' represente l'observateur qui a emis la notification par contre 'message' represente le message de notification.
}

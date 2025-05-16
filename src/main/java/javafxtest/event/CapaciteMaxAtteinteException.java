package javafxtest.event;

public class CapaciteMaxAtteinteException extends Exception{
    public  CapaciteMaxAtteinteException(){
        super("La capacite maximale de l'evenement est atteinte. Plus possicible d'ajouter un participant!");
    }
}

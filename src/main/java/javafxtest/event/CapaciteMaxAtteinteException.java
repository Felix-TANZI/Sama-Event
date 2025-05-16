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

public class CapaciteMaxAtteinteException extends Exception{
    public  CapaciteMaxAtteinteException(){
        super("La capacite maximale de l'evenement est atteinte. Plus possicible d'ajouter un participant!");
    }
}

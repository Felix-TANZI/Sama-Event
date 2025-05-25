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


public class EvenementDejaExistantException extends Exception{
    public EvenementDejaExistantException(){
        super("Un evenement existe deja avec cet identifiant. Merci de bien vouloir changer d'identifier!");
    }
}

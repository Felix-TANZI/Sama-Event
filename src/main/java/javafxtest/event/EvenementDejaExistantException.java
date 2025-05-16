package javafxtest.event;

public class EvenementDejaExistantException extends Exception{
    public EvenementDejaExistantException(){
        super("Un evenement existe deja avec cet identifiant. Merci de bien vouloir changer d'identifier!");
    }
}

package my.TNTBuilder.Exceptions;

public class InvalidUnitPurchaseException extends TNTException{
    public InvalidUnitPurchaseException(){
        super();
    }
    public InvalidUnitPurchaseException(String message){
        super(message);
    }
    public InvalidUnitPurchaseException(String message, Exception cause){
        super(message, cause);
    }
}

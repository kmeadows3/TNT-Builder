package my.TNTBuilder.Exceptions;

public class FailedPurchaseException extends TNTException{
    public FailedPurchaseException(){
        super();
    }
    public FailedPurchaseException(String message){
        super(message);
    }
    public FailedPurchaseException(String message, Exception cause){
        super(message, cause);
    }
}

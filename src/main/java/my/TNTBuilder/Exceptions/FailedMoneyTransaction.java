package my.TNTBuilder.Exceptions;

public class FailedMoneyTransaction extends TNTException{
    public FailedMoneyTransaction(){
        super();
    }
    public FailedMoneyTransaction(String message){
        super(message);
    }
    public FailedMoneyTransaction(String message, Exception cause){
        super(message, cause);
    }
}

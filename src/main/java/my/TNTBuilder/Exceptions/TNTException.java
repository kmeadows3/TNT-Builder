package my.TNTBuilder.Exceptions;

public class TNTException extends Exception{
    public TNTException (){
        super();
    }


    public TNTException (String message){
        super(message);
    }

    public TNTException (String message, Exception cause){
        super(message, cause);
    }
}

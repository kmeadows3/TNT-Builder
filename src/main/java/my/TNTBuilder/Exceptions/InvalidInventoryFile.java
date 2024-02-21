package my.TNTBuilder.Exceptions;

public class InvalidInventoryFile extends TNTException{

    public InvalidInventoryFile (){
        super();
    }

    public InvalidInventoryFile (String message){
        super(message);
    }
    public InvalidInventoryFile (String message, Exception cause){
        super(message, cause);
    }
}

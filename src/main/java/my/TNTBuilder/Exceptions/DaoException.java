package my.TNTBuilder.Exceptions;

public class DaoException extends TNTException{
    public DaoException (){
        super();
    }
    public DaoException (String message){
        super(message);
    }
    public DaoException (String message, Exception cause){
        super(message, cause);
    }
}

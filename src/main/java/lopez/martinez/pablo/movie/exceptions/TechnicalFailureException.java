package lopez.martinez.pablo.movie.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class TechnicalFailureException extends Exception{
    public TechnicalFailureException(){
        super("System error");
    }
}

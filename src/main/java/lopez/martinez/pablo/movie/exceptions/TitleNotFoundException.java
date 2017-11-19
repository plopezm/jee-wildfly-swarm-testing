package lopez.martinez.pablo.movie.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class TitleNotFoundException extends Exception {
    public TitleNotFoundException(){
        super("The movie service could not find the given movie");
    }
}

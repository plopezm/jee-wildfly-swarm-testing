package lopez.martinez.pablo.movie.boundary;

import lopez.martinez.pablo.movie.exceptions.TechnicalFailureException;
import lopez.martinez.pablo.movie.exceptions.TitleNotFoundException;

public interface MovieService {
    ParentalControlLevel getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;
}

package lopez.martinez.pablo.parental.boundary;

import lopez.martinez.pablo.movie.boundary.MovieService;
import lopez.martinez.pablo.movie.boundary.ParentalControlLevel;
import lopez.martinez.pablo.movie.exceptions.TechnicalFailureException;
import lopez.martinez.pablo.movie.exceptions.TitleNotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ParentalControlService {

    @Inject
    private MovieService movieService;

    public boolean isUserAllowedToWatchMovie(String movieId, ParentalControlLevel parentalControlLevelPreference) throws TitleNotFoundException {
        try {
            ParentalControlLevel movieParentalControlLevel = this.movieService.getParentalControlLevel(movieId);
            return movieParentalControlLevel.ordinal() >= parentalControlLevelPreference.ordinal();
        }catch (TechnicalFailureException ex){
            return false;
        }
    }
}

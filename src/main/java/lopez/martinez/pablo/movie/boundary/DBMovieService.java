package lopez.martinez.pablo.movie.boundary;

import lopez.martinez.pablo.movie.entity.Movie;
import lopez.martinez.pablo.movie.exceptions.TechnicalFailureException;
import lopez.martinez.pablo.movie.exceptions.TitleNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;

@Stateless
public class DBMovieService implements MovieService{

    @PersistenceContext
    private EntityManager em;

    public ParentalControlLevel getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
        long movieIdToSearch = 0;
        try {
            movieIdToSearch = Long.parseLong(movieId);
        }catch (NumberFormatException e){
            throw new TechnicalFailureException();
        }
        Movie movie = em.find(Movie.class, movieIdToSearch);
        if(movie == null){
            throw new TitleNotFoundException();
        }
        return movie.getParentalControlLevel();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}

package lopez.martinez.pablo.parental.boundary;

import lopez.martinez.pablo.movie.boundary.MovieService;
import lopez.martinez.pablo.movie.boundary.ParentalControlLevel;
import lopez.martinez.pablo.movie.exceptions.TechnicalFailureException;
import lopez.martinez.pablo.movie.exceptions.TitleNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class ParentalControlServiceTest {

    private static final String MOVIE = "1";
    private static final String MOVIE_DOES_NOT_EXIST = "3";
    private static final String MOVIE_ID_NOT_VALID = "NotValid";

    @InjectMocks
    private ParentalControlService underTest;

    @Mock
    private MovieService movieService;

    @Before
    public void setUp() throws TitleNotFoundException {
        MockitoAnnotations.initMocks(this); //Required because @InjectMocks
    }

    @Test
    public void isUserAllowedToWatchMovieTrue() throws Exception {
        //Given
        when(movieService.getParentalControlLevel(MOVIE)).thenReturn(ParentalControlLevel.PG);
        //When
        boolean isAllowed = underTest.isUserAllowedToWatchMovie(MOVIE, ParentalControlLevel.U);
        //Then
        assertTrue(isAllowed);
    }

    @Test
    public void isUserAllowedToWatchMovieEqualIsValid() throws Exception {
        //Given
        when(movieService.getParentalControlLevel(MOVIE)).thenReturn(ParentalControlLevel.PG);
        //When
        boolean isAllowed = underTest.isUserAllowedToWatchMovie(MOVIE, ParentalControlLevel.PG);
        //Then
        assertTrue(isAllowed);
    }

    @Test
    public void isUserAllowedToWatchMovieFalse() throws Exception {
        //Given
        when(movieService.getParentalControlLevel(MOVIE)).thenReturn(ParentalControlLevel.PG);
        //When
        boolean isAllowed = underTest.isUserAllowedToWatchMovie(MOVIE, ParentalControlLevel.PC12);
        //Then
        assertFalse(isAllowed);
    }

    @Test(expected = TitleNotFoundException.class)
    public void isUserAllowedToWatchMovieNotFound() throws Exception {
        //Given
        when(movieService.getParentalControlLevel(MOVIE_DOES_NOT_EXIST)).thenThrow(new TitleNotFoundException());
        //When
        underTest.isUserAllowedToWatchMovie(MOVIE_DOES_NOT_EXIST, ParentalControlLevel.U);
        //Then
    }

    @Test
    public void isUserAllowedToWatchMovieNotValidID() throws Exception {
        //Given
        when(movieService.getParentalControlLevel(MOVIE_ID_NOT_VALID)).thenThrow(new TechnicalFailureException());
        //When
        boolean isAllowed = underTest.isUserAllowedToWatchMovie(MOVIE_ID_NOT_VALID, ParentalControlLevel.U);
        //Then
        assertFalse(isAllowed);
    }
}
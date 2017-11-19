package lopez.martinez.pablo.movie.boundary;

import lopez.martinez.pablo.movie.entity.Movie;
import lopez.martinez.pablo.movie.exceptions.TechnicalFailureException;
import lopez.martinez.pablo.movie.exceptions.TitleNotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class DBMovieServiceTest {

    private final static String WRONG_ID = "NonNumeric";
    private final static String BAD_ID = "123123123";
    private final static String FIRST_ID = "1";
    private static EntityManagerFactory emf;

    @BeforeClass
    public static void init(){
        emf = Persistence.createEntityManagerFactory("testing");
        generateTestCases(emf.createEntityManager());
    }

    private MovieService underTest;

    @Before
    public void setUp(){
        underTest = new DBMovieService();
        ((DBMovieService)underTest).setEm(emf.createEntityManager());
    }

    @Test(expected = TechnicalFailureException.class)
    public void getParentalControlLevelMovieIdNotValid() throws Exception {
        //Given
        //When
        this.underTest.getParentalControlLevel(WRONG_ID);
        //Then
    }

    @Test(expected = TitleNotFoundException.class)
    public void getParentalControlLevelMovieIdNotFound() throws Exception {
        //Given
        //When
        this.underTest.getParentalControlLevel(BAD_ID);
        //Then
    }

    @Test
    public void getParentalControlLevelSuccess() throws Exception {
        //Given
        //When
        ParentalControlLevel parentalControlLevel = this.underTest.getParentalControlLevel(FIRST_ID);
        //Then
        assertEquals(ParentalControlLevel.U, parentalControlLevel);
    }

    private static void generateTestCases(EntityManager em){
        Movie movieParentalU = new Movie(ParentalControlLevel.U);
        Movie movieParentalPG = new Movie(ParentalControlLevel.PG);
        Movie movieParentalPC12 = new Movie(ParentalControlLevel.PC12);
        Movie movieParentalPC15 = new Movie(ParentalControlLevel.PC15);
        Movie movieParentalPC18 = new Movie(ParentalControlLevel.PC18);

        saveTestCase(em, movieParentalU);
        saveTestCase(em, movieParentalPG);
        saveTestCase(em, movieParentalPC12);
        saveTestCase(em, movieParentalPC15);
        saveTestCase(em, movieParentalPC18);
    }

    private static void saveTestCase(EntityManager em, Movie movie){
        em.getTransaction().begin();
        em.persist(movie);
        em.flush();
        em.getTransaction().commit();
    }
}
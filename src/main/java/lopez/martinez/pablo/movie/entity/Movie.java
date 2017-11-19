package lopez.martinez.pablo.movie.entity;

import lopez.martinez.pablo.movie.boundary.ParentalControlLevel;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "parental", nullable = false)
    private ParentalControlLevel parentalControlLevel;

    public Movie(){
    }

    public Movie(ParentalControlLevel parentalControlLevel) {
        this.parentalControlLevel = parentalControlLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ParentalControlLevel getParentalControlLevel() {
        return parentalControlLevel;
    }

    public void setParentalControlLevel(ParentalControlLevel parentalControlLevel) {
        this.parentalControlLevel = parentalControlLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (getId() != movie.getId()) return false;
        return getParentalControlLevel() == movie.getParentalControlLevel();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getParentalControlLevel().hashCode();
        return result;
    }
}

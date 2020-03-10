package es.com.vortech.film.models.movies;

import es.com.vortech.film.entities.MovieEntity;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class MovieTitleYearModelTest {
    private MovieTitleYearModel movieEntity;
    @Test
    public void constructorEmpty(){
        movieEntity = new MovieTitleYearModel();
        assertNotNull(movieEntity);
    }

    @Test
    public void constructor2Fields(){
        movieEntity = new MovieTitleYearModel("anyString()", 2020);
        assertNotNull(movieEntity);
    }

    @Test
    public void getterSetter(){
        movieEntity = new MovieTitleYearModel("anyString()", 2000);
        assertEquals("anyString()", movieEntity.getTitle());
        assertEquals(2000, movieEntity.getYear());
    }
}
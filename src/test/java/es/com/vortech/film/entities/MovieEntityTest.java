package es.com.vortech.film.entities;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class MovieEntityTest {
    private MovieEntity movieEntity;
    @Test
    public void constructorEmpty(){
        movieEntity = new MovieEntity();
        assertNotNull(movieEntity);
    }

    @Test
    public void constructor3Fields(){
        movieEntity = new MovieEntity("title", "genre", 2000, 3);
        assertNotNull(movieEntity);
        assertEquals("title", movieEntity.getTitle());
        assertEquals("genre", movieEntity.getGenre());
        assertEquals(2000, movieEntity.getYear());
        assertEquals(3, movieEntity.getOscarsWonNumber());
        assertEquals(new HashSet<>(), movieEntity.getActors());
        movieEntity.setGenre("newGenre");
        assertEquals("newGenre", movieEntity.getGenre());
        movieEntity.setTitle("newTitle");
        assertEquals("newTitle", movieEntity.getTitle());
        movieEntity.setActors(new HashSet<>());
        assertNotNull(movieEntity.getActors());
        movieEntity.setYear(2000);
        assertEquals(2000, movieEntity.getYear());
    }
}
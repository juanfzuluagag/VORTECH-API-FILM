package es.com.vortech.film.services;

import es.com.vortech.film.entities.ActorEntity;
import es.com.vortech.film.entities.MovieEntity;
import es.com.vortech.film.repositories.ActorRepository;
import es.com.vortech.film.repositories.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void getAllMoviesWithTitleAndYear() {
        //Arrange
        MovieEntity movieEntityMocked = new MovieEntity("mockedTitle", "mockedGener", 2019, 3);
        List<MovieEntity> movieEntities = new ArrayList<>();
        movieEntities.add(movieEntityMocked);

        when(movieRepository.findAll()).thenReturn(movieEntities);

        ResponseEntity<Object> responseEntity = movieService.getAllMoviesWithTitleAndYear();

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    public void createNewMovieWithActorsIdNull() {
        ResponseEntity<Object> responseEntity = movieService.createNewMovie(null, new MovieEntity());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void createNewMovieWithActorsIdEmpty() {
        int[] actorId = {};
        ResponseEntity<Object> responseEntity = movieService.createNewMovie(actorId, new MovieEntity());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void createNewMovieSucceess() {
        int[] actorId = {1};

        ActorEntity actorEntityMocked = new ActorEntity(1, "mockedName", "anyString()", new HashSet<MovieEntity>());
        MovieEntity movieEntityMocked = new MovieEntity(1, "mockedTitle", "mockedGener", 2019, 3, new HashSet<ActorEntity>());

        when(actorRepository.findById(1)).thenReturn(actorEntityMocked);

        when(movieRepository.save(movieEntityMocked)).thenReturn(movieEntityMocked);
        ResponseEntity<Object> responseEntity = movieService.createNewMovie(actorId, movieEntityMocked);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void createNewMovieWhenactorsIdNotExist() {
        int[] actorId = {2};

        ActorEntity actorEntityMocked = new ActorEntity(1, "mockedName", "anyString()", new HashSet<MovieEntity>());
        MovieEntity movieEntityMocked = new MovieEntity(1, "mockedTitle", "mockedGener", 2019, 3, new HashSet<ActorEntity>());

        when(actorRepository.findById(1)).thenReturn(actorEntityMocked);

        when(movieRepository.save(movieEntityMocked)).thenReturn(movieEntityMocked);
        ResponseEntity<Object> responseEntity = movieService.createNewMovie(actorId, movieEntityMocked);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void getMovieById() {
        int movieId = 1;
        MovieEntity movieEntityMocked = new MovieEntity(1, "mockedTitle", "mockedGener", 2019, 3, new HashSet<ActorEntity>());
        when(movieRepository.findById(movieId)).thenReturn(movieEntityMocked);
        ResponseEntity<Object> responseEntity = movieService.getMovieById(movieId);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    public void getMovieByIdNotFound() {
        int movieId = 1;
        MovieEntity movieEntityMocked = new MovieEntity(1, "mockedTitle", "mockedGener", 2019, 3, new HashSet<ActorEntity>());
        when(movieRepository.findById(movieId)).thenReturn(null);
        ResponseEntity<Object> responseEntity = movieService.getMovieById(movieId);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


}
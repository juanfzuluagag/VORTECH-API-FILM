package es.com.vortech.film.controllers;

import es.com.vortech.film.entities.ActorEntity;
import es.com.vortech.film.entities.MovieEntity;
import es.com.vortech.film.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {
    @Autowired
    private MovieService movieService;
    @PostMapping
    public MovieEntity createNewMovie(@RequestBody MovieEntity newMovieEntity){
        return movieService.testRelation(newMovieEntity);
    }
    @GetMapping
    public List<MovieEntity> getAllMovies(){
        return movieService.findAll();
    }
}

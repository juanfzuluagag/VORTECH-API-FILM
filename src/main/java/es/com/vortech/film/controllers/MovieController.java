package es.com.vortech.film.controllers;

import es.com.vortech.film.entities.ActorEntity;
import es.com.vortech.film.entities.MovieEntity;
import es.com.vortech.film.services.MovieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {
    @Autowired
    private MovieService movieService;
    @PostMapping
    @ApiOperation(value = "Guardar película asociado a una lista de actores YA CREADOS")
    public ResponseEntity createNewMovie(
            @ApiParam(value = "Arreglo de IDs asociados Actores: actorsIds=1,2...", required = true) @RequestParam int[] actorsIds,
            @ApiParam(value = "Objeto tipo Movie, no es necesario ingresar Objetos tipo Actores, con actorsIds se creará la relación automaticamente", required = true) @Valid @RequestBody MovieEntity newMovieEntity){
        return movieService.createNewMovie(actorsIds, newMovieEntity);
    }
    @GetMapping
    public List<MovieEntity> getAllMovies(){
        return movieService.findAll();
    }
}

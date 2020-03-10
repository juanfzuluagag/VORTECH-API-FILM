package es.com.vortech.film.controllers;

import es.com.vortech.film.entities.MovieEntity;
import es.com.vortech.film.services.MovieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @PostMapping
    @ApiOperation(value = "Guardar película asociado a una lista de actores YA CREADOS")
    public ResponseEntity createNewMovie(
            @ApiParam(value = "Arreglo de IDs asociados Actores: actorsIds=1,2...", required = true) @Valid @NotNull int[] actorsIds,
            @ApiParam(value = "Objeto tipo Movie, no es necesario ingresar Objetos tipo Actores, con actorsIds se creará la relación automaticamente", required = true) @Valid @RequestBody MovieEntity newMovieEntity){
        return movieService.createNewMovie(actorsIds, newMovieEntity);
    }
    @GetMapping
    public ResponseEntity<Object> getAllMovies(){
        return movieService.getAllMoviesWithTitleAndYear();
    }

    @GetMapping(path = "/{idMovie}")
    public ResponseEntity<Object> getMovieById(@PathVariable int idMovie){
        return movieService.getMovieById(idMovie);
    }
}

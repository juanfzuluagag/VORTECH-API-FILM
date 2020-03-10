package es.com.vortech.film.controllers;

import es.com.vortech.film.entities.MovieEntity;
import es.com.vortech.film.services.MovieService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Api(value="Gestionar repositorio de Películas")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "OK")
    @ApiOperation(value = "Obtener todos las Películas")
    public ResponseEntity<Object> getAllMovies(){
        return movieService.getAllMoviesWithTitleAndYear();
    }

    @GetMapping(path = "/{idMovie}")
    @ApiOperation(value = "Obtener detalle de una pelicula especifica")
    public ResponseEntity<Object> getMovieById(  @PathVariable int idMovie){
        return movieService.getMovieById(idMovie);
    }

    @PostMapping
    @ApiOperation(value = "Guardar película asociado a una lista de actores que estan creados")
    public ResponseEntity createNewMovie(
            @ApiParam(value = "Arreglo de IDs asociados Actores: actorsIds=1,2...", required = true ,example = "[1,2...N]") @Valid @NotNull int[] actorsIds,
            @ApiParam(value = "Objeto tipo Movie, no es necesario ingresar Objetos tipo Actores, con actorsIds se creará la relación automaticamente", required = true) @Valid @RequestBody MovieEntity newMovieEntity){
        return movieService.createNewMovie(actorsIds, newMovieEntity);
    }


}

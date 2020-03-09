package es.com.vortech.film.services;

import es.com.vortech.film.entities.ActorEntity;
import es.com.vortech.film.entities.MovieEntity;
import es.com.vortech.film.models.response.ErrorModel;
import es.com.vortech.film.models.response.ResponseModel;
import es.com.vortech.film.models.movies.MovieTitleYearModel;
import es.com.vortech.film.repositories.ActorRepository;
import es.com.vortech.film.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    private ResponseModel responseModel;
    private HttpStatus httpStatus;

    public ResponseEntity<Object> getAllMoviesWithTitleAndYear(){
        responseModel = new ResponseModel();
        implementGetAllMoviesWithtitleAndYear();
        return new ResponseEntity<>(responseModel, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Object> createNewMovie(int[] actorsId, MovieEntity newMovieEntity){
        responseModel = new ResponseModel();
        if(actorsId!= null){
            implementCreateNewMovie(actorsId, newMovieEntity);
        }else{
            httpStatus = HttpStatus.BAD_REQUEST;
            responseModel.setData(ErrorModel.builder()
                    .errorMessage("No se encuentra actorsId")
                    .date(LocalDateTime.now())
                    .build());
        }
        return new ResponseEntity<>(responseModel, httpStatus);
    }

    public ResponseEntity<Object> getMovieById(int idMovie) {
        responseModel = new ResponseModel();
        implementGetMovieById(idMovie);
        return new ResponseEntity<>(responseModel, httpStatus);
    }

    private void implementGetMovieById(int idMovie){
        MovieEntity movieEntity = movieRepository.findById(idMovie);
        if(movieEntity != null){
            httpStatus = HttpStatus.FOUND;
            responseModel.setData(movieEntity);
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
            responseModel.setData(ErrorModel.builder()
                    .errorMessage("Película no encontrad: " + idMovie)
                    .date(LocalDateTime.now())
                    .build());
        }
    }

    private void implementGetAllMoviesWithtitleAndYear(){
        List<MovieTitleYearModel> movieEntities = convertFromEntityToDTO();
        if(movieEntities.isEmpty()){
            httpStatus = HttpStatus.NOT_FOUND;
        }else {
            httpStatus = HttpStatus.ACCEPTED;
        }
        responseModel.setData(movieEntities);
    }

    private List<MovieTitleYearModel> convertFromEntityToDTO(){
        return movieRepository.findAll().stream()
                    .map(this::map)
                    .collect(Collectors.toList());
    }

    private MovieTitleYearModel map(MovieEntity movieEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movieEntity, MovieTitleYearModel.class);
    }

    private void implementCreateNewMovie(int[] actorsId, MovieEntity movieEntity){
        if(actorsId.length != 0){
            if(validateAllActors(actorsId).isEmpty()){
                responseModel.setData(insertActorsOnMovie(actorsId, saveMovieEntity(movieEntity)));
            }
        }else{
            httpStatus = HttpStatus.BAD_REQUEST;
            responseModel.setData(ErrorModel.builder()
                    .errorMessage("ActorsId no puede estar vacio")
                    .date(LocalDateTime.now())
                    .build());
        }

    }

    private MovieEntity saveMovieEntity(MovieEntity movieEntity){
        return movieRepository.save(movieEntity);
    }

    private MovieEntity insertActorsOnMovie(int[] actorsId, MovieEntity movieEntity){

        Arrays.stream(actorsId).forEach(actorId ->{
            ActorEntity relatedActor = actorRepository.findById(actorId);
            relatedActor.getFilms().add(movieEntity);
            movieEntity.getActors();
            movieEntity.getActors().add(relatedActor);
        });
        return saveMovieEntity(movieEntity);
    }

    private List<Integer> validateAllActors(int[] actorsId){
        List<Integer> badActorsId = new ArrayList<>();
        Arrays.stream(actorsId).forEach(actorId ->{if(actorRepository.findById(actorId) == null ){badActorsId.add(actorId);}});
        if(!badActorsId.isEmpty()){
            responseModel.setData(ErrorModel.builder()
                            .errorMessage("Veriicar los IDs de actores enviados: " + badActorsId.toString())
                            .date(LocalDateTime.now())
                            .build());
            httpStatus = HttpStatus.NOT_FOUND;
        }else{
            httpStatus = HttpStatus.FOUND;
        }
        return badActorsId;
    }
}

package es.com.vortech.film.services;

import es.com.vortech.film.entities.ActorEntity;
import es.com.vortech.film.entities.MovieEntity;
import es.com.vortech.film.models.ResponseModel;
import es.com.vortech.film.repositories.ActorRepository;
import es.com.vortech.film.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private ResponseModel responseModel;

    public ResponseEntity<Object> createNewMovie(int[] actorsId, MovieEntity newMovieEntity){
        responseModel = new ResponseModel();
        implementCreateNewMovie(actorsId, newMovieEntity);
        return new ResponseEntity<>(responseModel, HttpStatus.ACCEPTED);
        /*
        ActorEntity chilindrina = actorRepository.findById(19);
        MovieEntity nemo = movieRepository.findById(16);
        System.out.println("******************************");
        System.out.println(nemo.getTitle());
        System.out.println(chilindrina.getNickName());
        System.out.println("******************************");
        nemo.getActors().add(chilindrina);
        chilindrina.getFilms().add(nemo);
        return movieRepository.save(nemo);

         */
    }
    private void implementCreateNewMovie(int[] actorsId, MovieEntity movieEntity){
        if(validateAllActors(actorsId).isEmpty()){
            responseModel.setData(insertActorsOnMovie(actorsId, saveMovieEntity(movieEntity)));
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
        if(!badActorsId.isEmpty()){responseModel.setErrorMessage("Veriicar los IDs de actores enviados: " + badActorsId.toString());}
        return badActorsId;
    }
    public List<ActorEntity> findAllActors(){
        return actorRepository.findAll();
    }

    public List<MovieEntity> findAll() {
        return movieRepository.findAll();
    }
}

package es.com.vortech.film.services;

import es.com.vortech.film.entities.ActorEntity;
import es.com.vortech.film.entities.MovieEntity;
import es.com.vortech.film.repositories.ActorRepository;
import es.com.vortech.film.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;

    public MovieEntity testRelation(MovieEntity newMovieEntity){
        ActorEntity chilindrina = actorRepository.findById(19);
        MovieEntity nemo = movieRepository.findById(16);
        System.out.println("******************************");
        System.out.println(nemo.getTitle());
        System.out.println(chilindrina.getNickName());
        System.out.println("******************************");
        nemo.getActors().add(chilindrina);
        chilindrina.getFilms().add(nemo);
        return movieRepository.save(nemo);
    }

    public List<ActorEntity> findAllActors(){
        return actorRepository.findAll();
    }

    public List<MovieEntity> findAll() {
        return movieRepository.findAll();
    }
}

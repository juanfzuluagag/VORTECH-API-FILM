package es.com.vortech.film.config;

import es.com.vortech.film.repositories.ActorRepository;
import es.com.vortech.film.repositories.MovieRepository;
import es.com.vortech.film.services.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public MovieService createMovieService(MovieRepository movieRepository, ActorRepository actorRepository){
        return new MovieService(movieRepository, actorRepository);
    }
}

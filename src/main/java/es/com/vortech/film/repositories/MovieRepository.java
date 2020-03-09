package es.com.vortech.film.repositories;

import es.com.vortech.film.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    List<MovieEntity> findAll();
    MovieEntity save(MovieEntity movieEntity);
    MovieEntity findById(int id);
}

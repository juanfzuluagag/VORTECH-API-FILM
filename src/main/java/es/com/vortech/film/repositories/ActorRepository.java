package es.com.vortech.film.repositories;

import es.com.vortech.film.entities.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {
    List<ActorEntity> findAll();
    ActorEntity save(ActorEntity actorEntity);
    ActorEntity findById(int id);
}

package es.com.vortech.film.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String title;
    private String genre;
    private int year;
    private int oscarsWonNumber;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "film_data_actors",
            joinColumns = { @JoinColumn(name = "film_film_id") },
            inverseJoinColumns = { @JoinColumn(name = "actor_id") })
    @JsonManagedReference
    private Set<ActorEntity> actors;

    public MovieEntity() {
    }

    public MovieEntity(String title, String genre, int year, int oscarsWonNumber) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.oscarsWonNumber = oscarsWonNumber;
    }

    public MovieEntity(int id, String title, String genre, int year, int oscarsWonNumber, Set<ActorEntity> actors) {
        Id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.oscarsWonNumber = oscarsWonNumber;
        this.actors = actors;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOscarsWonNumber() {
        return oscarsWonNumber;
    }

    public void setOscarsWonNumber(int oscarsWonNumber) {
        this.oscarsWonNumber = oscarsWonNumber;
    }

    public Set<ActorEntity> getActors() {
        return actors;
    }

    public void setActors(Set<ActorEntity> actors) {
        this.actors = actors;
    }
}

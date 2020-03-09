package es.com.vortech.film.entities;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@ApiModel(description = "Detalles de la película")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "ID es autogenerado, tipo entero")
    private int id;
    @ApiModelProperty(notes = "Título es obligatorio")
    private String title;
    @ApiModelProperty(notes = "Género es obligatorio")
    private String genre;
    @ApiModelProperty(notes = "Año es obligatorio. El año de expedición de la pelicula debe ser mayor a 1990")
    @Min(value = 1990, message = "El año de expedición de la pelicula debe ser mayor a 1990")
    private int year;
    @ApiModelProperty(notes = "Numero de Oscars ganaos es obligatorio")
    private int oscarsWonNumber;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "movie_data_actor",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "actor_id") })
    private Set<ActorEntity> actors = new HashSet<>();

    public MovieEntity() {
    }

    public MovieEntity(String title, String genre, int year, int oscarsWonNumber) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.oscarsWonNumber = oscarsWonNumber;
    }

    public MovieEntity(int id, String title, String genre, int year, int oscarsWonNumber, Set<ActorEntity> actors) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.oscarsWonNumber = oscarsWonNumber;
        this.actors = actors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
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

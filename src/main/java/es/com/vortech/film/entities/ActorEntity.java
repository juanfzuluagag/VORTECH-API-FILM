package es.com.vortech.film.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ActorEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String nickName;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "actors")
    @JsonBackReference
    private Set<MovieEntity> films = new HashSet<>();

    public ActorEntity() {
    }

    public ActorEntity(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    public ActorEntity(int id, String name, String nickName, Set<MovieEntity> films) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.films = films;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Set<MovieEntity> getFilms() {
        return films;
    }

    public void setFilms(Set<MovieEntity> films) {
        this.films = films;
    }
}

package es.com.vortech.film.entities;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

public class ActorEntityTest {
    private ActorEntity actorEntity;

    @Test
    public void emptyContruct(){
        actorEntity = new ActorEntity();
        assertNotNull(actorEntity);
    }

    @Test
    public void contruct2Fields(){
        actorEntity = new ActorEntity(anyString(), anyString());
        assertNotNull(actorEntity);
    }

    @Test
    public void contructAllField(){
        String name = "Jhonson";
        String nickName = "TheRock";
        int id = 1;
        actorEntity = new ActorEntity(id, name, nickName, new HashSet<>());
        assertNotNull(actorEntity);
        assertEquals(id, actorEntity.getId());
        assertEquals(name, actorEntity.getName());
        assertEquals(nickName, actorEntity.getNickName());
        assertNotNull(actorEntity.getFilms());
    }

}
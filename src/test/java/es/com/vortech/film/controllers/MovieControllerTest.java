package es.com.vortech.film.controllers;


import es.com.vortech.film.services.MovieService;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

@WebMvcTest
@RunWith(SpringRunner.class)
public class MovieControllerTest {
    @MockBean
    MovieService movieService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllMovies() throws Exception {
        String uri = "/movies";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void getById() throws Exception {
        String uri = "/movies/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
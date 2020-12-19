package com.stackroute.favmovie.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favmovie.controller.FavController;
import com.stackroute.favmovie.exceptions.FavouriteAlreadyFoundException;
import com.stackroute.favmovie.exceptions.FavouriteNotFoundException;
import com.stackroute.favmovie.model.Favmodel;
import com.stackroute.favmovie.service.FavService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FavControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Favmodel favmodel;
    @MockBean
    FavService favService;
    @InjectMocks
    FavController favController;
    private List<Favmodel> allmovies = null;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(favController).build();
        favmodel = new Favmodel();
        favmodel.setFavid("101");
        favmodel.setTitle("drishyam");
        favmodel.setPoster_path("aaaa");
        favmodel.setOverview("blahblah");
        favmodel.setUsername("jyothish");
    }
    
    @Test
    public void createFavouriteSuccess() throws Exception {

        when(favService.createFavourite(favmodel)).thenReturn(favmodel);
        mockMvc.perform(post("/api/fav/addFav")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(favmodel)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }
    

    @Test
    public void deleteFavSuccess() throws Exception {
        when(favService.deleteFav("jyothish")).thenReturn(true);
        mockMvc.perform(delete("/api/fav/101")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    

    @Test
    public void getFavByUserSuccess() throws Exception {
        when(favService.getFavByUser("jyothish")).thenReturn(allmovies);
        mockMvc.perform(get("/api/fav/user/jyothish")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    

    
}

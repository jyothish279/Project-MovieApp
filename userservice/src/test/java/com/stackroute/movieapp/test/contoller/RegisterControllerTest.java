package com.stackroute.movieapp.test.contoller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.any;

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
import com.stackroute.movieapp.controller.RegisterController;
import com.stackroute.movieapp.exceptions.UserAlreadyFoundException;
import com.stackroute.movieapp.model.Register;
import com.stackroute.movieapp.service.RegisterService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RegisterControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Register register;
    @MockBean
    RegisterService registerService;
    @InjectMocks
    RegisterController registerController;
    private List<Register> alluser=null;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
        register = new Register();
        
        register.setUsername("jyothish");
        register.setPassword("jyothish123");
        register.setFirstname("jyothish");
        register.setLastname("sudhakaran");
        register.setLocation("kollam");
    }
    
    @Test
    public void createUserSuccess() throws Exception {

        when(registerService.createUser(register)).thenReturn(register);
        mockMvc.perform(post("/api/register/addUser")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(register)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void createUserFailure() throws Exception {

        when(registerService.createUser(any())).thenThrow(UserAlreadyFoundException.class);
        mockMvc.perform(post("/api/register/addUser")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(register)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    


}





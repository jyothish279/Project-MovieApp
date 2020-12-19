package com.stackroute.movieapp.test.service;




import com.stackroute.movieapp.exceptions.UserAlreadyFoundException;

import com.stackroute.movieapp.model.Register;
import com.stackroute.movieapp.repository.RegisterRepo;
import com.stackroute.movieapp.service.RegisterServiceImpl;

import static org.mockito.Mockito.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;





public class RegisterServiceImplTest {

	@Mock
	RegisterRepo registerRepo;
	
	Register register;
	
	@InjectMocks
	RegisterServiceImpl registerService;
	
	
    List<Register> registerList = null;
    Optional<Register> options;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        register = new Register();
        register.setUsername("jyothish");
        register.setPassword("jyothish123");
        register.setFirstname("jyothish");
        register.setLastname("sudhakaran");
        register.setLocation("kollam");
        registerList=new ArrayList<>();
        registerList.add(register);
        options = Optional.of(register);

}
    @Test
    public void createUserSuccess() throws UserAlreadyFoundException{
   	 when(registerRepo.save((Register) any())).thenReturn(register);
   	 Register usersaved = registerService.createUser(register);
   	 assertEquals(register,usersaved);
   	
   }
    @Test
    public void createUserFailure() throws UserAlreadyFoundException{
   	 when(registerRepo.save((Register) any())).thenReturn(null);
   	Register usersaved = registerService.createUser(register);
   	 assertEquals(register,usersaved);
   	
   }
    

}

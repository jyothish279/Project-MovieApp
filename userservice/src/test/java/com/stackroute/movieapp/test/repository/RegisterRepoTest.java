package com.stackroute.movieapp.test.repository;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.stackroute.movieapp.model.Register;
import com.stackroute.movieapp.repository.RegisterRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase 
public class RegisterRepoTest {
	
	@Autowired
	private RegisterRepo registerRepo;
    private Register register;
    private List<Register> alluser=null;
    
    @Before
    public void setUp() throws Exception {
    	 register = new Register();
    	 
         register.setUsername("jyothish");
         register.setPassword("jyothish123");
         register.setFirstname("jyothish");
         register.setLastname("sudhakaran");
         register.setLocation("kollam");
    }
    
    @After
    public void tearDown() throws Exception {

    	registerRepo.deleteAll();

    }
    
    @Test
    public void createUserTest() {
    	registerRepo.save(register);
        Register fetcheduser = registerRepo.findById("jyothish").get();
        Assert.assertEquals(register.getUsername(), fetcheduser.getUsername());

    	
    }
    


    





}

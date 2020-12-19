package com.stackroute.favmovie.test.repository;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favmovie.model.Favmodel;
import com.stackroute.favmovie.repository.FavRepo;

@RunWith(SpringRunner.class)
@DataMongoTest
public class FavRepoTest {
	
	  @Autowired
	  private FavRepo favRepo;
      private Favmodel favmodel;
      
      @Before
      public void setUp() throws Exception {
          MockitoAnnotations.initMocks(this);

          favmodel = new Favmodel();
          favmodel.setFavid("101");
          favmodel.setTitle("drishyam");
          favmodel.setPoster_path("aaaa");
          favmodel.setOverview("blahblah");
          favmodel.setUsername("jyothish");
      }
      
      @After
      public void tearDown() throws Exception {

    	  favRepo.deleteAll();

      }
      
      @Test(expected = NoSuchElementException.class)
      public void createUserTest() {

    	  favRepo.save(favmodel);
          Favmodel fetcheduser = favRepo.findById("jyothish").get();
          Assert.assertEquals(favmodel.getUsername(), fetcheduser.getUsername());

      }
      
      @Test(expected = NoSuchElementException.class)
      public void deleteFavTest() {
    	  favRepo.save(favmodel);
          Favmodel fetcheduser = favRepo.findById("jyothish").get();
          Assert.assertEquals("jyothish", fetcheduser.getUsername());
          favRepo.delete(fetcheduser);
          fetcheduser = favRepo.findById("jyothish").get();

      }
      
      @Test
      public void getFavByUserTest() {
    	  favRepo.save(favmodel);
    	  List<Favmodel> allmovies = favRepo.findByUsername("jyothish");
    	  Assert.assertEquals(1,allmovies.size());
      }



}

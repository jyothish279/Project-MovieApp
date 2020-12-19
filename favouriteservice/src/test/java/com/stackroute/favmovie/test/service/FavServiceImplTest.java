package com.stackroute.favmovie.test.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.stackroute.favmovie.exceptions.FavouriteAlreadyFoundException;
import com.stackroute.favmovie.exceptions.FavouriteNotFoundException;
import com.stackroute.favmovie.model.Favmodel;
import com.stackroute.favmovie.repository.FavRepo;
import com.stackroute.favmovie.service.FavServiceImpl;


public class FavServiceImplTest {
	
    @Mock
    FavRepo favRepo;


    Favmodel favmodel;

    @InjectMocks
    FavServiceImpl favService;

    List<Favmodel> allmovies = null;
    Optional<Favmodel> options;

    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        favmodel = new Favmodel();
        favmodel.setFavid("101");
        favmodel.setTitle("drishyam");
        favmodel.setPoster_path("aaaa");
        favmodel.setOverview("blahblah");
        favmodel.setUsername("jyothish");

        allmovies = new ArrayList<>();
        allmovies.add(favmodel);

        options = Optional.of(favmodel);
        
       
    }
    
    @Test
    public void createFavouriteSuccess() throws FavouriteAlreadyFoundException {
        when(favRepo.save((Favmodel) any())).thenReturn(favmodel);
        Favmodel movieadded = favService.createFavourite(favmodel);
        assertEquals(favmodel, movieadded);
  	
    }
    
    @Test
    public void createFavouriteFailure() throws FavouriteAlreadyFoundException {
        when(favRepo.save((Favmodel) any())).thenReturn(null);
        Favmodel movieadded = favService.createFavourite(favmodel);
        assertEquals(favmodel, movieadded);

    }
    
    @Test
    public void deleteFavSuccess() throws FavouriteNotFoundException {
    	when(favRepo.findById(favmodel.getFavid())).thenReturn(options);
    	boolean flag = favService.deleteFav(favmodel.getFavid());
    	assertEquals(true, flag);
    }

    
    @Test
    public void getFavByUserSuccess() {
    	when(favRepo.findByUsername(favmodel.getUsername())).thenReturn(allmovies);
    	List<Favmodel> moviebyuser = favService.findAllFavouritesByUsername(favmodel.getUsername());
    	assertEquals(allmovies, moviebyuser);
    	
    }
    
    



}

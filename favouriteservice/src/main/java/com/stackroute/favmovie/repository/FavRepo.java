package com.stackroute.favmovie.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favmovie.model.Favmodel;



@Repository
public interface FavRepo extends MongoRepository<Favmodel,String> {
	
	Favmodel findByUsernameAndTitle(String username,String title);
	List<Favmodel> findByUsername(String username);
}

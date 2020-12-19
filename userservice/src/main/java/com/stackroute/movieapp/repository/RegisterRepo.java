package com.stackroute.movieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.movieapp.model.Register;

@Repository
public interface RegisterRepo extends JpaRepository<Register,String> {
	
	Register findByUsernameAndPassword(String username,String password);

}

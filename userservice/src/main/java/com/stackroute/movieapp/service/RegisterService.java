package com.stackroute.movieapp.service;

import com.stackroute.movieapp.exceptions.UserAlreadyFoundException;
import com.stackroute.movieapp.exceptions.UserNotFoundException;
import com.stackroute.movieapp.model.Register;

public interface RegisterService {
	
	Register createUser(Register register) throws UserAlreadyFoundException;
	Register validateUser(String username,String password) throws UserNotFoundException ;

}

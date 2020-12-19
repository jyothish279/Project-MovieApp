package com.stackroute.movieapp.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.movieapp.exceptions.UserAlreadyFoundException;
import com.stackroute.movieapp.exceptions.UserNotFoundException;
import com.stackroute.movieapp.model.Register;
import com.stackroute.movieapp.repository.RegisterRepo;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	RegisterRepo registerrepo;

	@Override
	public Register createUser(Register usernew) throws UserAlreadyFoundException {

		Optional<Register> result=registerrepo.findById(usernew.getUsername());
		if(result.isPresent())
			throw new UserAlreadyFoundException();
		else
			registerrepo.save(usernew);
		return usernew;
	}

	@Override
	public Register validateUser(String username, String password) throws UserNotFoundException {
		if(username.isEmpty() || password.isEmpty())
		{
			throw new UserNotFoundException();
		} else {
		
		
		Register reguser=registerrepo.findByUsernameAndPassword(username, password);	
		if(reguser==null)
		{
			throw new UserNotFoundException();
		} else {
			System.out.println("user data in service class" + reguser);
			return reguser;
		}
		}
		

	}

}

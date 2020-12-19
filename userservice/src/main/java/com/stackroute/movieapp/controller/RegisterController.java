package com.stackroute.movieapp.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieapp.exceptions.UserAlreadyFoundException;
import com.stackroute.movieapp.exceptions.UserNotFoundException;
import com.stackroute.movieapp.model.Register;
import com.stackroute.movieapp.service.RegisterService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
@RequestMapping("/api/register")
public class RegisterController {
	
	@Autowired
	RegisterService registerservice;

	@PostMapping("/addUser")
	  public ResponseEntity<?> adduser(@RequestBody Register registernew)
	  {
		try {
			System.out.println("request body is " + registernew);
		Register registerresult=registerservice.createUser(registernew)	;
		return new ResponseEntity<Register>(registerresult,HttpStatus.CREATED);
	  }
		catch(UserAlreadyFoundException e) {
			return new ResponseEntity<String>("Username Already exist",HttpStatus.CONFLICT);
		}
	  }
	
	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody Register register)
	{
		try {
		System.out.println("output" + register.getUsername() + register.getPassword());	
		Register registeroutput=registerservice.validateUser(register.getUsername(), register.getPassword());
		if(register.getUsername()==null || register.getPassword()==null) {
			throw new UserNotFoundException();
		}
	    String mytoken=generateToken(registeroutput)	;
	
	    HashMap<String,String> mymap=new HashMap();
	    mymap.put("token",mytoken);
	    return new ResponseEntity<HashMap>(mymap,HttpStatus.ACCEPTED);
	       }
	    catch(Exception e) {
		return new ResponseEntity<String>("Invalid User",HttpStatus.UNAUTHORIZED);
           }	
		}

	public String generateToken(Register obj)
	{
		long expiry=10_00_0000;
		  
		return  Jwts.builder().setSubject(obj.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
		  .setExpiration(new Date(System.currentTimeMillis()+expiry)).signWith(SignatureAlgorithm.HS256, "jwtsecret").compact();
		
		
	}
	
	
}

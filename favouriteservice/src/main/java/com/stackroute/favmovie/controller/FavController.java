package com.stackroute.favmovie.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favmovie.exceptions.FavouriteAlreadyFoundException;
import com.stackroute.favmovie.exceptions.FavouriteNotFoundException;
import com.stackroute.favmovie.model.Favmodel;
import com.stackroute.favmovie.service.FavService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;



@RestController
@CrossOrigin
@RequestMapping("/api/fav")
public class FavController {
	
	@Autowired
	FavService favservice;
	
	@ApiOperation("used to add details of Movies in database")
	@PostMapping("/addFav")
	  public ResponseEntity<?> addfav(@RequestBody Favmodel favnew,HttpSession httpsession)
	  {
		try {
			Favmodel registerresult=favservice.createFavourite(favnew)	;
		return new ResponseEntity<Favmodel>(registerresult,HttpStatus.OK);
	  }
		catch(FavouriteAlreadyFoundException e) {
			return new ResponseEntity<String>("Favourite Already exist",HttpStatus.CONFLICT);
		}
	  }
	
	@ApiOperation(value="delete",response=Iterable.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Successfully Deleted"),
			@ApiResponse(code=404,message="ID Mismatch")
	})
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String favid) {
		try {
			favservice.deleteFav(favid);
			return new ResponseEntity<String>("Successfully Deleted Favourite with id: " + favid, HttpStatus.OK);
		} catch (FavouriteNotFoundException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") String favid) {
		try {
			return new ResponseEntity<Favmodel>(favservice.getFavById(favid), HttpStatus.OK);
		} catch (FavouriteNotFoundException e) {
			return new ResponseEntity<String>("The requested favourite movie does not exist", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/allmovies")
	public ResponseEntity<?> getAllmvovies() {
		try {
			return new ResponseEntity<List<Favmodel>>(favservice.getAllFav(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Favourites not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/user/{uname}")
	public ResponseEntity<?> moviesByUsername(@PathVariable("uname") String username) {
		List<Favmodel> favsbyuser=favservice.getFavByUser(username);
			return new ResponseEntity<List>(favsbyuser,HttpStatus.OK);

	}
	
}

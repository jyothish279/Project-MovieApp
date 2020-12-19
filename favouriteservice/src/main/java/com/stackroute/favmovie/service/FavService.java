	package com.stackroute.favmovie.service;
	
	
	
	import java.util.List;

import com.stackroute.favmovie.exceptions.FavouriteAlreadyFoundException;
	import com.stackroute.favmovie.exceptions.FavouriteNotFoundException;
	import com.stackroute.favmovie.model.Favmodel;
	
	public interface FavService {
		
		Favmodel createFavourite(Favmodel fav) throws FavouriteAlreadyFoundException;
		boolean deleteFav(String favid) throws FavouriteNotFoundException;
		Favmodel getFavById(String favid) throws FavouriteNotFoundException;
		List<Favmodel> getAllFav();
		List<Favmodel> findAllFavouritesByUsername(String username);
		List<Favmodel> getFavByUser(String username);
	}

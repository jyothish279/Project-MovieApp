package com.stackroute.favmovie.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favmovie.exceptions.FavouriteAlreadyFoundException;
import com.stackroute.favmovie.exceptions.FavouriteNotFoundException;
import com.stackroute.favmovie.model.Favmodel;
import com.stackroute.favmovie.repository.FavRepo;

@Service
public class FavServiceImpl implements FavService {
	
	@Autowired
	FavRepo favrepo;
	
	public FavServiceImpl(FavRepo favrepo) {
		this.favrepo = favrepo;
	}

	@Override
	public Favmodel createFavourite(Favmodel fav) throws FavouriteAlreadyFoundException {
		Favmodel favobj=favrepo.findByUsernameAndTitle(fav.getUsername(), fav.getTitle());
		if(favobj!=null)
		{
			throw new FavouriteAlreadyFoundException("Favourite already added");
		}
		else
		{
			favrepo.save(fav);
			return fav;
		}
		
	}

	@Override
	public boolean deleteFav(String favid) throws FavouriteNotFoundException {
		boolean status = false;
		try {
			Favmodel fecthedFav = favrepo.findById(favid).get();
			if (fecthedFav != null) {
				favrepo.delete(fecthedFav);
				status = true;
			}
		} catch (NoSuchElementException exception) {
			throw new FavouriteNotFoundException("Favourite does not exist");
		}
		return status;

	}

	@Override
	public Favmodel getFavById(String favid) throws FavouriteNotFoundException {
		
		Favmodel fecthedFav = favrepo.findById(favid).get();
		if (fecthedFav == null) {
			throw new FavouriteNotFoundException("Favourite does not exist");
		}
		return fecthedFav;
	}

	
	public List<Favmodel> getAllFav() {
		List<Favmodel> allmovies=favrepo.findAll();
		return allmovies;
	}

	@Override
	public List<Favmodel> findAllFavouritesByUsername(String username) {

		return favrepo.findByUsername(username);
	}

	@Override
	public List<Favmodel> getFavByUser(String username) {
		List<Favmodel> allmovies=favrepo.findByUsername(username);
		return allmovies;
	}



	
	


	}



import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Movieres } from './movieres';
import { UserauthService } from './userauth.service';


@Injectable({
  providedIn: 'root'
})
export class AddfavouriteService {

  moviearr : Array<Movieres>=[];
  movieSubject : BehaviorSubject<Array<Movieres>>;

  constructor(private httpcli : HttpClient, private authobj:UserauthService) { }


  addFavourite(favnew : Movieres ):Observable<Movieres> 
  {
    let tok=this.authobj.getMytoken();
    return this.httpcli.post<Movieres>("http://localhost:9093/api/fav/addFav",favnew,
    {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    }
    );

  
  }

  getAllmovies(){
    let tok=this.authobj.getMytoken();
    return this.httpcli.get("http://localhost:9093/api/fav/user/"+sessionStorage.getItem("username"),
    {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    }
    );

  }

  delMovie(id){
    let tok=this.authobj.getMytoken();
    return this.httpcli.delete("http://localhost:9093/api/fav/"+id,
    {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    }
    );
  }

  getMovieById(id){
    let tok=this.authobj.getMytoken();
    return this.httpcli.get("http://localhost:9093/api/fav/"+id,
    {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    }
    );
  }

}

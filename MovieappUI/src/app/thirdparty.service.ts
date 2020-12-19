import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ThirdpartyService {

  constructor(private http : HttpClient) { }

getallmovies()
  {
return this.http.get('https://api.themoviedb.org/3/movie/upcoming?api_key=49cf4dc573ccea959dec1538632b6efa');
  }

}
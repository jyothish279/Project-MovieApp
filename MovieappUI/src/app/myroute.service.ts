import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class MyrouteService {

  constructor(private myroute : Router) { }

  openLogin()
{
  this.myroute.navigate(['login']);
}

openDashboard()
{
  this.myroute.navigate(['dashboard']);
}

openFavourites()
{
  this.myroute.navigate(['favourite']);
}

}

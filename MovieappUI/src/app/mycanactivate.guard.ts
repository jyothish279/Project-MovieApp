import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { MyrouteService } from './myroute.service';
import { UserauthService } from './userauth.service';


@Injectable({
  providedIn: 'root'
})
export class MycanactivateGuard implements CanActivate {

  constructor(private userservice : UserauthService,private rouobj:MyrouteService)
  {

  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
  
      let mytoken=this.userservice.getMytoken();
      if(mytoken==null){
        this.rouobj.openLogin();
        return false;
      } else {
        return true;
      }
    }
  }

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {  Observable } from 'rxjs';
import { Register } from './register';


@Injectable({
  providedIn: 'root'
})
export class RegisterserviceService {

  constructor(private httpcli : HttpClient) { }

  registerUser( registernew : Register) : Observable<Register>
  {
 return this.httpcli.post<Register>("http://localhost:9095/api/register/addUser",registernew);
  }

}

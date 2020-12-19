import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserauthService {



  constructor(private httpcli : HttpClient) { }


authenticateUser(userdet)
{

  return this.httpcli.post('http://localhost:9095/api/register/login',userdet);
}

setMytoken(tok)
{
  sessionStorage.setItem("mytoken",tok);
  localStorage.setItem
}

getMytoken() :any
{
return sessionStorage.getItem("mytoken");
}
}

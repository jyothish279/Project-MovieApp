import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { UserauthService } from '../userauth.service';
import { MyrouteService } from '../myroute.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username : FormControl;
  password: FormControl;
  loginform : FormGroup;
  mytoken : any;
  submitMessage: string;


 

  constructor(private routobj : MyrouteService,private authobj:UserauthService) {

    this.loginform=new FormGroup(
      {
    username :new FormControl('',Validators.required),
    password:new FormControl('',Validators.required)
   });
   this.username=new FormControl();

   }
  

   

   
   



   getValue()
   {
  console.log (this.loginform.value);
 let data=this.loginform.value;
  this.authobj.authenticateUser(data).subscribe(
    (res)=> { 
           console.log(res);  
           let tok=res["token"];
            this.authobj.setMytoken(tok);
            sessionStorage.setItem("username",this.loginform.controls['username'].value);
          console.log(this.loginform.controls['username'].value);
            console.log(tok);
            alert("Login Successful");
            this.routobj.openDashboard();
            
          },
    (err)=>
      { 
        if (err.status === 404) {
          this.submitMessage = err.message;
        } else {
          this.submitMessage = err.error.message;
        }

        console.log("inside failure");
        console.log(err); 
        alert("Username/Password is wrong")
      }
    
      );  
    
  }


  ngOnInit(): void {
  }

}

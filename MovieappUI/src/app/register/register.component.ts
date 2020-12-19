import { Component, OnInit } from '@angular/core';

import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { RegisterserviceService } from '../registerservice.service';
import { MyrouteService } from '../myroute.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerform : FormGroup;
  username : FormControl;
  password: FormControl;
  firstname: FormControl;
  lastname: FormControl;
  location: FormControl;

  constructor(private regserve : RegisterserviceService,private rout:MyrouteService) { 

    this.registerform=new FormGroup(
      {
        username: new FormControl("",Validators.required),
        password: new FormControl("",Validators.required),
        firstname: new FormControl("",Validators.required),
        lastname: new FormControl("",Validators.required),
        location: new FormControl("",Validators.required)
      }

    )
  }

  ngOnInit(): void {
  }

  storeRec()
  {
    let data=this.registerform.value;
    console.log(data);
this.regserve.registerUser(data).subscribe
( (res)=> {console.log("user Registered" + res);
    alert("Registered Successfully");
    this.rout.openLogin();
},
error=> {
  console.log("register user error",error);
  alert("Username already exists");
}
);

  }

  callLogin()
  {
    this.rout.openLogin();
    console.log("yes");
  }


}

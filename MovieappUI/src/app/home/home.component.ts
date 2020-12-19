import { Component, OnInit } from '@angular/core';
import { Movieres } from '../movieres';
import { MyrouteService } from '../myroute.service';
import { ThirdpartyService } from '../thirdparty.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  newMovDetails: Movieres[];
  q: any;

  constructor(private rout:MyrouteService,private movieobj: ThirdpartyService) { }

  ngOnInit(): void {
    
   this.movieobj.getallmovies().subscribe(res => {
    this.newMovDetails=res["results"];
    console.log(this.newMovDetails);
    console.log(res);
  })

  }

  toLogin(){

    this.rout.openLogin();
  }



}

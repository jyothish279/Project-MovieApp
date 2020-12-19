import { Component, OnInit } from '@angular/core';
import { ThirdpartyService } from '../thirdparty.service';
import { Movieres} from '../movieres'
import { MyrouteService } from '../myroute.service';
import { AddfavouriteService } from '../addfavourite.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  newMovieres: Movieres[];
  q: any;
  addobj: Movieres = new Movieres();


  constructor(private movieobj: ThirdpartyService , private rout:MyrouteService,private favservice:AddfavouriteService ) { }

  ngOnInit(): void{

   this.movieobj.getallmovies().subscribe(res => {
     this.newMovieres=res["results"];
     console.log(this.newMovieres);
     console.log(res);
   })

  }

addFav(movieobj) {
  this.addobj.title=movieobj.title;
  this.addobj.poster_path=movieobj.poster_path;
  this.addobj.overview=movieobj.overview;
  this.addobj.username=sessionStorage.getItem("username");
  console.log(sessionStorage.getItem("username"));
  console.log(this.addobj);
  this.favservice.addFavourite(this.addobj).subscribe(res=>{
  console.log(res,"movie added");

    alert("favourite added");
  },
  (err)=>{
    console.log("error",err);
    alert("favourite already exists");
  }
  );
}




callFavourites(){
  this.rout.openFavourites();
  console.log("yes");

}

callLogout() {
  sessionStorage.clear();
  this.rout.openLogin();
}


}
 
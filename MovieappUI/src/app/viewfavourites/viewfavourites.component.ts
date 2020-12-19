import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { AddfavouriteService } from '../addfavourite.service';
import { Movieres } from '../movieres';

@Component({
  selector: 'app-viewfavourites',
  templateUrl: './viewfavourites.component.html',
  styleUrls: ['./viewfavourites.component.css']
})
export class ViewfavouritesComponent implements OnInit {
  newDetails: any;
  q: any;

  constructor(private viewFavService:AddfavouriteService ) { }

  ngOnInit(): void {
    this.getAllfavouritemovies();
  }


  getAllfavouritemovies(){

    this.viewFavService.getAllmovies().subscribe(
      response => {
        console.log(response);
        this.newDetails = response;
      },
      err=>{
        console.log("not able to view favourites",err);
      }
      
    );

  }

  

  delFav(res){
    console.log(res);
    this.viewFavService.delMovie(res.favid).subscribe(
      response=>{
        console.log(response);
        this.getAllfavouritemovies();
      },
      err=>{
        let index=this.newDetails.indexOf(res);
        this.newDetails.splice(index,1);
        console.log("Favourite not deleted",err);
      alert("Successfull removed from favouirtes");      }
    );
  }


}
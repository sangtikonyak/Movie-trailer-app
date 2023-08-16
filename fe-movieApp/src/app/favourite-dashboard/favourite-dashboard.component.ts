import { Component, OnChanges, OnInit } from '@angular/core';
import { FavmoviesService } from '../services/favmovies.service';
import { AuthServiceService } from '../services/auth-service.service';



@Component({
  selector: 'app-favourite-dashboard',
  templateUrl: './favourite-dashboard.component.html',
  styleUrls: ['./favourite-dashboard.component.css']
})
export class FavouriteDashboardComponent implements OnInit {
  constructor(private favMovieService: FavmoviesService) { }

  movies: any = [];
  filteredMoviesArr: any = [];

  ngOnInit(): void {
    this.favMovieService.fetchMovies().subscribe(
      (response: any) => {
        this.movies = response;
      }
    )
  }


  searchThis(movieName: any) {
    console.log(movieName);
    console.log(this.movies);
    this.filteredMoviesArr = this.movies;
    this.filteredMoviesArr = this.filteredMoviesArr.filter((n: { movieName: string; }) => n.movieName?.toLowerCase().startsWith(movieName.toLowerCase()));
    this.movies = this.filteredMoviesArr;
    console.log(this.filteredMoviesArr);
  }

  getMovieList(event: any) {
    this.movies = event;
  }
}



import { Component } from '@angular/core';
import { WatchlaterService } from '../services/watchlater.service';

@Component({
  selector: 'app-watchlater-dashboard',
  templateUrl: './watchlater-dashboard.component.html',
  styleUrls: ['./watchlater-dashboard.component.css']
})
export class WatchlaterDashboardComponent {
  
  constructor(private watchlaterService: WatchlaterService) { }

  movies: any = [];
  filteredMoviesArr: any = [];

  ngOnInit(): void {
    this.watchlaterService.fetchMovies().subscribe(
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
   this.ngOnInit()
  }

}

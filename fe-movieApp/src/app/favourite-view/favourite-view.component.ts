import { Component, EventEmitter, Input, Output } from '@angular/core';
import { HomeServiceService } from '../services/home-service.service';
import { Router } from '@angular/router';
import { FavmoviesService } from '../services/favmovies.service';

@Component({
  selector: 'app-favourite-view',
  templateUrl: './favourite-view.component.html',
  styleUrls: ['./favourite-view.component.css']
})
export class FavouriteViewComponent {

  constructor(private homeService: HomeServiceService, private router: Router, private favMovieService: FavmoviesService) { }
  @Input()
  movie: any;

  image_url: any;
  POSTER_PATH = "https://image.tmdb.org/t/p/w500/";
  url: any;

  ngOnInit(): void {
    this.url = this.POSTER_PATH + this.movie.posterPath;
  }

  goToMovieDetails(){
    const  movieId=this.movie.movieId;
    console.log("movieid :" + movieId);
   this.router.navigate(["movie-details",movieId])
  }

  movies: any = [];
  @Output() favMovies = new EventEmitter<any>();

  getMovies() {
    this.favMovies.emit(this.movies);
  }
  deleteMovie() {

    this.favMovieService.deleteMovie(this.movie.movieId).subscribe({
      next: (data) => {
        console.log(data)
        this.movies = data
        this.getMovies()
        alert('Successfully Deleted');

      },
      error: (err) => {
        alert('Not deleted');
      },
    });
  }

}



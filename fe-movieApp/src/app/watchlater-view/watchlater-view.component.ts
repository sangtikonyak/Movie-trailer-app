import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { WatchlaterService } from '../services/watchlater.service';

@Component({
  selector: 'app-watchlater-view',
  templateUrl: './watchlater-view.component.html',
  styleUrls: ['./watchlater-view.component.css']
})
export class WatchlaterViewComponent {
  constructor( private router: Router, private watchLater: WatchlaterService) { }
  @Input()
  movie: any;

  image_url: any;
  POSTER_PATH = "https://image.tmdb.org/t/p/w500/";
  url: any;

  ngOnInit(): void {
    this.url = this.POSTER_PATH + this.movie.posterPath;
  }

  movies: any = [];
  @Output() watchLaterMovies = new EventEmitter<any>();

  getMovies() {
    this.watchLaterMovies.emit(this.movies);
  }
  deleteMovie() {

    this.watchLater.deleteMovie(this.movie.movieId).subscribe({
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

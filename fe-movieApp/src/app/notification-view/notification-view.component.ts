import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NotificationService } from '../services/notification.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-notification-view',
  templateUrl: './notification-view.component.html',
  styleUrls: ['./notification-view.component.css']
})
export class NotificationViewComponent {
  constructor(  private notification : NotificationService, private router :Router) { }
  @Input()
  movie: any;

  image_url: any;
  POSTER_PATH = "https://image.tmdb.org/t/p/w500/";
  url: any;

  ngOnInit(): void {
    this.url = this.POSTER_PATH + this.movie.posterPath;
  }

  movies: any = [];
  @Output() notificationMovies = new EventEmitter<any>();

  getMovies() {
    this.notificationMovies.emit(this.movies);
  }

  goToMovieDetails(){
    const  movieId=this.movie.movieId;
    console.log("movieid :" + movieId);
   this.router.navigateByUrl("movie-details",movieId)
  }

  deleteMovie() {

    this.notification.deleteMovie(this.movie.movieId).subscribe({
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

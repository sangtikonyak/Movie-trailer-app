import { Component, OnInit } from '@angular/core';
import { WatchlaterService } from '../services/watchlater.service';
import { NotificationService } from '../services/notification.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {
  constructor(private notification : NotificationService) { }

  movies: any = [];
  filteredMoviesArr: any = [];

  ngOnInit(): void {

    this.notification.fetchMovies().subscribe(
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


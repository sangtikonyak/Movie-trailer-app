import { Component, Input, OnInit } from '@angular/core';
import { HomeServiceService } from '../services/home-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-noteview-dashboard',
  templateUrl: './noteview-dashboard.component.html',
  styleUrls: ['./noteview-dashboard.component.css']
})
export class NoteviewDashboardComponent implements OnInit {
  constructor(private homeService: HomeServiceService, private router: Router) { }
  @Input()
  movie: any;

  image_url: any;
  POSTER_PATH = "https://image.tmdb.org/t/p/w500/";
  url: any;

  ngOnInit(): void {
    this.url = this.POSTER_PATH + this.movie.poster_path;
  }

  goToMovieDetails() {
    this.homeService.movie = this.movie;
    console.log(this.homeService.movie)
    const movieId = this.movie.id;
    console.log("movieid :" + movieId);
    this.router.navigate(["movie-details", movieId]);

  }
}

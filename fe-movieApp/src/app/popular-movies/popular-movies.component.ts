import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { HomeServiceService } from '../services/home-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-popular-movies',
  templateUrl: './popular-movies.component.html',
  styleUrls: ['./popular-movies.component.css']
})
export class PopularMoviesComponent implements OnInit {
  constructor(private homeService: HomeServiceService, private router: Router) { }
  
  @Input()
  popularMovie: any;

  url: any;

  ngOnInit(): void {
    // console.log(this.popularMovie)
    this.url = this.homeService.POSTER_PATH + this.popularMovie.poster_path;
    // console.log(this.url)
  }

  goToMovieDetails() {
    // console.log(this.popularMovie.id)
    this.router.navigate(["movie/top_rated/details", this.popularMovie.id])
  }

}

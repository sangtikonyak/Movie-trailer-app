import { Component, OnInit } from '@angular/core';
import { HomeServiceService } from '../services/home-service.service';


@Component({
  selector: 'app-home-dashboard',
  templateUrl: './home-dashboard.component.html',
  styleUrls: ['./home-dashboard.component.css']
})
export class HomeDashboardComponent implements OnInit {
  constructor(private homeService: HomeServiceService) { }
  movies: any = [];
  filteredMoviesArr: any = [];
  changePage: boolean = false;
  page: any;
  reloadPage : boolean = false;
  ngOnInit(): void {
    this.homeService.fetchMovies().subscribe(
      (response: any) => {
        this.movies = response.results;
        this.page = response.page;
        
      }
    )
  }

  searchThis(movieName: any) {
    // console.log(movieName);
    // console.log(this.movies);
    this.filteredMoviesArr = this.movies;
    this.filteredMoviesArr = this.filteredMoviesArr.filter((n: { title: string; }) => n.title?.toLowerCase().startsWith(movieName.toLowerCase()));
    this.movies = this.filteredMoviesArr;
    // console.log(this.filteredMoviesArr);
    this.changePage = true;
    this.reloadPage = true;
  }

  goToNewPage() {

    this.homeService.fetchMoviesByPage(++this.page).subscribe(
      (response: any) => {
        this.movies = response.results;
        // console.log(this.movies)
      }
    )

  }

  goToPreviousPage() {
    this.homeService.fetchMoviesByPage(--this.page).subscribe(
      (response: any) => {
        this.movies = response.results;
        // console.log(this.movies)
      }
    )
  }
  reload(){
    this.ngOnInit()
  }
}

import { Component, EventEmitter, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { HomeServiceService } from '../services/home-service.service';
import { ActivatedRoute, Params } from '@angular/router';
import { FavmoviesService } from '../services/favmovies.service';
import { AuthServiceService } from '../services/auth-service.service';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { VideoComponentComponent } from '../video-component/video-component.component';
import { Movie } from '../model/Movie';
import { WatchlaterService } from '../services/watchlater.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-movie-details-dashboard',
  templateUrl: './movie-details-dashboard.component.html',
  styleUrls: ['./movie-details-dashboard.component.css']
})
export class MovieDetailsDashboardComponent implements OnInit {
  constructor(private homeService: HomeServiceService, private route: ActivatedRoute, private favService: FavmoviesService,
    private auth: AuthServiceService, private matDialog: MatDialog, private watchLaterService: WatchlaterService,
    private snackBar : MatSnackBar ) { }

  POSTER_PATH = "https://image.tmdb.org/t/p/w500/";
  image_url = '';
  movie: any = {};
  popularMovies: any;
  favMovies: any = [];
  movieDb: Movie = {}
  login : boolean =false


  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      const movieId = params["id"]
      this.homeService.fetchMoviesById(movieId).subscribe((response) => {
        this.movie = response;
        this.image_url = this.POSTER_PATH + this.movie.poster_path;
        this.movieDb.movieId = this.movie.id;
        this.movieDb.movieName = this.movie.original_title;
        this.movieDb.posterPath = this.movie.poster_path;
        this.movieDb.yearOfRelease = this.movie.release_date;
        this.movieDb.watched = true;
      })
    })
    this.homeService.fetchPopularMovies().subscribe((response: any) => {
      this.popularMovies = response.results;
    })
  }

  addMovieToFavourite() {
    if (this.auth.loginStatus) {

      this.favService.addMovie(this.movieDb)
      .subscribe({
        next: data => {
          this.snackBar.open('movie added', 'successfully', {
            duration: 2000,
            panelClass: ['mat-toolbar', 'mat-primary']
          });
        },
        error: err =>  
        {
          if(err.status == 409){
            this.snackBar.open('movie ', 'already exist', {
              duration: 2000,
              panelClass: ['mat-toolbar', 'mat-primary']
            })
          }
          else{
            this.snackBar.open('movie ', 'not added', {
              duration: 2000,
              panelClass: ['mat-toolbar', 'mat-primary']
            })
          }
        }

  
        
      })
        
    }
    else {
      let dialogRef= this.matDialog.open(LoginComponent, {
        maxWidth: '530px',
        maxHeight: '380px',
      })
      dialogRef.afterClosed().subscribe(()=>
      {
      this.login = this.auth.loginStatus;
    })
    }

  }

  addToWatchLater() {
    if (this.auth.loginStatus) {
      this.watchLaterService.addMovie(this.movieDb)
        .subscribe({
          next: data => {
            this.snackBar.open('movie added', 'successfully', {
              duration: 2000,
              panelClass: ['mat-toolbar', 'mat-primary']
            });
          },
          error: err =>    {
            if(err.status == 409){
              this.snackBar.open('movie ', 'already exist', {
                duration: 2000,
                panelClass: ['mat-toolbar', 'mat-primary']
              })
            }
            else{
              this.snackBar.open('movie ', 'not added', {
                duration: 2000,
                panelClass: ['mat-toolbar', 'mat-primary']
              })
            }
          }
        })
    }
    else {
     let dialogRef= this.matDialog.open(LoginComponent, {
        maxWidth: '530px',
        maxHeight: '380px',
      })

      dialogRef.afterClosed().subscribe(()=>
      {
      this.login = this.auth.loginStatus;
      
    })
    }

  }

  openPlayVideo() {
    this.movieDb.watched = false;
    this.matDialog.open(VideoComponentComponent, {
      maxWidth: '500px',
      maxHeight: '420px',
      data: {
        dataKey: this.movie.id
      }
    })
  }

}

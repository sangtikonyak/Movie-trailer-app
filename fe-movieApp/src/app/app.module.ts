
import { NgModule,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeDashboardComponent } from './home-dashboard/home-dashboard.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';

import { MatIconModule } from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatListModule} from '@angular/material/list';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { HttpClientModule } from '@angular/common/http'; 
import { MatCardModule } from '@angular/material/card';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NoteviewDashboardComponent } from './noteview-dashboard/noteview-dashboard.component';
import { MovieDetailsDashboardComponent } from './movie-details-dashboard/movie-details-dashboard.component';
import { MatTooltipModule} from '@angular/material/tooltip';
import { PopularMoviesComponent } from './popular-movies/popular-movies.component';
import { FavouriteDashboardComponent } from './favourite-dashboard/favourite-dashboard.component';
import { MatButtonModule } from "@angular/material/button";
import { MatDialogModule } from "@angular/material/dialog";
import { HeaderComponent } from './header/header.component';
import { FavouriteViewComponent } from './favourite-view/favourite-view.component';
import { YouTubePlayerModule } from '@angular/youtube-player';
import { VideoComponentComponent } from './video-component/video-component.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { WatchlaterDashboardComponent } from './watchlater-dashboard/watchlater-dashboard.component';
import { WatchlaterViewComponent } from './watchlater-view/watchlater-view.component';
import { NotificationComponent } from './notification/notification.component';
import { NotificationViewComponent } from './notification-view/notification-view.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeDashboardComponent,
    SignupComponent,
    LoginComponent,
    NoteviewDashboardComponent,
    MovieDetailsDashboardComponent,
    PopularMoviesComponent,
    FavouriteDashboardComponent,
    HeaderComponent,
    FavouriteViewComponent,
    VideoComponentComponent,
    PageNotFoundComponent,
    WatchlaterDashboardComponent,
    WatchlaterViewComponent,
    NotificationComponent,
    NotificationViewComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    HttpClientModule,
    MatCardModule,
    MatSnackBarModule,
    MatTooltipModule,
    MatButtonModule,
    MatDialogModule,
    YouTubePlayerModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }

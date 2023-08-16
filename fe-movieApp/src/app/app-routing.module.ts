import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { MovieDetailsDashboardComponent } from './movie-details-dashboard/movie-details-dashboard.component';
import { HomeDashboardComponent } from './home-dashboard/home-dashboard.component';
import { FavouriteDashboardComponent } from './favourite-dashboard/favourite-dashboard.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { WatchlaterDashboardComponent } from './watchlater-dashboard/watchlater-dashboard.component';
import { NotificationComponent } from './notification/notification.component';
import { authGuard } from './auth.guard';

const routes: Routes = [
  {path:'movie-details/:id', component:MovieDetailsDashboardComponent},
  {path:'movie/top_rated/details/:id',component:MovieDetailsDashboardComponent},
  {path:'home', component:HomeDashboardComponent},
  {path:"favourites", component:FavouriteDashboardComponent,canActivate : [authGuard]},
  {path:"watch-later", component:WatchlaterDashboardComponent,canActivate : [authGuard]},
  {path: "notification", component : NotificationComponent,canActivate : [authGuard]},
  {path:'', redirectTo:'/home',pathMatch: 'full' },
  {path:"**", component: PageNotFoundComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

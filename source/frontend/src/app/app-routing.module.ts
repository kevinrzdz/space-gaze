import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {AsteroidComponent} from "./components/asteroid/asteroid.component";
import {HomeComponent} from "./components/home/home.component";
import {PlanetComponent} from "./components/planet/planet.component";
import {StarComponent} from "./components/star/star.component";
import {ExoplanetComponent} from "./components/exoplanet/exoplanet.component";
import {AstronomicalEventComponent} from "./components/astronomical-event/astronomical-event.component";

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'asteroids', component: AsteroidComponent },
  { path: 'planets', component: PlanetComponent },
  { path: 'exoplanets', component: ExoplanetComponent },
  { path: 'events', component: AstronomicalEventComponent },
  { path: 'stars', component: StarComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

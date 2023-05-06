import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {TrackerComponent} from "./components/tracker/tracker.component";
import {HomeComponent} from "./components/home/home.component";
import {PlanetComponent} from "./components/planet/planet.component";
import {AstronomicalEventComponent} from "./components/astronomical-event/astronomical-event.component";
import {DetailsComponent} from "./components/tracker/details/details.component";

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'tracker', component: TrackerComponent},
  {path: 'tracker/:id', component: DetailsComponent},
  {path: 'planets', component: PlanetComponent},
  {path: 'events', component: AstronomicalEventComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

import {Component} from '@angular/core';
import {AsteroidService} from '../../services/asteroid/asteroid.service';
import {Asteroid} from '../../models/asteroid/asteroid.model';
import {Star} from "../../models/star/star.model";
import {StarService} from "../../services/star/star.service";
import {Exoplanet} from "../../models/exoplanet/exoplanet.model";
import {ExoplanetService} from "../../services/exoplanet/exoplanet.service";

@Component({
  selector: 'app-asteroid',
  templateUrl: './tracker.component.html',
  styleUrls: ['./tracker.component.scss'],
})
export class TrackerComponent {
  asteroids!: Asteroid[];
  stars!: Star[];
  exoplanets!: Exoplanet[];
  searchName: string = '';

  constructor(private asteroidService: AsteroidService, private starService: StarService, private exoplanetService: ExoplanetService) {
  }

  search(): void {
    this.loadFilteredStars(this.searchName);
    this.loadFilteredExoplanets(this.searchName);
    this.loadFilteredAsteroids(this.searchName);
  }

  loadFilteredAsteroids(searchName: string): void {
    if (searchName.trim() === '') {
      this.asteroids = [];
    } else {
      this.asteroidService
        .getFilteredAsteroids(searchName)
        .subscribe((data) => {
          this.asteroids = data;
        });
    }
  }

  loadFilteredStars(searchName: string): void {
    if (searchName.trim() === '') {
      this.stars = [];
    } else {
      this.starService
        .getFilteredStars(searchName)
        .subscribe((data) => {
          this.stars = data;
        });
    }
  }

  loadFilteredExoplanets(searchName: string): void {
    if (searchName.trim() === '') {
      this.exoplanets = [];
    } else {
      this.exoplanetService
        .getFilteredExoplanets(searchName)
        .subscribe((data) => {
          this.exoplanets = data;
        });
    }
  }

}

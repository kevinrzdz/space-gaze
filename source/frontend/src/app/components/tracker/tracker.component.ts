import {Component} from '@angular/core';
import {AsteroidService} from '../../services/asteroid/asteroid.service';
import {Asteroid} from '../../models/asteroid/asteroid.model';
import {Star} from "../../models/star/star.model";
import {StarService} from "../../services/star/star.service";
import {Exoplanet} from "../../models/exoplanet/exoplanet.model";
import {ExoplanetService} from "../../services/exoplanet/exoplanet.service";
import { Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';

@Component({
  selector: 'app-asteroid',
  templateUrl: './tracker.component.html',
  styleUrls: ['./tracker.component.scss'],
})
export class TrackerComponent {
  asteroids: Asteroid[] = [];
  stars: Star[] = [];
  exoplanets: Exoplanet[] = [];
  searchName: string = '';
  private searchSubject = new Subject<string>();
  isLoading: boolean = false;
  hasSearched: boolean = false;
  selectedFilter: string = 'all';

  constructor(private asteroidService: AsteroidService, private starService: StarService, private exoplanetService: ExoplanetService) {
    this.searchSubject.pipe(
      debounceTime(500),
      distinctUntilChanged()
    ).subscribe((searchName) => {
      this.search(searchName);
    });
  }

  onInputChange(searchName: string): void {
    this.isLoading = true;
    this.searchSubject.next(searchName);
  }

  search(searchName: string): void {
    this.hasSearched = true;

    switch (this.selectedFilter) {
      case 'asteroids':
        this.loadFilteredAsteroids(searchName);
        this.stars = [];
        this.exoplanets = [];
        break;
      case 'stars':
        this.loadFilteredStars(searchName);
        this.asteroids = [];
        this.exoplanets = [];
        break;
      case 'exoplanets':
        this.loadFilteredExoplanets(searchName);
        this.asteroids = [];
        this.stars = [];
        break;
      default:
        this.loadFilteredAsteroids(searchName);
        this.loadFilteredStars(searchName);
        this.loadFilteredExoplanets(searchName);
    }

    this.isLoading = false;
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

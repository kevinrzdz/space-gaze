import {Component} from '@angular/core';
import {AsteroidService} from '../../services/asteroid/asteroid.service';
import {Asteroid} from '../../models/asteroid/asteroid.model';
import {Star} from "../../models/star/star.model";
import {StarService} from "../../services/star/star.service";
import {Exoplanet} from "../../models/exoplanet/exoplanet.model";
import {ExoplanetService} from "../../services/exoplanet/exoplanet.service";
import {forkJoin, of, Subject, tap} from 'rxjs';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';

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
  isLoading: boolean = false;
  isSearching: boolean = false;
  hasSearched: boolean = false;
  selectedFilter: string = 'all';
  searchSubject: Subject<any> = new Subject<string>();

  constructor(private asteroidService: AsteroidService, private starService: StarService, private exoplanetService: ExoplanetService) {
    this.searchSubject.pipe(
      debounceTime(750),
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
    this.isSearching = true;
    let observables = [];

    switch (this.selectedFilter) {
      case 'asteroids':
        observables.push(this.loadFilteredAsteroids(searchName));
        this.stars = [];
        this.exoplanets = [];
        break;
      case 'stars':
        observables.push(this.loadFilteredStars(searchName));
        this.asteroids = [];
        this.exoplanets = [];
        break;
      case 'exoplanets':
        observables.push(this.loadFilteredExoplanets(searchName));
        this.asteroids = [];
        this.stars = [];
        break;
      default:
        observables.push(this.loadFilteredAsteroids(searchName));
        observables.push(this.loadFilteredStars(searchName));
        observables.push(this.loadFilteredExoplanets(searchName));
    }

    forkJoin(observables).subscribe(() => {
      this.isLoading = false;
      this.hasSearched = true;
      this.isSearching = false;
    });
  }

  loadFilteredAsteroids(searchName: string) {
    if (searchName.trim() === '') {
      this.asteroids = [];
      return of([]);
    } else {
      return this.asteroidService.getFilteredAsteroids(searchName)
        .pipe(tap((data) => {
          this.asteroids = data;
        }));
    }
  }

  loadFilteredStars(searchName: string) {
    if (searchName.trim() === '') {
      this.stars = [];
      return of([]);
    } else {
      return this.starService.getFilteredStars(searchName)
        .pipe(tap((data) => {
          this.stars = data;
        }));
    }
  }

  loadFilteredExoplanets(searchName: string) {
    if (searchName.trim() === '') {
      this.exoplanets = [];
      return of([]);
    } else {
      return this.exoplanetService.getFilteredExoplanets(searchName)
        .pipe(tap((data) => {
          this.exoplanets = data;
        }));
    }
  }
}


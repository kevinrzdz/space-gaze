import {Component} from '@angular/core';
import {AsteroidService} from '../../services/asteroid/asteroid.service';
import {Asteroid} from '../../models/asteroid/asteroid.model';

@Component({
  selector: 'app-asteroid',
  templateUrl: './asteroid.component.html',
  styleUrls: ['./asteroid.component.scss'],
})
export class AsteroidComponent {
  asteroids!: Asteroid[];
  searchName: string = '';

  constructor(private asteroidService: AsteroidService) {
  }

  searchAsteroids(): void {
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
}

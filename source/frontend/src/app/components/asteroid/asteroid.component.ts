import {Component, OnInit} from '@angular/core';
import {AsteroidService} from '../../services/asteroid.service';
import {Asteroid} from '../../models/asteroid.model';

@Component({
  selector: 'app-asteroid',
  templateUrl: './asteroid.component.html',
  styleUrls: ['./asteroid.component.scss']
})
export class AsteroidComponent implements OnInit {
  asteroids: Asteroid[] = [];
  page: number = 1;
  pageSize: number = 20;
  collectionSize: number = 0;
  goToPage: number = 1;

  constructor(private asteroidService: AsteroidService) {
  }

  ngOnInit(): void {
    this.loadAsteroids(this.page - 1);
  }

  loadAsteroids(page: number): void {
    this.asteroidService.getAsteroids(page).subscribe(data => {
      this.asteroids = data.content;
      this.collectionSize = data.totalElements;
    });
  }

  loadPage(page: number): void {
    this.loadAsteroids(page - 1);
  }

  jumpToPage(): void {
    if (this.goToPage > 0 && this.goToPage <= Math.ceil(this.collectionSize / this.pageSize)) {
      this.page = this.goToPage;
      this.loadAsteroids(this.page - 1);
    } else {
      alert('La página ingresada no es válida.');
    }
  }

}

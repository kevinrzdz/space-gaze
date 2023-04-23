import {Component, OnInit} from '@angular/core';
import {AsteroidService} from '../../services/asteroid/asteroid.service';
import {Asteroid} from '../../models/asteroid/asteroid.model';
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-asteroid',
  templateUrl: './asteroid.component.html',
  styleUrls: ['./asteroid.component.scss']
})
export class AsteroidComponent implements OnInit {
  asteroids!: Asteroid[];
  collectionSize: number = 0;
  pageSize: number = 15;
  pageIndex: number = 0;

  constructor(private asteroidService: AsteroidService) {
  }

  ngOnInit(): void {
    this.loadAsteroids(this.pageIndex);
  }

  loadAsteroids(page: number): void {
    this.asteroidService.getAsteroids(page, this.pageSize).subscribe(data => {
      this.asteroids = data.content;
      this.collectionSize = data.totalElements;
    });
  }

  pageChanged(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadAsteroids(this.pageIndex);
  }

}

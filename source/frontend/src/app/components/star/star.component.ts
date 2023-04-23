import { Component, OnInit } from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {Star} from "../../models/star/star.model";
import {StarService} from "../../services/star/star.service";

@Component({
  selector: 'app-star',
  templateUrl: './star.component.html',
  styleUrls: ['./star.component.scss']
})
export class StarComponent implements OnInit {
  stars!: Star[];
  collectionSize: number = 0;
  pageSize: number = 15;
  pageIndex: number = 0;

  constructor(private starService: StarService) {
  }

  ngOnInit(): void {
    this.loadAsteroids(this.pageIndex);
  }

  loadAsteroids(page: number): void {
    this.starService.getStars(page, this.pageSize).subscribe(data => {
      console.log(data)
      this.stars = data.content;
      this.collectionSize = data.totalElements;
    });
  }

  pageChanged(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadAsteroids(this.pageIndex);
  }

}

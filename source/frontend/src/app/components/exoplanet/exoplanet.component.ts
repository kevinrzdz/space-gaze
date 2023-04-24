import {Component} from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {Exoplanet} from "../../models/exoplanet/exoplanet.model";
import {ExoplanetService} from "../../services/exoplanet/exoplanet.service";

@Component({
  selector: 'app-exoplanet',
  templateUrl: './exoplanet.component.html',
  styleUrls: ['./exoplanet.component.scss']
})
export class ExoplanetComponent {
  exoplanets!: Exoplanet[];
  collectionSize: number = 0;
  pageSize: number = 15;
  pageIndex: number = 0;

  constructor(private exoplanetService: ExoplanetService ) {
  }

  ngOnInit(): void {
    this.loadExoplanets(this.pageIndex);
  }

  loadExoplanets(page: number): void {
    this.exoplanetService.getExoplanets(page, this.pageSize).subscribe(data => {
      this.exoplanets = data.content;
      this.collectionSize = data.totalElements;
    });
  }

  pageChanged(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadExoplanets(this.pageIndex);
  }

}


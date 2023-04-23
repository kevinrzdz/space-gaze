import {Component, OnInit} from '@angular/core';
import {Planet} from "../../models/planet/planet.model";
import {PlanetService} from "../../services/planet/planet.service";

@Component({
  selector: 'app-planet',
  templateUrl: './planet.component.html',
  styleUrls: ['./planet.component.scss']
})
export class PlanetComponent implements OnInit {
  planets: Planet[] = [];

  constructor(private planetService: PlanetService) { }

  ngOnInit(): void {
    this.loadPlanets();
  }

  loadPlanets(): void {
    this.planetService.getPlanets().subscribe(data => {
      console.log(data);
      this.planets = data;
    });
  }

}

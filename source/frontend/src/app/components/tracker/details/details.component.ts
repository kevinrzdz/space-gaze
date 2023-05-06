import {Component, OnInit} from '@angular/core';
import {Asteroid} from "../../../models/asteroid/asteroid.model";
import {AsteroidService} from "../../../services/asteroid/asteroid.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'asteroid-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})
export class DetailsComponent implements OnInit {
  asteroid!: Asteroid;
  private selectedImage!: File;

  constructor(private asteroidService: AsteroidService, private activatedRoute: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    const id: number = parseInt(<string>this.activatedRoute.snapshot.paramMap.get('id'), 10);
    this.asteroidService.getAsteroidById(id).subscribe((asteroid: Asteroid) => this.asteroid = asteroid)
  }

  selectImage(event: any) {
    this.selectedImage = event.target.files[0];
  }

  uploadImage() {
    this.asteroidService.uploadImage(this.selectedImage, this.asteroid.id).subscribe(() => window.location.reload()
    )
  }
}

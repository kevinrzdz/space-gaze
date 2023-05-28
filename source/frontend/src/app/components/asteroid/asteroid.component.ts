import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Asteroid} from "../../models/asteroid/asteroid.model";
import {AsteroidService} from "../../services/asteroid/asteroid.service";
import Swal from 'sweetalert2';

@Component({
  selector: 'asteroid-details',
  templateUrl: './asteroid.component.html',
  styleUrls: ['./asteroid.component.scss']
})
export class AsteroidComponent implements OnInit {
  asteroid!: Asteroid;
  selectedImage!: File;

  constructor(private asteroidService: AsteroidService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    const id: number = parseInt(<string>this.activatedRoute.snapshot.paramMap.get('id'), 10);
    this.asteroidService.getAsteroidById(id).subscribe((asteroid: Asteroid) => this.asteroid = asteroid)
  }

  selectImage(event: any) {
    this.selectedImage = event.target.files[0];
  }

  uploadImage() {
    this.asteroidService.uploadImage(this.selectedImage, this.asteroid.id).subscribe(() => {
      Swal.fire({
        icon: 'success',
        title: 'Success',
        text: `Your image has been updated`,
        confirmButtonColor: '#6f32be',
        background: '#000',
        iconColor: '#c844ec'
      }).then(
          () => window.location.reload()
        );
      }
    )
  }

  defaultImage(event: any) {
    event.target.src = '/assets/img/no-img.jpg';
  }
}

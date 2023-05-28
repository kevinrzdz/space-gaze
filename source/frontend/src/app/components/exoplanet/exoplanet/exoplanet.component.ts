import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import Swal from "sweetalert2";
import {Exoplanet} from "../../../models/exoplanet/exoplanet.model";
import {ExoplanetService} from "../../../services/exoplanet/exoplanet.service";

@Component({
  selector: 'app-exoplanet',
  templateUrl: './exoplanet.component.html',
  styleUrls: ['./exoplanet.component.scss']
})
export class ExoplanetComponent implements OnInit {
  exoplanet!: Exoplanet;
  selectedImage!: File;

  constructor(private exoplanetService: ExoplanetService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    const id: number = parseInt(<string>this.activatedRoute.snapshot.paramMap.get('id'), 10);
    this.exoplanetService.getExoplanet(id).subscribe((exoplanet: Exoplanet) => this.exoplanet = exoplanet)
  }

  selectImage(event: any) {
    this.selectedImage = event.target.files[0];
  }

  uploadImage() {
    this.exoplanetService.uploadImage(this.selectedImage, this.exoplanet.id).subscribe(() => {
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


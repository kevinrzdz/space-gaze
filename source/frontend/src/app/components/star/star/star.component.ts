import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import Swal from "sweetalert2";
import {Star} from "../../../models/star/star.model";
import {StarService} from "../../../services/star/star.service";

@Component({
  selector: 'app-star',
  templateUrl: './star.component.html',
  styleUrls: ['./star.component.scss']
})
export class StarComponent implements OnInit {
  star!: Star;
  selectedImage!: File;

  constructor(private starService: StarService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    const id: number = parseInt(<string>this.activatedRoute.snapshot.paramMap.get('id'), 10);
    this.starService.getStar(id).subscribe((star: Star) => this.star = star)
  }

  selectImage(event: any) {
    this.selectedImage = event.target.files[0];
  }

  uploadImage() {
    this.starService.uploadImage(this.selectedImage, this.star.id).subscribe(() => {
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


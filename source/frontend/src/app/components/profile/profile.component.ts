import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user/user.model";
import {UserService} from "../../services/user/user.service";
import {AuthService} from "../../services/auth/auth.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user: User = {
    id: '',
    password: '',
    email: '',
    username: '',
    role: ''
  }
  image: string = '';
  selectedImage!: File;

  constructor(private userService: UserService, private authService: AuthService) {
  }

  ngOnInit() {
    this.userService.getUserData(this.authService.getDecodedToken().sub).subscribe((user) => {
        this.user = user;
        this.image = user.image;
      }
    )
  }

  uploadImage() {
    this.userService.uploadImage(this.selectedImage, parseInt(this.user.id)).subscribe(() => {
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
    event.target.src = '/assets/img/profile.png';
  }

  selectImage(event: any) {
    this.selectedImage = event.target.files[0];
  }

}


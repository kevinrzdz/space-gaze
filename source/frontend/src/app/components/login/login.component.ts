import {Component} from '@angular/core';
import {AuthService} from '../../services/auth/auth.service';
import {Router} from '@angular/router';
import {Login} from "../../models/user/login.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginData: Login = {
    email: '',
    password: ''
  };
  errorMessage!: string;

  constructor(private authService: AuthService, private router: Router) {
  }

  login() {
    this.authService.login(this.loginData).subscribe(response => {
      console.log(response);
    });
  }

  onEnter(event: any) {
    if (event.keyCode === 13) {
      this.login();
    }
  }
}

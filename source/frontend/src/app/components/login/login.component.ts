import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { LoginDto } from '../../models/loginDto/loginDto.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginData: LoginDto = {
    username: '',
    password: ''
  };

  errorMessage!: string;

  constructor(private authService: AuthService, private router: Router) { }

  login() {
    this.authService.login(this.loginData).subscribe(response => {
      if (response.status) {
        this.router.navigate(['/home']);
      } else {
        this.errorMessage = response.message;
      }
    });
  }
}

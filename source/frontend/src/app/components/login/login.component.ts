import {Component} from '@angular/core';
import {AuthService} from '../../services/auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  errorMessage!: string;

  constructor(private authService: AuthService, private router: Router) {
  }

  // login() {
  //  this.authService.login(this.loginData).subscribe(response => {
  //    if (response.status) {
  //      this.router.navigate(['/home']);
  //    } else {
  //      this.errorMessage = response.message;
  //    }
  //  });
  // }

  // onEnter(event: any) {
  //   if (event.keyCode === 13) {
  //    this.login();
  //  }
  // }
}

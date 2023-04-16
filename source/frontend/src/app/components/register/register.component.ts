import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth/auth.service';
import {User} from "../../models/user/user.model";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  user: User = {
    id: '',
    email: '',
    username: '',
    password: ''
  };

  errorMessage!: string;

  constructor(private authService: AuthService, private router: Router) {
  }

  register() {
    this.authService.register(this.user).subscribe(response => {
      if (response.status) {
        this.router.navigate(['/home']);
      } else {
        this.errorMessage = response.message;
      }
    });
  }

  onEnter(event: any) {
    if (event.keyCode === 13) {
      this.register();
    }
  }
}

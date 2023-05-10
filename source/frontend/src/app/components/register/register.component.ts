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
  passwordConfirm: string = '';
  validations = [
    {
      isValid: () => this.user.email.trim().length > 0 && this.user.username.trim().length > 0 && this.user.password.trim().length > 0 && this.passwordConfirm.trim().length > 0,
      errorMessage: 'Please, fill all the fields'
    },
    {
      isValid: () => this.user.email.match("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"),
      errorMessage: 'Invalid email format'
    },
    {
      isValid: () => this.user.username.match("^[a-zA-Z0-9]+$"),
      errorMessage: 'Username can only contain alphanumeric characters'
    },
    {
      isValid: () => this.user.password.match("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.])[A-Za-z\\d@$!%*?&.]{8,}$"),
      errorMessage: 'Password must have at least 8 characters, one uppercase letter, one lowercase letter, one number, and one special character'
    },
    {
      isValid: () => this.user.password === this.passwordConfirm,
      errorMessage: 'Password confirm does not match'
    },
  ];
  errorMessage!: string;

  constructor(private authService: AuthService, private router: Router) {
  }

  register() {
    if (this.validate()) {
      this.authService.register(this.user).subscribe((response) => {
        console.log(response)
      });
    }
  }


  validate(): boolean {
    for (const validation of this.validations) {
      if (!validation.isValid()) {
        this.errorMessage = validation.errorMessage;
        return false;
      }
    }
    this.errorMessage = '';
    return true;
  }


  onEnter(event: any) {
    if (event.keyCode === 13) {
      this.register();
    }
  }
}

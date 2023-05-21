import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  userMenuVisible = false;
  emailUsuario: string = '';

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.emailUsuario = this.authService.getDecodedToken().sub;
  }

  toggleUserMenu(): void {
    this.userMenuVisible = !this.userMenuVisible;
  }

  logOut() {
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }
}


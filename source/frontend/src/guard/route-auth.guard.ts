import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from "../app/services/auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class RouteAuthGuard implements CanActivate {

  constructor(private loginService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.loginService.getToken() != null) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }

}

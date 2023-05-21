import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, Observable, tap, throwError} from 'rxjs';
import {User} from "../../models/user/user.model";
import {Login} from "../../models/user/login.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl: string = 'http://localhost:8090';

  constructor(private http: HttpClient) {
  }

  register(user: User): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, user);
  }

  login(user: Login) {
    return this.http.post(`${this.apiUrl}/login`, user, {observe: 'response'})
      .pipe(
        tap(response => {
          const token = response.headers.get('Authorization');
          if (typeof token === "string") {
            sessionStorage.setItem('jwt', token.replace('Bearer ', ''));
          }
        }),
        catchError(() => {
          return throwError(() => new Error('There was a mistake. Check your credentials'));
        })
      );
  }

  getToken() {
    return sessionStorage.getItem('jwt');
  }

}

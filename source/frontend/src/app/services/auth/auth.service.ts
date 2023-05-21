import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, Observable, tap, throwError} from 'rxjs';
import {User} from "../../models/user/user.model";
import {Login} from "../../models/user/login.model";
import jwt_decode from 'jwt-decode';

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
    return this.http.post('http://localhost:8090/login', user, {observe: 'response'})
      .pipe(
        tap(response => {
          const token = response.headers.get('Authorization');
          if (typeof token === "string") {
            sessionStorage.setItem('jwt', token.replace('Bearer ', ''));
          }
        }),
        catchError(() => {
          return throwError(() => new Error('Hubo un error al iniciar sesión'));
        })
      );
  }

  getToken(): any {
    return sessionStorage.getItem('jwt');
  }

  getDecodedToken(): any {
    try {
      return jwt_decode(this.getToken());
    } catch (Error) {
      return null;
    }
  }

}

import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
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

  login(user: Login): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, user);
  }
}

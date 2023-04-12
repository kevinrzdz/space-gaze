import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {User} from "../../models/user/user.model";
import {LoginResponse} from "../../models/loginResponse/loginResponse.model";
import {LoginDto} from "../../models/loginDto/loginDto.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8090/api/users';

  constructor(private http: HttpClient) {
  }

  register(user: User): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/save`, user);
  }

  login(loginData: LoginDto): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, loginData);
  }
}

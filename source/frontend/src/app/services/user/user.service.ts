import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/user/user.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl: string = 'http://localhost:8090/api/user';

  constructor(private http: HttpClient) {
  }

  getUserData(email: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?email=${email}`);
  }

  uploadImage(file: File, id: number) {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('id', id.toString());
    return this.http.post(`${this.apiUrl}/upload`, formData);
  }

}

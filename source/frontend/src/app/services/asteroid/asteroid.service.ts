import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Asteroid } from '../../models/asteroid/asteroid.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AsteroidService {
  private apiUrl = 'http://localhost:8090/api/asteroids';

  constructor(private http: HttpClient) {}

  getAsteroidById(id: number): Observable<Asteroid> {
    return this.http.get<Asteroid>(`${this.apiUrl}/${id}`);
  }

  getFilteredAsteroids(searchTerm: string): Observable<Asteroid[]> {
    return this.http
      .get<any>(`${this.apiUrl}?name=${searchTerm}`)
      .pipe(map((response) => response));
  }


  uploadImage(file: File, id: number) {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('id', id.toString());
    return this.http.post(`${this.apiUrl}/upload`, formData);
  }
}

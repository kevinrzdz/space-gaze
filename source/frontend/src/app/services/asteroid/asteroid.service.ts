import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Asteroid } from '../../models/asteroid/asteroid.model';

@Injectable({
  providedIn: 'root',
})
export class AsteroidService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getAsteroids(
    page: number
  ): Observable<{ content: Asteroid[]; totalElements: number }> {
    return this.http.get<{ content: Asteroid[]; totalElements: number }>(
      `${this.apiUrl}/asteroids?page=${page}&size=15`
    );
  }

}

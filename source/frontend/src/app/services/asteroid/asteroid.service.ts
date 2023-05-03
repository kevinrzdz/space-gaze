import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Asteroid} from '../../models/asteroid/asteroid.model';

@Injectable({
  providedIn: 'root',
})
export class AsteroidService {
  private apiUrl = 'http://localhost:8090/api/asteroids';

  constructor(private http: HttpClient) {
  }

  getAsteroids(
    page: number,
    size: number
  ): Observable<{ content: Asteroid[]; totalElements: number }> {
    return this.http.get<{ content: Asteroid[]; totalElements: number }>(
      `${this.apiUrl}?page=${page}&size=${size}`
    );
  }

  getAsteroidById(id: number): Observable<Asteroid> {
    return this.http.get<Asteroid>(`${this.apiUrl}/${id}`)
  }


  uploadImage(file: File, id: number) {
    const formData = new FormData();
    formData.append("file", file);
    formData.append("id", id.toString());
    return this.http.post(`${this.apiUrl}/uploads`, formData);
  }

}

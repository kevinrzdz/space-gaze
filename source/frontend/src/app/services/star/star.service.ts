import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Star } from 'src/app/models/star/star.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class StarService {
  private apiUrl: string = 'http://localhost:8090/api/stars';

  constructor(private http: HttpClient) {}

  getFilteredStars(searchTerm: string): Observable<Star[]> {
    return this.http
      .get<any>(`${this.apiUrl}?name=${searchTerm}`)
      .pipe(map((response) => response));
  }

  getStar(id: number): Observable<Star> {
    return this.http.get<Star>(`${this.apiUrl}/${id}`);
  }

  uploadImage(file: File, id: number) {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('id', id.toString());
    return this.http.post(`${this.apiUrl}/upload`, formData);
  }
}

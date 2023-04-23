import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Star} from 'src/app/models/star/star.model';

@Injectable({
  providedIn: 'root'
})
export class StarService {

  private apiUrl: string = 'http://localhost:8090/api/stars';

  constructor(private http: HttpClient) {
  }

  getStars(
    page: number,
    size: number
  ): Observable<{ content: Star[]; totalElements: number }> {
    return this.http.get<{ content: Star[]; totalElements: number }>(
      `${this.apiUrl}?page=${page}&size=${size}`
    );
  }

  getStar(id: number): Observable<Star> {
    return this.http.get<Star>(`${this.apiUrl}/${id}`);
  }
}

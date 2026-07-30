import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AstronomicalEvent } from '../../models/astronomical-event/astronomical-event';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AstronomicalEventService {
  private apiUrl = `${environment.apiUrl}/api/astronomical-events`;

  constructor(private http: HttpClient) {}

  getAstronomicalEvents(): Observable<AstronomicalEvent[]> {
    return this.http.get<AstronomicalEvent[]>(this.apiUrl);
  }

  getAstronomicalEvent(id: number): Observable<AstronomicalEvent> {
    return this.http.get<AstronomicalEvent>(`${this.apiUrl}/${id}`);
  }
}

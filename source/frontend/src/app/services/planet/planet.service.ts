import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Planet} from "../../models/planet/planet.model";

@Injectable({
  providedIn: 'root'
})
export class PlanetService {

  apiUrl: string = 'https://spacegazebackend.alu7359.arkania.es/api/planets';

  constructor(private http: HttpClient) {
  }

  getPlanets(): Observable<Planet[]> {
    return this.http.get<Planet[]>(this.apiUrl);
  }

  getPlanet(id: number): Observable<Planet> {
    return this.http.get<Planet>(`${this.apiUrl}/${id}`);
  }
}

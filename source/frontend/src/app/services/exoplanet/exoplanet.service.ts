import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Exoplanet} from "../../models/exoplanet/exoplanet.model";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ExoplanetService {

  private apiUrl = 'http://localhost:8090/api/exoplanets';

  constructor(private http: HttpClient) {
  }

  getFilteredExoplanets(searchTerm: string): Observable<Exoplanet[]> {
    return this.http
      .get<any>(`${this.apiUrl}?name=${searchTerm}`)
      .pipe(map((response) => response));
  }
}

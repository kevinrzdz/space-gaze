import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Exoplanet} from "../../models/exoplanet/exoplanet.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ExoplanetService {

  private apiUrl = 'http://localhost:8090/api/exoplanets';

  constructor(private http: HttpClient) { }

  getExoplanets(
    page: number,
    size: number
  ): Observable<{ content: Exoplanet[]; totalElements: number }> {
    return this.http.get<{ content: Exoplanet[]; totalElements: number }>(
      `${this.apiUrl}?page=${page}&size=${size}`
    );
  }
}

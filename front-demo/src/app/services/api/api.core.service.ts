import { HttpClient } from '@angular/common/http';
import { ApiService } from './api.service';
import { Api } from '../../constants/Api.constants';
import { Injectable } from '@angular/core';
import Auth from '../../models/auth.model';
import Login from '../../models/login.model';
import { catchError, map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiCoreService extends ApiService {
  constructor(http: HttpClient) {
    super(http, Api.URL_CORE);
  }

  protected override handleResponse<T>(response: { data: T }): T {
    return response.data as T;
  }

  login( data: Login): Observable<Auth> {
      return this.http.post<Auth>(`${this.urlBase}${Api.LOGIN}`, data).pipe(
        map((response) =>{
          console.log(response);
          return response}),
        catchError(this.handleError.bind(this))
      );
    }
}

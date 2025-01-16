import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';



export class ApiService {
  constructor(
    protected readonly http: HttpClient,
    protected urlBase: string
  ) {}

  get<T>(element: string): Observable<T> {
    console.log(`${this.urlBase}${element}`);
    return this.http.get<T >(`${this.urlBase}${element}`).pipe(
      map((response) => response),
      catchError(this.handleError.bind(this))
    );
  }

  put<T>(element: string, data: T): Observable<T> {
    return this.http.put<T >(`${this.urlBase}${element}`, data).pipe(
      map((response) => response),
      catchError(this.handleError.bind(this))
    );
  }

  post<T>(element: string, data: T): Observable<T> {
    return this.http.post<T>(`${this.urlBase}${element}`, data).pipe(
      map((response) => response),
      catchError(this.handleError.bind(this))
    );
  }

  delete<T>(element: string, id:string): Observable<T> {
    return this.http.delete<T>(`${this.urlBase}${element}/${id}`).pipe(
      map((response) => response),
      catchError(this.handleError.bind(this))
    );
  }

  protected handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred!';
    if (error.error.message) {
      // Client-side or network error
      errorMessage = `A client-side error occurred: ${error.error.message}`;
    } else {
      // Backend error
      errorMessage = `Backend returned code ${error.status}, body was: ${error.error}`;
    }
    return throwError(errorMessage);
  }

  protected handleResponse<T>(response: { data: T }): T {
    return response.data as T;
  }
}

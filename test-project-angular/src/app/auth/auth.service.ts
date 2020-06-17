import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {throwError} from 'rxjs';
import {UserModel} from '../shared/user.model';
import {catchError, map} from 'rxjs/operators';

const jsonHeader = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  }),
  responseType: 'text' as 'json'
};

@Injectable()
export class AuthService {
  baseURL = 'http://localhost:8080/';
  registerSuffix = 'register';
  loginSuffix = 'login';
  roleSuffix = 'getRole';

  constructor(private http: HttpClient) { }

  register(user: UserModel) {
    return this.http.post<boolean>(this.baseURL + this.registerSuffix,
      JSON.stringify(user), jsonHeader)
      .pipe(catchError(this.handleError));
  }

  login(user: UserModel) {
    return this.http.post<string>(this.baseURL + this.loginSuffix,
      JSON.stringify(user), jsonHeader)
      .pipe(catchError(this.handleError));
  }

  getRole(token: string) {
    return this.http.post<string>(this.baseURL + this.roleSuffix,
      JSON.stringify(token), jsonHeader)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError(error.error);
  }
}

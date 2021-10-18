import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from './login/user';
import { UserPassword } from './login/userPassword';

import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  apiURL: string = environment.apiURLBase + '/users';
  apiURLForgot: string = environment.apiURLBase + '/forgot?';
  tokenUrl: string = environment.apiURLBase + environment.tokenUrl;
  jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) {}

  handleToken() {
    const tokenString = localStorage.getItem('token');

    if (tokenString) {
      const token = JSON.parse(tokenString).token;
      return token;
    }
    return null;
  }

  signOut() {
    localStorage.removeItem('token');
  }

  getAuthenticatedUser() {
    const token = this.handleToken();
    if (token) {
      const user = this.jwtHelper.decodeToken(token).sub;
      return user;
    }
    return null;
  }

  isAuthenticated(): boolean {
    const token = this.handleToken();

    if (token) {
      const expired = this.jwtHelper.isTokenExpired(token);
      return !expired;
    }
    return false;
  }

  save(user: User): Observable<any> {
    return this.http.post<any>(this.apiURL, user);
  }

  handleLogin(user: User): Observable<any> {
    return this.http.post(this.tokenUrl, user);
  }

  handleForgotPassword(user: User): Observable<any> {
    let params = new HttpParams()
      .set('question', user.question)
      .set('answer', user.answer);
    return this.http.get(this.apiURLForgot, {
      params: params,
      observe: 'response',
    });
  }

  handleChangePassword(id: string, user: UserPassword): Observable<any> {
    return this.http.patch(`${this.apiURL}/${id}`, user);
  }
}

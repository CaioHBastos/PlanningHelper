import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from './login/user';
import { UserAuth } from './login/userAuth';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  apiURL: string = environment.apiURLBase + '/users';
  constructor(private http: HttpClient) {}

  tokenStringHeader = localStorage.getItem('token');

  reqHeader = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'Bearer ' + JSON.parse(this.tokenStringHeader).token,
  });

  getUserById(id: string): Observable<UserAuth> {
    return this.http.get<any>(`${this.apiURL}/${id}`, {
      headers: this.reqHeader,
    });
  }
}

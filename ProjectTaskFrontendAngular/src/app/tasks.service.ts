import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Team } from './landing/landing';
import { Task } from './task/task';
import { TaskWithId } from './task/taskwithid';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TasksService {
  apiURL: string = environment.apiURLBase + '/tasks';

  constructor(private http: HttpClient) {}
  tokenStringHeader = localStorage.getItem('token');
  reqHeader = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'Bearer ' + JSON.parse(this.tokenStringHeader).token,
  });

  //saveTeam(team: Team): Observable<any> {
  //  return this.http.get<any>(`${this.apiURL}?nomeTime=${team.nomeTime}`, {
  //    headers: this.reqHeader,
  //  });
  //}

  save(task: Task): Observable<Task> {
    return this.http.post<Task>(this.apiURL, task, { headers: this.reqHeader });
  }

  getTasks(id: string): Observable<TaskWithId[]> {
    let params = new HttpParams().set('id', id);
    return this.http.get<TaskWithId[]>(this.apiURL, {
      params: params,
      headers: this.reqHeader,
    });
  }

  getTaskByHistory(storyNumber: string): Observable<TaskWithId[]> {
    let params = new HttpParams().set('story_number', storyNumber);
    return this.http.get<TaskWithId[]>(this.apiURL, {
      params: params,
      headers: this.reqHeader,
    });
  }

  deletar(task: Task): Observable<any> {
    return this.http.delete<any>(`${this.apiURL}/${task.id}`, {
      headers: this.reqHeader,
    });
  }

  update(task: Task): Observable<any> {
    return this.http.put<Task>(`${this.apiURL}/${task.id}`, task, {
      headers: this.reqHeader,
    });
  }

  getTaskById(id: string): Observable<TaskWithId> {
    return this.http.get<any>(`${this.apiURL}/${id}`, {
      headers: this.reqHeader,
    });
  }
}

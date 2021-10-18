import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Story } from './stories/story';
import { TaskWithId } from './task/taskwithid';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class StoriesService {
  apiURL: string = environment.apiURLBase + '/stories';
  jiraImporter: string = environment.apiURLBase + '/exportJiraImporter';
  planningPoker: string = environment.apiURLBase + '/exportPlanningPoker';

  constructor(private http: HttpClient) {}
  tokenStringHeader = localStorage.getItem('token');
  reqHeader = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'Bearer ' + JSON.parse(this.tokenStringHeader).token,
  });

  save(story: Story): Observable<Story> {
    return this.http.post<Story>(this.apiURL, story, {
      headers: this.reqHeader,
    });
  }

  //gerarPokerPlanning(): Observable<any> {
  //  return this.http.post<any>(this.planningPoker, { headers: this.reqHeader });
  //}
  //
  //gerarJiraImporter(): Observable<any> {
  //  return this.http.post<any>(this.jiraImporter, { headers: this.reqHeader });
  //}

  update(story: Story): Observable<any> {
    return this.http.put<Story>(`${this.apiURL}/${story.id}`, story, {
      headers: this.reqHeader,
    });
  }

  getStories(id: string): Observable<Story[]> {
    let params = new HttpParams().set('user_id', id);
    return this.http.get<Story[]>(this.apiURL, {
      params: params,
      headers: this.reqHeader,
    });
  }

  getStorieById(id: string): Observable<Story> {
    return this.http.get<any>(`${this.apiURL}/${id}`, {
      headers: this.reqHeader,
    });
  }

  deletar(story: Story): Observable<any> {
    return this.http.delete<any>(`${this.apiURL}/${story.id}`, {
      headers: this.reqHeader,
    });
  }
}

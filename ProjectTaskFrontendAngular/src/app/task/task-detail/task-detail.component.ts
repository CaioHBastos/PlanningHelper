import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Task } from '../task';
import { TasksService } from '../../tasks.service';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css'],
})
export class TaskDetailComponent implements OnInit {
  task: Task;
  id: string;

  constructor(
    private serviceTask: TasksService,
    private activatedRoute: ActivatedRoute
  ) {
    this.task = new Task();
  }

  ngOnInit(): void {
    let params: Params = this.activatedRoute.params;

    if (params && params.value && params.value.id) {
      this.id = params.value.id;
      this.serviceTask.getTaskById(this.id).subscribe(
        (response) => (this.task = response),
        (errorResponse) => (this.task = new Task())
      );
    }
  }
}

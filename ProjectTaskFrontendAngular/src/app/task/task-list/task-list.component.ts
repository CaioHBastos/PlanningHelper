import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TasksService } from '../../tasks.service';
import { Task } from '../task';
import { TaskWithId } from '../taskwithid';
import { AuthService } from '../../auth.service';

declare var $: any;
@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
})
export class TaskListComponent implements OnInit {
  tasks: TaskWithId[] = [];
  taskSelected: Task;
  mensagemSucesso: string;
  mensagemErro: string;
  task: TaskWithId;
  userAuthenticated: string;

  constructor(
    private service: TasksService,
    private router: Router,
    private authService: AuthService
  ) {
    this.task = new TaskWithId();
  }

  ngOnInit(): void {
    this.userAuthenticated = this.authService.getAuthenticatedUser();
    this.service
      .getTasks(this.userAuthenticated)
      .subscribe((response) => (this.tasks = response));
  }

  preparaDelecao(task: Task) {
    this.taskSelected = task;
  }

  preparaHours(task: TaskWithId) {
    this.taskSelected = task;
  }

  deletarTask() {
    this.service.deletar(this.taskSelected).subscribe(
      (response) => {
        this.mensagemSucesso = 'Task deletado com sucesso!';
        setTimeout(() => (this.mensagemSucesso = null), 2000);
        this.ngOnInit();
      },
      (erro) => (this.mensagemErro = 'Ocorreu um erro ao deletar a Task.')
    );
  }

  onSubmit() {
    this.taskSelected = {
      id: this.taskSelected.id,
      issueType: this.taskSelected.issueType,
      description: this.taskSelected.description,
      summary: this.taskSelected.summary,
      hours: this.taskSelected.hours,
      issueId: this.taskSelected.issueId,
      story: this.taskSelected.story,
      epicLink: this.taskSelected.epicLink,
      complexityPoints: this.taskSelected.complexityPoints,
      priority: this.taskSelected.priority,
      components: this.taskSelected.components,
      fixVersions: this.taskSelected.fixVersions,
      labels: this.taskSelected.labels,
      dueDate: this.taskSelected.dueDate,
      team: '',
      originalEstimate: this.taskSelected.originalEstimate,
    };

    this.service.update(this.taskSelected).subscribe(
      (response) => {
        this.mensagemSucesso = 'Horas cadastrada com sucesso!';
        setTimeout(() => (this.mensagemSucesso = null), 2000);
        $('#modalHours').modal('hide');
        this.ngOnInit();
      },
      (erro) => (this.mensagemErro = 'Ocorreu um erro ao cadastrar task.')
    );
    console.log(this.taskSelected);
  }
}

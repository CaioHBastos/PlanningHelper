import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Story } from '../story';
import { StoriesService } from '../../stories.service';
import { Task } from '../../task/task';
import { TaskWithId } from '../../task/taskwithid';
import { TasksService } from '../../tasks.service';

declare var $: any;
@Component({
  selector: 'app-stories-detail',
  templateUrl: './stories-detail.component.html',
  styleUrls: ['./stories-detail.component.css'],
})
export class StoriesDetailComponent implements OnInit {
  story: Story;
  stories: Story[] = [];
  id: string;
  task: Task;
  issues: string[];
  label: string[];
  primary: string[];
  issueType: string;
  priority: string;
  labels: string;
  storyJira: string;
  tasks: TaskWithId[] = [];
  mensagemSucesso: string;
  mensagemErro: string;
  taskSelected: Task;
  mensagemSucessoTask: string;
  mensagemErroTask: string;
  taskWithId: TaskWithId;

  constructor(
    private service: StoriesService,
    private serviceTask: TasksService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) {
    this.story = new Story();
    this.task = new Task();
    this.taskWithId = new TaskWithId();
    this.issues = ['Sub-Task', 'Sub-Development', 'Sub-Test'];
    this.label = ['component_suthub', 'integracao_suthub'];
    this.primary = ['high', 'medium', 'low'];
    this.storyJira = this.story.storyNumber;
  }

  ngOnInit(): void {
    let params: Params = this.activatedRoute.params;

    if (params && params.value && params.value.id) {
      this.id = params.value.id;
      this.service.getStorieById(this.id).subscribe(
        (response) => (this.story = response),
        (errorResponse) => (this.story = new Story())
      );
    }

    this.serviceTask
      .getTaskByHistory(this.story.storyNumber)
      .subscribe((response) => (this.tasks = response));
    console.log('this.story.storyNumber');
    console.log(this.story.storyNumber);
  }

  onSubmit() {
    this.task = {
      //id: '',
      issueType: this.task.issueType,
      description: this.task.description,
      summary: this.task.summary,
      hours: this.task.hours,
      issueId: this.task.issueId,
      story: this.story,
      epicLink: this.task.epicLink,
      complexityPoints: this.task.complexityPoints,
      priority: this.task.priority,
      components: this.task.components,
      fixVersions: this.task.fixVersions,
      labels: this.task.labels,
      dueDate: this.task.dueDate,
      team: '',
      originalEstimate: this.task.originalEstimate,
    };
    console.log('task');
    console.log(this.task);
    this.serviceTask.save(this.task).subscribe(
      (response) => {
        this.mensagemSucesso = 'Task cadastrada com sucesso!';
        setTimeout(() => (this.mensagemSucesso = null), 2000);
        $('#modalCadastro').modal('hide');
        this.ngOnInit();
      },
      (erro) => (this.mensagemErro = 'Ocorreu um erro ao cadastrar task.')
    );
  }

  preparaDelecao(task: Task) {
    this.taskSelected = task;
  }

  preparaHours(task: TaskWithId) {
    this.taskSelected = task;
  }

  deletarTask() {
    this.serviceTask.deletar(this.taskSelected).subscribe(
      (response) => {
        this.mensagemSucessoTask = 'Task deletado com sucesso!';
        setTimeout(() => (this.mensagemSucessoTask = null), 2000);
        this.ngOnInit();
      },
      (erro) =>
        (this.mensagemErroTask = 'Ocorreu um erro ao deletar o história.')
    );
  }

  onSubmitHour() {
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

    this.serviceTask.update(this.taskSelected).subscribe(
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

  includeBCP() {
    this.task = {
      //id: '',
      issueType: 'Sub-Task',
      description: 'BCP da história',
      summary: 'BCP',
      hours: 0,
      issueId: 0,
      story: this.story,
      epicLink: '',
      complexityPoints: '',
      priority: '',
      components: '',
      fixVersions: '',
      labels: '',
      dueDate: '',
      team: '',
      originalEstimate: 0,
    };
    this.serviceTask.save(this.task).subscribe(
      (response) => {
        this.mensagemSucesso = 'Task cadastrada com sucesso!';
        setTimeout(() => (this.mensagemSucesso = null), 2000);
        this.ngOnInit();
      },
      (erro) => (this.mensagemErro = 'Ocorreu um erro ao cadastrar task.')
    );
  }

  includeCodeReview() {
    this.task = {
      //id: '',
      issueType: 'Sub-Task',
      description: 'Code Review da história',
      summary: 'Code Review',
      hours: 0,
      issueId: 0,
      story: this.story,
      epicLink: '',
      complexityPoints: '',
      priority: '',
      components: '',
      fixVersions: '',
      labels: '',
      dueDate: '',
      team: '',
      originalEstimate: 0,
    };
    this.serviceTask.save(this.task).subscribe(
      (response) => {
        this.mensagemSucesso = 'Task cadastrada com sucesso!';
        setTimeout(() => (this.mensagemSucesso = null), 2000);
        this.ngOnInit();
      },
      (erro) => (this.mensagemErro = 'Ocorreu um erro ao cadastrar task.')
    );
  }

  includeDemo() {
    this.task = {
      //id: '',
      issueType: 'Sub-Task',
      description: 'Demo da história',
      summary: 'Demo',
      hours: 0,
      issueId: 0,
      story: this.story,
      epicLink: '',
      complexityPoints: '',
      priority: '',
      components: '',
      fixVersions: '',
      labels: '',
      dueDate: '',
      team: '',
      originalEstimate: 0,
    };
    this.serviceTask.save(this.task).subscribe(
      (response) => {
        this.mensagemSucesso = 'Task cadastrada com sucesso!';
        setTimeout(() => (this.mensagemSucesso = null), 2000);
        this.ngOnInit();
      },
      (erro) => (this.mensagemErro = 'Ocorreu um erro ao cadastrar task.')
    );
  }
}

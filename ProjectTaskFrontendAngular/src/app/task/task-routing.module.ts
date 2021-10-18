import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from '../layout/layout.component';
import { TaskListComponent } from './task-list/task-list.component';
import { TaskFormComponent } from './task-form/task-form.component';
import { TaskDetailComponent } from './task-detail/task-detail.component';
import { AuthGuard } from '../auth.guard';

const routes: Routes = [
  {
    path: 'tasks',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'tasks-form',
        component: TaskFormComponent,
      },
      {
        path: 'tasks-form/:id',
        component: TaskFormComponent,
      },
      {
        path: 'tasks-form-story/:id',
        component: TaskFormComponent,
      },
      {
        path: 'tasks-detail/:id',
        component: TaskDetailComponent,
      },
      {
        path: 'tasks-list',
        component: TaskListComponent,
      },
      {
        path: '',
        redirectTo: '/tasks/tasks-list',
        pathMatch: 'full',
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TaskRoutingModule {}

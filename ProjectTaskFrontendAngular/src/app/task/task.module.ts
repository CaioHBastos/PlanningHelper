import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { TaskRoutingModule } from './task-routing.module';
import { TaskListComponent } from './task-list/task-list.component';
import { TaskFormComponent } from './task-form/task-form.component';
import { TaskDetailComponent } from './task-detail/task-detail.component';

@NgModule({
  declarations: [TaskListComponent, TaskFormComponent, TaskDetailComponent],
  imports: [CommonModule, TaskRoutingModule, FormsModule],
})
export class TaskModule {}

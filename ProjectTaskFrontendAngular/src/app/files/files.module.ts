import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FilesFormComponent } from './files-form/files-form.component';
import { FilesRoutingModule } from './files-routing.module';

@NgModule({
  declarations: [FilesFormComponent],
  imports: [CommonModule, FilesRoutingModule, FormsModule],
})
export class FilesModule {}

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { StoriesRoutingModule } from './stories-routing.module';
import { StoriesFormComponent } from './stories-form/stories-form.component';
import { StoriesListComponent } from './stories-list/stories-list.component';
import { StoriesDetailComponent } from './stories-detail/stories-detail.component';

@NgModule({
  declarations: [StoriesFormComponent, StoriesListComponent, StoriesDetailComponent],
  imports: [CommonModule, StoriesRoutingModule, FormsModule],
  exports: [StoriesFormComponent, StoriesListComponent],
})
export class StoriesModule {}

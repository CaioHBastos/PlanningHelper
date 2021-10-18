import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from '../layout/layout.component';
import { FilesFormComponent } from '../files/files-form/files-form.component';

const routes: Routes = [
  {
    path: 'files',
    component: LayoutComponent,
    children: [
      { path: 'files-form', component: FilesFormComponent },
      { path: '', redirectTo: '/files/lista', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FilesRoutingModule {}

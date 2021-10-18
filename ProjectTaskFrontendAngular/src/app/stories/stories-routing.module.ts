import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StoriesFormComponent } from './stories-form/stories-form.component';
import { LayoutComponent } from '../layout/layout.component';
import { StoriesListComponent } from './stories-list/stories-list.component';
import { StoriesDetailComponent } from './stories-detail/stories-detail.component';
import { AuthGuard } from '../auth.guard';

const routes: Routes = [
  {
    path: 'stories',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'stories-form',
        component: StoriesFormComponent,
      },
      {
        path: 'stories-form/:id',
        component: StoriesFormComponent,
      },
      {
        path: 'stories-list',
        component: StoriesListComponent,
      },
      {
        path: 'stories-detail/:id',
        component: StoriesDetailComponent,
      },
      {
        path: '',
        redirectTo: '/stories/stories-list',
        pathMatch: 'full',
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StoriesRoutingModule {}

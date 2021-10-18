import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LandingComponent } from './landing/landing.component';
import { LayoutComponent } from '../layout/layout.component';
import { AuthGuard } from '../auth.guard';

const routes: Routes = [
  {
    path: 'squad',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'addTeam',
        component: LandingComponent,
      },
      {
        path: '',
        redirectTo: '/squad/team',
        pathMatch: 'full',
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LandingRoutingModule {}

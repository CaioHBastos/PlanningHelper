import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { LandingRoutingModule } from './landing-routing.module';
import { LandingComponent } from './landing/landing.component';

@NgModule({
  declarations: [LandingComponent],
  imports: [CommonModule, LandingRoutingModule, FormsModule],
  exports: [LandingComponent],
})
export class LandingModule {}

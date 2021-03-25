import { MainPageComponent } from './main-page/main-page.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ManageSensorComponent } from './manage-sensor/manage-sensor.component';

const routes: Routes = [
  { path: '', component: MainPageComponent },
  {path : 'manageSensor', component : ManageSensorComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

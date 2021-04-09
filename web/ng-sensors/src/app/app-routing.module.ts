import { ManageLocationComponent } from './location/manage-location/manage-location.component';
import { MainPageComponent } from './main-page/main-page.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ManageSensorComponent } from './manage-sensor/manage-sensor.component';
import { LocationComponent } from './location/location.component';

const routes: Routes = [
  { path: '', component: MainPageComponent },
  {path : 'manageSensor', component : ManageSensorComponent},
  {path : 'location', component : LocationComponent},
  {path : 'manageLocation', component : ManageLocationComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

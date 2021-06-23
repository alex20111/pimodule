import { WorkerListComponent } from './worker-list/worker-list.component';
import { ManageWorkerComponent } from './manage-worker/manage-worker.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from './main-page/main-page.component';

const routes: Routes = [
  { path: '', component: MainPageComponent },
  { path : 'manage', component : ManageWorkerComponent},
  { path: 'list', component : WorkerListComponent}
]; 

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

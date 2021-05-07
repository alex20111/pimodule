import { ConfigAccess } from './_helpers/config.access';
import { WeatherConfigComponent } from './weather-config/weather-config.component';
import { WeatherAlertComponent } from './weather-alert/weather-alert.component';
import { WeatherComponent } from './weather/weather.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WeatherExtendedComponent } from './weather-extended/weather-extended.component';

const routes: Routes = [
  { path: '', component: WeatherComponent },
  { path: 'extForecast', component: WeatherExtendedComponent },
  { path: 'alert', component: WeatherAlertComponent },
  { path: 'config', component: WeatherConfigComponent, canActivate: [ConfigAccess] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

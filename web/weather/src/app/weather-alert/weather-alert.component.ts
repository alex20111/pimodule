import { Constants } from '../_model/constants';
import { WeatherService } from './../services/weather.service';
import { WeatherAlert } from './../_model/weatherAlert';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { ToastService } from '../services/toast.service';

@Component({
  selector: 'app-weather-alert',
  templateUrl: './weather-alert.component.html',
  styleUrls: ['./weather-alert.component.css']
})
export class WeatherAlertComponent implements OnInit, OnDestroy {

  wthSubscription: Subscription;
  alert: WeatherAlert;

  timer: number;

  constructor(private weatherSvc: WeatherService, private router: Router, private toastService: ToastService,) { }


  ngOnInit(): void {

    this.wthSubscription = this.weatherSvc.getFullWeather().subscribe(weatherData => {
      if (weatherData && weatherData.alert) {
        this.alert = weatherData.alert;
      }
    });

    setTimeout(() => { this.enableBackToaster(); }, 500); // remove preloader

    this.timer = setTimeout(() => {
      this.router.navigate(['/']);
      this.toastService.remove2(Constants.BACK_INDX);
    }, 45000);  //45s
  }


  enableBackToaster(): void {
    this.toastService.show('Back', {
      classname: 'bg-success text-light',
      autohide: false,
      navigate: '/',
      removeKey: Constants.BACK_INDX
    });
  }

  ngOnDestroy(): void {
    this.wthSubscription.unsubscribe();
    clearTimeout(this.timer);
  }

}

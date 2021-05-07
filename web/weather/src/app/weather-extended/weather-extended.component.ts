import { Constants } from '../_model/constants';
import { WeatherError } from './../_model/weatherError';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ToastService } from '../services/toast.service';
import { WeatherService } from '../services/weather.service';
import { WeatherInfo } from '../_model/weatherInfo';
import { Router } from '@angular/router';
import { ImageService } from '../services/image-service.service';

@Component({
  selector: 'app-weather-extended',
  templateUrl: './weather-extended.component.html',
  styleUrls: ['./weather-extended.component.css']
})
export class WeatherExtendedComponent implements OnInit, OnDestroy {
  wthSubscription: Subscription;
  forecast: WeatherInfo;
  wthErrors: WeatherError;

  timer: number;

  constructor(private weatherSvc: WeatherService, private toastService: ToastService, private router: Router,
    private imageService: ImageService) { }

  ngOnInit(): void {

    setTimeout(() => { this.enableBackToaster(); }, 500); // remove preloader

    this.timer = setTimeout(() => {
      this.router.navigate(['/']);
      this.toastService.remove2(Constants.BACK_INDX);
    }, 45000);  // 45s

     // get weather!!
    this.wthSubscription = this.weatherSvc.getFullWeather().subscribe(weatherData => {
      console.log('Getting extended forecast', new Date());
      this.forecast = weatherData;
      this.wthErrors = null;
      if (this.forecast){ // populate weather icons for the forecast
       this.forecast.forecast =  this.imageService.getIconsForForecast(this.forecast.forecast);
      }
    });

    this.toastService.remove2(Constants.BACK_INDX);
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

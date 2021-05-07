import { ImageService } from './../services/image-service.service';
import { Constants } from '../_model/constants';
import { AlertLvl } from '../_enums/alertsLvl';
import { WeatherInfo } from './../_model/weatherInfo';
import { ToastService } from './../services/toast.service';
import { WeatherService } from './../services/weather.service';
import { Component, OnInit, OnDestroy, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { WeatherError } from '../_model/weatherError';
import { Subscription } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PasswordModalComponent } from '../password-modal/password-modal.component';
import { faSwimmingPool, faWarehouse } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit, OnDestroy {


  //font awesome icon
  faWarehouse = faWarehouse;
  faSwimming = faSwimmingPool;

  title = 'weather';
  fullWeather: WeatherInfo;
  wthErrors: WeatherError;
  wthSubscription: Subscription;
  weatherIcon: string;

  // scrollUp; //timer for scrolling up.. 
  currDateTimer;
  currDate: Date;
  obsDate: Date;

  //temporary temperature
  poolTemp: string;

  constructor(private router: Router, private wsApi: WeatherService,
    private toastService: ToastService, private renderer: Renderer2, private imageService: ImageService,
    private modalService: NgbModal) {

  }

  ngOnInit(): void {
    //start date
    this.currDate = new Date();
    this.currDateTimer = setInterval(() => { this.currDate = new Date(); }, 1000);
    // get weather from service
    this.wthSubscription = this.wsApi.getFullWeather().subscribe(weatherData => {
      this.fullWeather = weatherData;
      this.wthErrors = null;
      if (this.fullWeather) {
        // console.log(this.fullWeather);
        this.weatherIcon = this.imageService.getWeatherIcon(this.fullWeather.weather);
        this.changeBackgroundImage();
        this.filterSensors();
        this.obsDate = new Date(this.fullWeather.observationTime);
        // console.log('temp: ' , this.fullWeather.localtemp.tempMap.GARAGE, this.fullWeather.localtemp.tempDateMap.GARAGE);

      }
    });

    // verify if we have any errors
    this.wsApi.errorGenerated().subscribe(wthErr => {
      if (wthErr != null) {
        this.wthErrors = wthErr;
        this.fullWeather = null;
      }
    });
  }
  // load up full forecast to display.
  fullForecast(): void {
    this.router.navigate(['/extForecast']);
  }

  showRedToast(): void {

    this.toastService.show('Garage Door Open', {
      classname: 'bg-danger text-light',
      delay: 150000,
      autohide: true,
      removeKey: 1
    });
  }
  // show toaster for full screen
  enableFullScreenToaster(): void {
    this.toastService.show('Enable Full screen', {
      classname: 'bg-success text-light  toast-custom',
      autohide: false,
      navigate: 'fullscreen',
      removeKey: Constants.FULL_SCREEN_INDX
    });
  }

  ngOnDestroy(): void {
    this.wthSubscription.unsubscribe();
    clearInterval(this.currDateTimer);
  }

  loading(): boolean {
    return this.wsApi.loading;
  }

  // return the color for the alert
  alertType(): string {
    if (this.fullWeather) {
      if (this.fullWeather.alert) {
        const alertLvl = this.fullWeather.alert.level;
        if (alertLvl === AlertLvl.STATEMENT) {
          return 'dark';
        } else if (alertLvl === AlertLvl.WARNING) {
          return 'danger';
        } else if (alertLvl === AlertLvl.WATCH) {
          return 'warning';
        }
      }
    }
    return 'light';
  }

  // navigate to the alert screen.
  showAlert(): void {
    this.router.navigate(['/alert']);
  }


  // test to change the background.
  changeBackgroundImage(): void {
    const b = this.imageService.getBackgroundImage(this.fullWeather.weather);

    this.renderer.setStyle(document.body, 'background-image', `url(${b})`);
  }
  // open the modal component to Rename the group
  openFormModal(btnNbr: number): void {
    const modalRef = this.modalService.open(PasswordModalComponent);

    modalRef.result.then((result) => {
      if (result === 'ValidPassword') {
        this.router.navigate(['/config']);
      }
    }).catch((error) => {
      if (error !== 'Cross click') {
        console.error('password error : ', error);
      }
    });
  }

  filterSensors(): void {
    // it will filter the sensors.. example, if we did not get any data from a sensor for more than a day, remove it.

    // pool sensor filter
    if (this.fullWeather.localtemp.tempPool ) {
       const poolDate: Date = new Date(this.fullWeather.localtemp.tmpPoolUpdDt);
       const timeSpent = this.currDate.getTime() - poolDate.getTime();
      //  console.log('time spent: ' , timeSpent, this.currDate.getTime() ,poolDate.getTime(), poolDate );
       if (timeSpent > 86400000 ){ //one day for the pool sensor
        this.fullWeather.localtemp.tmpPoolUpdDt = null;
        this.fullWeather.localtemp.tempPool = null;
       }

    }
  }

}


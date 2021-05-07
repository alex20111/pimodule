import { Cycle } from './../_enums/cycle';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { timer, Observable, EMPTY, Subscription } from 'rxjs';
import { flatMap, catchError } from 'rxjs/operators';
import { delayedRetry } from '../_helpers/HttpHelper';
import { WeatherError } from '../_model/weatherError';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class DayNightCycleService {


  cycle: Cycle;
  cycleTimer: Subscription;

  constructor(private http: HttpClient, private configService: ConfigService) {

    this.configService.configUpdated().subscribe(newConfig => {
      this.dayNightCycleTimer();
    });
  }

 // timer that  get the weather every 5 minutes.
 private dayNightCycleTimer(): void {

  if (this.cycleTimer){
    this.cycleTimer.unsubscribe();
  }

  this.cycleTimer = timer(1000, this.configService.getRefreshTimeMillis() ).pipe(
    flatMap(() => this.fetchCycle())).subscribe(cycleData => {
    console.log('cycle data: ' , cycleData);

    if(cycleData.cycle === 'DAY'){
      this.cycle = Cycle.DAY;
    }else  if(cycleData.cycle === 'NIGHT'){
      this.cycle = Cycle.NIGHT;
    }else{
      this.cycle = Cycle.NA;
    }

  },
    cycleError => {
      this.cycle = Cycle.NA;
      console.log('cycleError svc', cycleError);
    });
}

private fetchCycle(): Observable<any> {

  return this.http.get<any>('http://192.168.1.110:8081/web/date/dayNightCycle/45.41117/-75.69812').pipe(
    delayedRetry(5000, 5),
    catchError(err => {
      const wError = new WeatherError('Retries', 'Retries failure', 'Number of retries exceeded for day night cycle. Cannot connect to server ');
      return EMPTY;
    }
    )
  );

}

}

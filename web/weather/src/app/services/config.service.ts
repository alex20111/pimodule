import { BehaviorSubject, Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

export class City {
  key: string;
  nameEn: string;
  nameFr: string;
  lat: string;
  long: string;
  dist: string;
}

export class Config{

  constructor(public currentCity: string, public refreshTime: number){}

  setCurrentCity(city): void{
    this.currentCity = city;
  }

  setRefreshTime(time: number): void{
    this.refreshTime = time;   // (in minutes)
  }
}

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private configPass = 'kitty12345';
  private configFields: Config;

  private configUpdate: BehaviorSubject<Config>;
  private weatherCity: BehaviorSubject<City[]>;

  isConfigUpdated = false;

  authenticated = false;

  constructor(private http: HttpClient) {

// check for any stored city
    const config: Config = JSON.parse(localStorage.getItem('weatherCity')) ;
    if (config === undefined || config == null ){
      this.configFields = new Config('on-118', 10);
    }else{
      this.configFields = new Config(config.currentCity, config.refreshTime);
    }

    this.weatherCity = new BehaviorSubject<City[]>([]);
    this.configUpdate = new BehaviorSubject<Config>(this.configFields);
  }

  passwordValid(pass: string): boolean {
    return this.configPass === pass;
  }

  getEnvCanCity(): Observable<City[]> {
    if (this.weatherCity.getValue().length === 0) {
      console.log('getFromWeb');
      // const options = { responseType: 'text' as 'text' };
      // return cities , but first pipe it and map it to save it to cache.
      this.http.get<City[]>('http://192.168.1.110:8081/web/temperature/envCanCities').subscribe(result => {

        // console.log("Result: " , result);
        const cc: City[] = result;
        this.weatherCity.next(cc);

      },
      error => {
        console.log('erorr in config,', error);       
      });
    }
    return this.weatherCity.asObservable();
  }

  getCurrentCity(): string {
    return this.configFields.currentCity;
  }
  getRefreshTimeMinutes(): number {
    return this.configFields.refreshTime;
  }
  getRefreshTimeMillis(): number {
    return this.configFields.refreshTime * 1000 * 60;
  }

  configUpdated(): Observable<Config>{
    return this.configUpdate;
  }

  updateConfig(currentCity: string, refreshTime: number): void {

    if (this.configFields.currentCity !== currentCity) {
      this.configFields.setCurrentCity(currentCity);
      this.isConfigUpdated = true;
    }
    if (this.configFields.refreshTime !== refreshTime) {
      this.configFields.setRefreshTime(refreshTime);
      this.isConfigUpdated = true;
    }

    if (this.configUpdate){
      console.log('this.configFields', this.configFields);
      localStorage.setItem('weatherCity', JSON.stringify(this.configFields));
      this.configUpdate.next(this.configFields);
    }
  }

}

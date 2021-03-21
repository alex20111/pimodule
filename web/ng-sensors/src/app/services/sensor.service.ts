import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Constants } from '../_helper/Constants';

@Injectable({
  providedIn: 'root'
})
export class SensorService {

  constructor(private http: HttpClient) {

  }

  loadAllSensors(): Observable<Sensor[]> {
    return this.http.get<Sensor[]>(`http://${Constants.HOST_ADDRESS}:8081/web/sensorsConfig/sensorList`);
  }

}

export class Sensor {

  id: number = -1;
  sensorId: string = "";
  sensorType: SensorType = SensorType.NONE;
  transFreq: number = -1;
  lastTransmit: string = null;
  lastUpdated: string = null;
  powerSave: boolean = false;
  pwSaveStart: number = -1;
  pwSaveEnd: number = -1;
  pwSaveTransFreq: number = -1;
  battLvl: string = "";
  configured: boolean = false;
  description: string = "";
}

enum SensorType {
  NONE,
  POOL,
  TEMPERATURE,
  GARDEN,
  LED
}

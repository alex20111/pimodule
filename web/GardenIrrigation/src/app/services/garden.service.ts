
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from '../_helper/Constants';

@Injectable({
  providedIn: 'root'
})
export class GardenService {

  constructor(private http: HttpClient) { }

  loadAllUnassignedSensors(): Observable<Sensor[]> {
    return this.http.get<Sensor[]>(`http://${Constants.HOST_ADDRESS}:8081/web/garden/unassigned`);
  }

  loadAllWorkers(): Observable<GardenWorker[]> {
    return this.http.get<GardenWorker[]>(`http://${Constants.HOST_ADDRESS}:8081/web/garden`);
  }

  addWorker(worker: GardenWorker): Observable<any> {
    return this.http.post(`http://${Constants.HOST_ADDRESS}:8081/web/garden`, worker);
  }

  updateWorker(worker: GardenWorker): Observable<any> {
    return this.http.put(`http://${Constants.HOST_ADDRESS}:8081/web/garden`, worker);
  }

  deleteWorker(workerId: number) {
    return this.http.delete<any>(`http://${Constants.HOST_ADDRESS}:8081/web/garden/${workerId}`);
  }

  activateWater(workerId: number, waterOn: boolean): Observable<string> {
    if (waterOn) {
      return this.http.get(`http://${Constants.HOST_ADDRESS}:8081/web/garden/${workerId}/turnOn`, { responseType: 'text' });
    } else {
      return this.http.get(`http://${Constants.HOST_ADDRESS}:8081/web/garden/${workerId}/turnOff`,  { responseType: 'text' });
      // return            this.http.get(`${Constants.HOST_ADDRESS}/blah`, { responseType: 'text' });
    }
  }

  loadWorker(workerId: number): Observable<GardenWorker>{
    return this.http.get<GardenWorker>(`http://${Constants.HOST_ADDRESS}:8081/web/garden/${workerId}`);
  }


}


export class Sensor {

  id: number = -1;
  sensorId: string = "";
  sensorType: SensorType = SensorType.NONE;
  transFreq: number = -1;
  lastTransmit: string = "";
  lastUpdated: string = "";
  powerSave: boolean = false;
  pwSaveStart: number = -1;
  pwSaveEnd: number = -1;
  pwSaveTransFreq: number = -1;
  battLvl: string = "";
  configured: boolean = false;
  description: string = "";
  errorField: string = "";

}

export enum SensorType {
  NONE = "NONE",
  POOL = "POOL",
  TEMP = "TEMP",
  GARDEN = "GARDEN",
  LED = "LED"
}

export class GardenWorker {
  id: number = -1;
  name: string = "";
  scheduleType: ScheduleType = ScheduleType.NONE; //if daily, weekly, monthly
  wateringDate: string = "";
  wateringDuration: number = -1;
  description: string = "";
  sensorIdFk: number = -1;

  status: WorkerStatus = new WorkerStatus();
}
export class WorkerStatus {
  workerId: number = -1;
  watering: boolean = false;
  doNotWater: boolean = false;
  lastUpdate: string = "";
  alive: boolean = false;
}

export enum ScheduleType {
  NONE = "NONE",
  DAILY = "DAILY",
  TWO_DAYS = "TWO_DAYS",
  THREE_DAYS = "THREE_DAYS",
  FOUR_DAYS = "FOUR_DAYS",
  WEEK = "WEEK"

}
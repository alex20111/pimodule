import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Constants } from '../_helper/Constants';
import { Message } from '../_helper/Message';

@Injectable({
  providedIn: 'root'
})
export class SensorService {

  constructor(private http: HttpClient) {

  }

  loadAllSensors(): Observable<Sensor[]> {
    return this.http.get<Sensor[]>(`http://${Constants.HOST_ADDRESS}:8081/web/sensorsConfig/sensorList`);
  }
  loadSensorById(id: string): Observable<Sensor | Message> {

    console.log("sendingL ", id);

    // console.log(typeof(id));

    return this.http.post<Sensor | Message>(`http://${Constants.HOST_ADDRESS}:8081/web/sensorsConfig/fetchSensorById`, id);
  }

  updateSensor(sensor: Sensor): Observable<Message> {
    return this.http.post<Message>(`http://${Constants.HOST_ADDRESS}:8081/web/sensorsConfig/updateSensor`, sensor);
  }

  deleteSensor(sensorId: string): Observable<Message> {
    return this.http.post<Message>(`http://${Constants.HOST_ADDRESS}:8081/web/sensorsConfig/deleteSensor`, sensorId);
  }

  messages(): Observable<Message[]> {
    return this.http.get<Message[]>(`http://${Constants.HOST_ADDRESS}:8081/web/sensorsConfig/messages`);
  }

  //-------------location --------------//

  loadAllSensorLocation(request: string): Observable<SensorLoc[]> {
    //request types: ALL_LOC_NO_SENSOR  // all location but don't retrieve the sensor entity 
    //ALL_LOC_WITH_SENSOR				// all location with sensors if any associated to it
    //ALL_LOC_FREE (No associated sensors) // all location that has no sensor associated to it ( no sensor FK )
    return this.http.post<SensorLoc[]>(`http://${Constants.HOST_ADDRESS}:8081/web/sensorsConfig/locations`, request);
  }

  addSensorLocation(location: SensorLoc): Observable<Message> {
    return this.http.post<Message>(`http://${Constants.HOST_ADDRESS}:8081/web/sensorsConfig/addLocation`, location);
  }

  updateLocation(location: SensorLoc): Observable<Message> {
    return this.http.post<Message>(`http://${Constants.HOST_ADDRESS}:8081/web/sensorsConfig/updateLocation`, location);
  }

  loadLocationById(id: string): Observable<Sensor | Message> {
    console.log("senloadLocationByIddingL ", id);
    return this.http.post<Sensor | Message>(`http://${Constants.HOST_ADDRESS}:8081/web/sensorsConfig/fetchLocationById`, id);
  }

  deleteLocation(id: string): Observable<Message> {
    console.log("deleteLocation ", id);
    return this.http.post< Message>(`http://${Constants.HOST_ADDRESS}:8081/web/sensorsConfig/deleteLocation`, id);
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
  errorField: string = "";

  sensorLocation: SensorLoc;
}

export enum SensorType {
  NONE = "NONE",
  POOL = "POOL",
  TEMPERATURE = "TEMPERATURE",
  GARDEN = "GARDEN",
  LED = "LED"
}

export class SensorLoc {
  id: number = -1;
  sensorLocation: string = "";
  description: string = "";
  sensorIdFk: number = -1;

  sensorName: string = "";
}
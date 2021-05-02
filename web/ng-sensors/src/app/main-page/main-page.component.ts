import { Sensor, SensorService, SensorType } from './../services/sensor.service';
import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { faBars, faBan, faSwimmingPool, IconDefinition, faThermometerThreeQuarters, faTrashAlt, faPlusCircle, faEdit } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit, OnDestroy {

  resultMessage: string = "";
  resultError: string = "";

  sensorList: Sensor[] = [];
  // menuCollapse: boolean = false;

  type = SensorType;
  intervalId;

  //icons
  faBars = faBars;
  faBan = faBan;
  faSwimmingPool = faSwimmingPool;
  faThermometerThreeQuarters = faThermometerThreeQuarters;
  faTrashAlt = faTrashAlt;
  faPlusCircle = faPlusCircle;
  faEdit = faEdit;

  constructor(private sensorService: SensorService, private router: Router) { 
    const navigation = this.router.getCurrentNavigation();
    const state = navigation.extras.state as {saveResult: string};
    if (state){
     this.resultMessage = state.saveResult;
    }
  }

  ngOnInit(): void {    

		//ALL_SENSORS --> all sensors with location or not.
		//SENSORS_WITH_LOC --> sensors that only has location attatched to them
		//SENSORS_WITH_NO_LOC --> all sensors that don't have a location attatched to it.


    this.loadAllSensors();

    this.intervalId = setInterval(() => {
      this.loadAllSensors(); 
    }, 3000);
  
  }

  loadAllSensors(){
    this.sensorService.loadAllSensors("ALL_SENSORS").subscribe(result => {
      console.log("Sersor service result: ", result);
      this.sensorList = result;
    },
      err => {
        console.error("error in loadAllSensors", err);
      });

  }

  sensorTypeIcon(sensorType: SensorType): IconDefinition {
    // console.log(sensorType, SensorType.POOL.toFixed, SensorType.POOL);
    let icon = this.faBan;
    if (sensorType === SensorType.POOL) {
      icon = this.faSwimmingPool;
    } else if (sensorType === SensorType.TEMP) {
      icon = this.faThermometerThreeQuarters;

    }
    return icon;

  }
  formatDate(dateStr: string): Date {
    let date = null;
    if (dateStr && dateStr.includes("[UTC]")) {
      dateStr = dateStr.substring(0, dateStr.indexOf("["));
    }

    // console.log("Date Str: ", dateStr);
    date = new Date(dateStr);
    return date;
  }

  deleteSensor(sensorId: string){
    // console.log(typeof(sensorId));

    let yes = confirm("Are you sure?");

    if (yes){
      let id = sensorId.toString();
      console.log("delete: " , id, typeof(id));
      this.sensorService.deleteSensor(id).subscribe(result => {
        if (result.message === "SUCCESS"){
          this.resultMessage = result.description;
          this.loadAllSensors();
        }else{
          this.resultError = result.message;
        }
      },
      err => {
        this.resultError = err.error.description + '<br/>' + err.message;
      })
    }
  }

  ngOnDestroy() {
     clearInterval(this.intervalId);
  }

}

import { SensorLoc, SensorService } from './../services/sensor.service';
import { Component, OnInit } from '@angular/core';
import { faEdit, faPlusCircle, faTrashAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {
  resultMessage: string = "";
  resultError: string = "";
  loadingList: boolean = false;
  loading: boolean = false;

  sensorLocList: SensorLoc[] = [];

  faPlusCircle = faPlusCircle;
  faTrashAlt = faTrashAlt;
  faEdit = faEdit;

  constructor(private sensorService: SensorService) { }

  ngOnInit(): void {
    this.loadingList = true;
    this.loadAllLocationWithSensor();
  }

  deleteLocation(id: any) {
    console.log("Deleting location id: ", id);

    let yes = confirm("Are you sure?");

    if (yes) {
      let locId;
      if( typeof id === 'number' ){
        locId = id.toString();
      }
      console.log("locId: " , locId);

      this.loading = true;
      this.sensorService.deleteLocation(locId).subscribe(result => {
        this.resultMessage = "Deleting ID: " + id;
        this.loadAllLocationWithSensor();
        this.loading = false;
      },
        err => {
          console.error("error in loadAllSensorLocation", err);
          this.loadingList = false;
          this.resultError = err.error.description + '<br/>' + err.message;
          this.loading = false;
        });
    }
  }

  loadAllLocationWithSensor() {
    this.sensorService.loadAllSensorLocation("ALL_LOC_WITH_SENSOR").subscribe(result => {
      this.sensorLocList = result;
      console.log("result: ", result);
      this.loadingList = false;
    },
      err => {
        console.error("error in loadAllSensorLocation", err);
        this.loadingList = false;
        this.resultError = err.error.description + '<br/>' + err.message;
      });
  }

}

		//request types: ALL_LOC_NO_SENSOR  // all location but don't retrieve the sensor entity 
		//ALL_LOC_WITH_SENSOR				// all location with sensors if any associated to it
		//ALL_LOC_FREE (No associated sensors) // all location that has no sensor associated to it ( no sensor FK )

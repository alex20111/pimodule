import { SensorLoc, SensorService } from './../services/sensor.service';
import { Component, OnInit } from '@angular/core';
import { faPlusCircle, faTrashAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {
  resultMessage: string = "";
  resultError: string = "";
  loadingList: boolean = false;


  sensorLocList: SensorLoc[] = [];

  faPlusCircle = faPlusCircle;
  faTrashAlt = faTrashAlt;

  constructor(private sensorService: SensorService) { }

  ngOnInit(): void {
this.loadingList = true;
    this.sensorService.loadAllSensorLocation().subscribe(result => {
      this.sensorLocList = result;
      console.log("result: " , result);
      this.loadingList = false;
    },
    err => {
      console.error("error in loadAllSensorLocation", err);
      this.loadingList = false;
    });
  }


  deleteLocation(id: string){
    console.log("Deleting location id: " , id);
    this.resultMessage = "Deleting ID: " + id;
  }



}

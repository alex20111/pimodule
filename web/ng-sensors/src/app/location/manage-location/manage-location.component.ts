import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { Sensor, SensorLoc, SensorService } from 'src/app/services/sensor.service';

@Component({
  selector: 'app-manage-location',
  templateUrl: './manage-location.component.html',
  styleUrls: ['./manage-location.component.css']
})
export class ManageLocationComponent implements OnInit {
  errorMessage: string = "";

  action: string = "";
  locId: string = "";

  submitted: boolean = false;
  loading: boolean = false;
  location: SensorLoc = new SensorLoc();

  sensorList: Sensor[] = [];

  sensorLocForm: FormGroup;


  faTrashAlt = faTrashAlt;

  constructor(private sensorService: SensorService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {

    this.action = window.history.state.action;
    this.locId = window.history.state.id;
    console.log("started locations,action: ", this.action, this.locId);

    if (this.action === 'add') {
      this.generateForm(this.location);
    } else {
      this.sensorService.loadLocationById(this.locId.toString()).subscribe(result => {
        this.location = result as SensorLoc;
        this.generateForm(this.location);
        console.log("Edit location: ", this.location);

        if (this.location.sensorName.length == 0 && this.location.sensorId === -1) {
          //load all sensors that does not have a location attatched. 
          this.sensorService.loadAllSensors("SENSORS_WITH_NO_LOC").subscribe(result => {
            this.loading = false;
            this.sensorList = result;
          },
            err => {
              console.error("error in loadAllSensors", err);
              this.loading = false;
              this.errorMessage = err.error.description + '<br/>' + err.message;
            });
        }
      },
        err => {
          console.error("error in loadAllSensorLocation", err);
          this.loading = false;
          this.errorMessage = err.error.description + '<br/>' + err.message;
        });
      console.log("edit");
    }

  }

  generateForm(locData: SensorLoc) {
    this.sensorLocForm = this.formBuilder.group({
      s_location: [locData.sensorLocation, [Validators.required, Validators.maxLength(20)]],
      s_description: [locData.description],
      s_sensor_fk: [locData.sensorIdFk]
    });   //, 


  }
  submit() {
    this.errorMessage = "";
    this.submitted = true;
    console.log(this.sensorLocForm);
    if (this.sensorLocForm.invalid) {
      return;
    }
    this.loading = true;

    let val = this.sensorLocForm.value;

    console.log("form values: ", val);
    let addLocationObj = this.location;
    addLocationObj.description = val.s_description;
    // addLocationObj.sensorIdFk = -1;
    addLocationObj.sensorLocation = val.s_location;

    console.log("Sending: ", addLocationObj);

    this.sensorService.addSensorLocation(addLocationObj).subscribe(result => {
      this.loading = false;
      this.submitted = false;
      console.log("result: ", result);
      this.router.navigate(['/location']);
    },
      err => {
        this.loading = false;
        this.submitted = false;
        console.log("Error: ", err);
        this.errorMessage = err.error.description + '<br/>' + err.message;
      });

  }
  get frm() { return this.sensorLocForm.controls; }

  cancelForm($event: any) {
    $event.preventDefault(); //to not sub,mit the form

    this.router.navigate(['/location']);
  }

  deleteSensorRelation($event: any) {
    $event.preventDefault(); //to not sub,mit the form
    let yes = confirm("Are you sure?");

    if (yes){
    }
    console.log("deleteSensoreRelation")
  }

}

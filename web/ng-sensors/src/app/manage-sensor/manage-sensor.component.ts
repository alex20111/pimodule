import { Sensor, SensorLoc, SensorService } from './../services/sensor.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NavigationExtras, Router } from '@angular/router';
import { numberValidator } from '../_helper/number.validator';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-manage-sensor',
  templateUrl: './manage-sensor.component.html',
  styleUrls: ['./manage-sensor.component.css']
})
export class ManageSensorComponent implements OnInit {

  action: string = "";
  sensorId: string = "";

  errorMessage: string = "";
  resultMessage: string = "";

  sensorForm: FormGroup;
  submitted: boolean = false;

  loadingLocsList: boolean = false;
  loading: boolean  = false;

  sensor: Sensor = new Sensor();

  locList: SensorLoc[] = [];
  powerSaveTime: PsTime[] = [];

  faTrashAlt = faTrashAlt;

  constructor(private sensorService: SensorService, private formBuilder: FormBuilder,  private router: Router) { }

  ngOnInit(): void {

    this.action = window.history.state.action;
    this.sensorId = window.history.state.id;

    console.log(window.history.state.action,window.history.state.id );
    
    this.generateForm(this.sensor);

    //load  from database
    this.sensorService.loadSensorById(this.sensorId.toString()).subscribe(result => {
      let data = result as Sensor;

      if (data.id > 0) {
        console.log("loadSensorById data ", data);
        this.sensor = result as Sensor;
        this.generateForm(data);
      }

      //check if we need to load the sensor location list if no location exist
      if (!data.sensorLocation){
        this.sensor.sensorLocation = new SensorLoc();
        this.loadSensorLocationList();
      }
    },
      err => {
        this.errorMessage = err.error.description + '<br/>' + err.message;
      });

     this.powerSaveHourList();

  }
  

  generateForm(sensorData: Sensor) {
    this.sensorForm = this.formBuilder.group({
      s_trans_freq: [sensorData.transFreq, [Validators.required, Validators.min(0), Validators.max(120),  Validators.pattern("^[0-9]*$"),]],
      s_power_save: [sensorData.powerSave],
      s_ps_start: [sensorData.pwSaveStart],
      s_ps_end: [sensorData.pwSaveEnd],
      s_ps_trans_freq: [sensorData.pwSaveTransFreq],
      description: [sensorData.description],
      s_location_id: sensorData.sensorLocation ? sensorData.sensorLocation.id : -1 //actually the sensor location ID passed here.
    });   //, 


  }

  submit(){
    this.errorMessage = "";
    this.submitted = true;
    console.log(this.sensorForm);
    if (this.sensorForm.invalid) {
      return;
    }  
    this.loading = true;

    let val = this.sensorForm.value;

    //update from values
    this.sensor.description = val.description;
    this.sensor.transFreq = val.s_trans_freq;
    this.sensor.powerSave = val.s_power_save;
    this.sensor.pwSaveStart = val.s_ps_start;
    this.sensor.pwSaveEnd = val.s_ps_end;
    this.sensor.pwSaveTransFreq = val.s_ps_trans_freq;
   
    //since we have the ID we need to re-ini the location with the current saved ID. 
    //so that the update methode find the right ID to update.
    let locIdValue = parseInt(val.s_location_id);
    if (locIdValue !== this.sensor.sensorLocation.id){
      this.sensor.sensorLocation = this.locList.find(x => x.id === locIdValue);
    }    
   
// console.log("this.locList:", this.locList, locIdValue);

    
    //check if we found a sensor .. if not re-ini
    if (!this.sensor.sensorLocation){
      this.sensor.sensorLocation = new SensorLoc();
    }
    
    console.log("Updated sensor: " , this.sensor);

    this.sensorService.updateSensor(this.sensor).subscribe( result => {
      console.log("resuit: " , result);
      this.loading = false;
      const navigationExtras: NavigationExtras = {state: {saveResult: result.description}};
      this.router.navigate(['/'], navigationExtras);
    },
    err => {
      console.log("update error: " , err);
      this.errorMessage = err.error.description + '<br/>' + err.message;
      this.loading = false;
      this.submitted = false;
    });
 
  }
  onPowerSaveCheckboxChange(){
    let psChkbx = this.sensorForm.get('s_power_save').value;

    if(psChkbx) {
      this.sensorForm.get('s_ps_trans_freq').setValidators(this.psFreqValidator); // 5.Set Required Validator
      this.sensorForm.get('s_ps_trans_freq').updateValueAndValidity();
    } else {
      this.sensorForm.get('s_ps_trans_freq').clearValidators(); // 6. Clear All Validators
      this.sensorForm.get('s_ps_trans_freq').updateValueAndValidity();
    }
  }

  powerSaveHourList(){
    for(let i = 1; i <=24 ; i++){
      let display: string = "";
      if (i < 10){
        display = '0'+i+':00';
      }else{
        display = i+':00';
      }
      let time = new PsTime(display,i.toString());
      this.powerSaveTime.push(time);
    }
  }

  cancelForm($event: any) {
    $event.preventDefault(); //to not sub,mit the form

    this.router.navigate(['/']);
  }

  deleteLocationRelation($event: any){
    $event.preventDefault(); //to not sub,mit the form
    let yes = confirm("Are you sure?");

    if (yes){
      this.loading = true;
      // console.log("Remove this location: " , this.sensor.sensorLocation);
      let toUpdLocation = this.sensor.sensorLocation;
      toUpdLocation.sensorIdFk = -1;
      this.sensorService.updateLocation(toUpdLocation).subscribe(result => {
        this.sensor.sensorLocation = new SensorLoc();
        this.loadSensorLocationList();
       
        this.sensorForm.controls.s_location_id.setValue(-1);
      },
      err => {
        this.errorMessage = err.error.description + '<br/>' + err.message;
        console.log("error: " , err);
        this.loading = false;
      });
    }
  }

  loadSensorLocationList(){
    this.sensorService.loadAllSensorLocation("ALL_LOC_FREE").subscribe(result => {
      console.log("result location", result);
      this.locList = result;
      this.loading = false;
    }, 
    err =>{
      this.errorMessage = err.error.description + '<br/>' + err.message;
      console.log("error: " , err);
      this.loading = false;
    })
  }

  get frm() { return this.sensorForm.controls; }

  private psFreqValidator = [
    Validators.required, Validators.min(0), Validators.max(120),  Validators.pattern("^[0-9]*$")
];

}

export class PsTime{
  hourDisplay : string = "";
  hourValue: string = "";
  constructor(hour: string, value: string){
    this.hourDisplay = hour;
    this.hourValue = value;
  }
}

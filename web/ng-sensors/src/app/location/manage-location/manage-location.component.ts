import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SensorLoc, SensorService } from 'src/app/services/sensor.service';

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
  location: SensorLoc;

  sensorLocForm: FormGroup;

  constructor(private sensorService: SensorService, private formBuilder: FormBuilder,  private router: Router) { }

  ngOnInit(): void {

    this.action = window.history.state.action;
    this.locId = window.history.state.id;
    console.log("started locations,action: ", this.action, this.locId);

    if (this.action === 'add'){
      this.generateForm(new SensorLoc());
    }else{
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
  get frm() { return this.sensorLocForm.controls; }

  cancelForm($event: any) {
    $event.preventDefault(); //to not sub,mit the form

    this.router.navigate(['/location']);
  }

}

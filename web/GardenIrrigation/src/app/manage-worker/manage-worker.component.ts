import { Sensor, GardenService } from './../services/garden.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GardenWorker, ScheduleType } from '../services/garden.service';
import { NgbCalendar, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { faCalendarAlt } from '@fortawesome/free-regular-svg-icons';
import { Router } from '@angular/router';


@Component({
  selector: 'app-manage-worker',
  templateUrl: './manage-worker.component.html',
  styleUrls: ['./manage-worker.component.css']
})
export class ManageWorkerComponent implements OnInit {
  errorMessage: string = "";
  dateModel: NgbDateStruct = this.calendar.getToday();
  time = { hour: 13, minute: 30 };

  action: string = "";
  workerId: string = "";
  worker: GardenWorker = new GardenWorker();

  listOfSchedule: Schedule[] = [];
  unassignedSensors: Sensor[] = [];

  submitted: boolean = false;
  loading: boolean = false;
  workerForm: FormGroup = this.generateForm(new GardenWorker());

  faCalendarAlt = faCalendarAlt;

  constructor(private formBuilder: FormBuilder, private gardenService: GardenService, private router: Router,
    private calendar: NgbCalendar) { }

  ngOnInit(): void {

    this.action = window.history.state.action;
    this.workerId = window.history.state.id;

    console.log(this.action, " id: ", this.workerId);
    this.listOfSchedule.push(new Schedule("Daily", ScheduleType.DAILY));
    this.listOfSchedule.push(new Schedule("Every 2 days", ScheduleType.TWO_DAYS));
    this.listOfSchedule.push(new Schedule("Every 3 days", ScheduleType.THREE_DAYS));
    this.listOfSchedule.push(new Schedule("Every 4 days", ScheduleType.FOUR_DAYS));
    this.listOfSchedule.push(new Schedule("Every week", ScheduleType.WEEK));



    this.gardenService.loadAllUnassignedSensors().subscribe(data => {
      this.unassignedSensors = data;
    },
      err => {
        console.log("error: ", err);
        this.errorMessage = err.error;
      });

    if ('edit' === this.action) {
      let wkId = parseInt(this.workerId);
      this.gardenService.loadWorker(wkId).subscribe(result => {
        this.worker = result;
        this.workerForm = this.generateForm(result);
        console.log("Edit load result: " , result);
      },
        err => {
          console.log("manage worker error: ", err);
          this.errorMessage = err.error;
        });
    }


  }

  generateForm(workerData: GardenWorker): FormGroup {
    // const date: NgbDateStruct = { year: 2021, month: 6, day: 14 };

    if (workerData.wateringDate.length > 0) {
      //convert date to the model.
      //date: NgbDateStruct = { year: 2021, month: 6, day: 14 };
      let wd = new Date(Date.parse(workerData.wateringDate));
      console.log("wd  date: ", wd);
      this.dateModel.year = wd.getFullYear();
      this.dateModel.day = wd.getDate();
      this.dateModel.month = wd.getMonth() + 1;

      this.time.hour = wd.getHours();
      this.time.minute = wd.getMinutes();
    }


    let form = this.formBuilder.group({
      w_name: [workerData.name, [Validators.required, Validators.maxLength(30), Validators.minLength(2)]],
      w_scheduleType: [workerData.scheduleType, [Validators.required, Validators.minLength(5)]],
      w_wateringDate: [this.dateModel, [Validators.required]],
      w_wateringTime: [this.time, [Validators.required]],
      w_duration: [workerData.wateringDuration, [Validators.required, Validators.min(1), Validators.max(1440), Validators.pattern("^[0-9]*$")]],
      w_description: [workerData.description],
      w_sensorId: [workerData.sensorIdFk, [Validators.required, Validators.min(0)]]
    });   //, 
    return form;

  }

  validateDate() {
    let val = this.workerForm.value;

    let selectedDate = val.w_wateringDate.year + "-" + val.w_wateringDate.month + "-" + val.w_wateringDate.day;
    let selDate = new Date(selectedDate);

    let currDate = new Date();

    console.log("Current: ", currDate, "Sel date: ", selDate);

  }
  submit() {
    this.errorMessage = "";
    this.submitted = true;
    // console.log(this.workerForm);
    if (this.workerForm.invalid) {
      return;
    }
    this.loading = true;

    let val = this.workerForm.value;

    console.log("Values: ", val, "Action: " , this.action);

    let wrk = new GardenWorker();

    wrk.name = val.w_name;
    wrk.scheduleType = val.w_scheduleType;
    wrk.description = val.w_description;
    wrk.sensorIdFk = val.w_sensorId;
    //date time to string
    let month: string = parseInt(val.w_wateringDate.month) < 10 ? "0"+val.w_wateringDate.month : val.w_wateringDate.month;
    let day: string = parseInt(val.w_wateringDate.day) < 10 ? "0"+val.w_wateringDate.day : val.w_wateringDate.day;
    let hour: string = parseInt(val.w_wateringTime.hour) < 10 ? "0"+val.w_wateringTime.hour : val.w_wateringTime.hour;
    let minutes: string = parseInt(val.w_wateringTime.minute) < 10 ? "0"+val.w_wateringTime.minute : val.w_wateringTime.minute;
    wrk.wateringDate = val.w_wateringDate.year + "-" + month + "-" + day + " " +
    hour + ":" + minutes;

      wrk.wateringDuration = val.w_duration;


    if   ('edit' === this.action) {
      wrk.id = this.worker.id;
      console.log("Updating worker: ", wrk);
      
      this.gardenService.updateWorker(wrk).subscribe(data => {
        // console.log("add object: ", data);
        this.loading = false;
        this.submitted = false;
        this.router.navigate(['/list']);
      },
        err => {
          console.log("err in update", err);
          this.loading = false; 
          this.submitted = false;
          this.errorMessage = err.error;
        });
    } else {
      console.log("adding worker: ", wrk);
      this.gardenService.addWorker(wrk).subscribe(data => {
        // console.log("add object: ", data);
        this.loading = false;
        this.submitted = false;
        this.router.navigate(['/list']);
      },
        err => {
          console.log("err in add", err);
          this.loading = false; this.submitted = false;
          this.errorMessage = err.error;
        });
    }
  }

  cancelForm($event: any) {
    $event.preventDefault(); //to not sub,mit the form

    this.router.navigate(['/']);
  }

  get formVal() { return this.workerForm.controls; }
}


export class Schedule {
  constructor(public scheduleName: string, public type: ScheduleType) { }
}
import { GardenWorker, GardenService, ScheduleType } from './../services/garden.service';
import { Component, OnInit } from '@angular/core';
import { faEdit, faTrashAlt } from '@fortawesome/free-regular-svg-icons';

@Component({
  selector: 'app-worker-list',
  templateUrl: './worker-list.component.html',
  styleUrls: ['./worker-list.component.css']
})
export class WorkerListComponent implements OnInit {
  resultMessage: string = "";
  resultError: string = "";

  workerList: GardenWorker[] = [];

  faEdit = faEdit;
  faTrashAlt = faTrashAlt;

  constructor(private gardenService: GardenService) { }

  ngOnInit(): void {
    console.log("Start worker list");

    this.gardenService.loadAllWorkers().subscribe(data => {
      this.workerList = data;
      console.log("Got data worker list");
    },
      err => {
        console.log("error in list workers:", err);
        this.resultError = err.error;
      });
  }

  deleteWorker(workerId: number){

    let yes = confirm("Are you sure?");

    if (yes){
     this.gardenService.deleteWorker(workerId).subscribe(data => {
      console.log("delete success data " , data);
      this.workerList.forEach((w,index)=>{
        if(w.id==workerId)  this.workerList.splice(index,1);
    });
     }, 
     err => {
       console.log("worker list delete error" , err);
       this.resultError = err.error;
     });
    }
  }

  sched(scheduleType: string): string{
    // console.log(scheduleType);

    if (scheduleType == ScheduleType.DAILY){
      // console.log("daily")
      return "Daily";
    }else if (scheduleType == ScheduleType.TWO_DAYS){
      return "Every 2 days";
    }else if (scheduleType == ScheduleType.THREE_DAYS){
      return "Every 3 days";
    }else if (scheduleType == ScheduleType.FOUR_DAYS){
      return "Every 4 days";
    }else if (scheduleType == ScheduleType.WEEK){
      return "Every week";
    }

    return "none";
  }

}

import { GardenService, GardenWorker } from './../services/garden.service';
import { Component, OnDestroy, OnInit } from '@angular/core';

import { faPowerOff, faWifi } from '@fortawesome/free-solid-svg-icons';
import { Subscription, timer } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit, OnDestroy {
  isMenuCollapsed: boolean = false;
  // doNextCall: boolean = true;
  allWorkers: GardenWorker[] = [];
  allWorkersTimer: Subscription | undefined;

  loadingAllWorkers : boolean = true;
  submitWaterLoading: boolean = false;

  faPowerOff = faPowerOff;
  faWifi = faWifi;


  constructor(private gardenService: GardenService) { }
  ngOnDestroy(): void {
    if (this.allWorkersTimer){
      this.allWorkersTimer.unsubscribe();
    }
  }



  ngOnInit(): void {
    // const nums = of(1, 2, 3, 4, 5);
    //   // Create a function that accepts an Observable.
    // const squareOddVals = pipe(
    //   filter((n: number) => n % 2 !== 0),
    //   map(n => n * n)
    // );

    // const squareOdd = squareOddVals(nums);

    // squareOdd.subscribe(x => console.log(x));

    this.allWorkersTimer = timer(0, 5000).
    pipe(
      mergeMap(() => this.gardenService.loadAllWorkers())).subscribe(data => {
        this.allWorkers = data;
        console.log("Main page data: ", data);
        this.loadingAllWorkers = false;
  },
    err => {
      console.log("Main page error: ", err);
        this.loadingAllWorkers = false;
    });

  }

  water(workerId: number) {

    this.submitWaterLoading = true;
    let currWorker = this.allWorkers.find(w => w.id = workerId);

    if (currWorker) {

      this.gardenService.activateWater(workerId, !currWorker.status.watering).subscribe(result => {
        console.log("Turn on water result: ", result);

        let waterOn = false;

        if (result === 'TURN_OFF_SUCCESS'){
          waterOn = false;
        }else if (result === 'TURN_ON_SUCCESS'){
          waterOn = true;
        }else{
          console.log("eror!!!!!!!!! " , result);
        }

        this.allWorkers.forEach(w => {
          if (w.id == workerId) {
            w.status.watering = waterOn;
          }
        });
        this.submitWaterLoading = false;
      },
        err => {
          console.error("turn on water error", err);
          this.submitWaterLoading = false;
        });
    } else {
      console.log("Water activation water() error not found");
    }

  }


}

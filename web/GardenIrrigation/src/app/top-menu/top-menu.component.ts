import { GardenService, Sensor } from './../services/garden.service';

import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { faChevronCircleRight, faChevronCircleLeft, faExclamationTriangle } from '@fortawesome/free-solid-svg-icons';
import { interval, Subscription, timer } from 'rxjs';
import { mergeMap } from 'rxjs/operators';


@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html',
  styleUrls: ['./top-menu.component.css']
})
export class TopMenuComponent implements OnInit, OnDestroy {
  //navbar variables
  menuCollapse: boolean = false;
  @Output() valueChange = new EventEmitter();
  navMenuBarCollapsed: boolean = true;

  //new badge for unassociated sensors
  newSensors: Sensor[] = [];
  newWorkerTimer: Subscription | undefined;


  //icons
  faChevronCircleRight = faChevronCircleRight;
  faChevronCircleLeft = faChevronCircleLeft;
  faExclamationTriangle = faExclamationTriangle;

  constructor(private gardenService: GardenService) { }

  ngOnInit(): void {


    this.newWorkerTimer = timer(0, 10000).pipe(mergeMap(() => this.gardenService.loadAllUnassignedSensors())).subscribe(data => {
      console.log("Unassigned sensors Data: ", data);
      this.newSensors = data;
    },
      err => {
        console.log("error: ", err)
      });
  }

  checkMessages() {

  }

  changeValue() {
    // You can give any function name
    this.menuCollapse = !this.menuCollapse;
    this.valueChange.emit(this.menuCollapse);
  }

  ngOnDestroy() {
    if (this.newWorkerTimer) {
      this.newWorkerTimer.unsubscribe();
    }
  }

}

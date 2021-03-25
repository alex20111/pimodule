import { Sensor, SensorService } from './../services/sensor.service';
import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { faChevronCircleRight, faChevronCircleLeft, faExclamationTriangle } from '@fortawesome/free-solid-svg-icons';
import { Message } from '../_helper/Message';

@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html',
  styleUrls: ['./top-menu.component.css']
})
export class TopMenuComponent implements OnInit, OnDestroy {

  menuCollapse: boolean = false;
  @Output() valueChange = new EventEmitter();

  navMenuBarCollapsed: boolean = true;

  msgErrorWarningCard: Message[] = [];
  intervalId;

  faChevronCircleRight = faChevronCircleRight;
  faChevronCircleLeft = faChevronCircleLeft;
  faExclamationTriangle = faExclamationTriangle;

  constructor(private sensorService: SensorService) { }

  ngOnInit(): void {

    //go fetch any errors or warning:
    this.checkMessages();
    this.intervalId = setInterval(() => {

      this.checkMessages();
     
    }, 5000);
    // let m: Message = {
    //   message: "ERROR: this is ",
    //   description: "bla bla"
    // }

    // let m2: Message = {
    //   message: "WARNING: this is ",
    //   description: "bla bla"
    // }

    // this.msgErrorWarningCard.push(m);
    // this.msgErrorWarningCard.push(m2);
  }

  checkMessages(){
    this.sensorService.messages().subscribe(msg => {
      this.msgErrorWarningCard = msg;
    },
      err => {
        console.log("error: ", err);
      });
  }

  changeValue() {
    // You can give any function name
    this.menuCollapse = !this.menuCollapse;
    this.valueChange.emit(this.menuCollapse);
  }

  ngOnDestroy() {
    clearInterval(this.intervalId);
  }

}

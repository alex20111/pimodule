import { Sensor, SensorService } from './../services/sensor.service';
import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { faBars, faChevronCircleRight, faChevronCircleLeft } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit, OnDestroy {

  sensorList: Sensor[] = [];


  dropDownCollapsed: boolean = true;
  navMenuBarCollapsed: boolean = true;
  menuCollapse: boolean = false;

  //icons
  faBars = faBars;
  faChevronCircleRight = faChevronCircleRight;
  faChevronCircleLeft = faChevronCircleLeft;

  constructor(private sensorService: SensorService) { }

  ngOnInit(): void {
    
    this.sensorService.loadAllSensors().subscribe(result => {
      console.log("Sersor service result: " , result);
      this.sensorList = result;
    },
    err =>{
      console.error("error in loadAllSensors", err);
    });


    // intervalId = setInterval(this.opensnack(text), 10000);


  }
 
  @HostListener('window:resize', ['$event'])
  onResize(event) {
    
    if(event.target.innerWidth < 988){ //px
      // console.log(event);
      this.menuCollapse = true;
    }
    
  }

  ngOnDestroy() {
    // clearInterval(this.intervalId);
  }

}

import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'GardenIrrigation';

  menuCollapse: boolean = false;


  topMenuCollapse(col: any) { //event coming from the child to the parent to collapse the menu
    this.menuCollapse = col;

  }
}

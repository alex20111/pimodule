import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ng-sensors';
  menuCollapse: boolean = false;


  topMenuCollapse(col) { //event coming from the child to the parent to collapse the menu
    this.menuCollapse = col;

  }
}

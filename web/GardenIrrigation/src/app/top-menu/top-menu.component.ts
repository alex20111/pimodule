
import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { faChevronCircleRight, faChevronCircleLeft, faExclamationTriangle } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html',
  styleUrls: ['./top-menu.component.css']
})
export class TopMenuComponent implements OnInit, OnDestroy {

  menuCollapse: boolean = false;
  @Output() valueChange = new EventEmitter();

  navMenuBarCollapsed: boolean = true;

  // msgErrorWarningCard: Message[] = [];
  // intervalId;

  faChevronCircleRight = faChevronCircleRight;
  faChevronCircleLeft = faChevronCircleLeft;
  faExclamationTriangle = faExclamationTriangle;

  constructor() { }

  ngOnInit(): void {

   }

  checkMessages(){

  }

  changeValue() {
    // You can give any function name
    this.menuCollapse = !this.menuCollapse;
    this.valueChange.emit(this.menuCollapse);
  }

  ngOnDestroy() {

  }

}

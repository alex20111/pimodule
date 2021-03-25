import { Component,  Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.css']
})
export class SideMenuComponent implements OnInit {

  private _menuCollapse: boolean = false;
  @Input()
  set menuCollapse(menuCollapse: boolean) {
    this._menuCollapse = menuCollapse;
    console.log(this._menuCollapse);
  }

  dropDownCollapsed: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }


  get menuCollapse(): boolean { return this._menuCollapse; }

  // @HostListener('window:resize', ['$event'])
  // onResize(event) {
  //   console.log(this.menuCollapse);

  //   if(event.target.innerWidth < 988){ //px
  //     // console.log(event);
  //     this.menuCollapse = true;

  //   }else{
  //     this.menuCollapse = false;
  //   }

  // }

}

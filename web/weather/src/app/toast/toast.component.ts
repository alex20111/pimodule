import { Constants } from '../_model/constants';
import { ToastService } from './../services/toast.service';
import {Component, Inject, TemplateRef} from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-toast',
  template: `
    <ngb-toast
      *ngFor="let toast of toastService.toasts"
      [header]="toast.headertext"
      [class]="toast.classname"
      [autohide]="toast.autohide"
      [delay]="toast.delay || 5000"
      (hide)="toastService.remove(toast)"
      (click)="action(toast.navigate)"
    >
      <ng-template [ngIf]="isTemplate(toast)" [ngIfElse]="text">
        <ng-template [ngTemplateOutlet]="toast.textOrTpl"></ng-template>
      </ng-template>

      <ng-template #text>{{ toast.textOrTpl }}</ng-template>
    </ngb-toast>
  `,
  host: {'[class.ngb-toasts]': 'true'}
})
export class ToastComponent {
  elem;
  constructor(public toastService: ToastService, @Inject(DOCUMENT) private document: any, private router: Router,) {}

  isTemplate(toast): boolean { return toast.textOrTpl instanceof TemplateRef; }

  fullScreen(): void{
    this.elem = document.documentElement;

    if (this.elem.requestFullscreen) {
      this.elem.requestFullscreen();
    } else if (this.elem.mozRequestFullScreen) {
      /* Firefox */
      this.elem.mozRequestFullScreen();
    } else if (this.elem.webkitRequestFullscreen) {
      /* Chrome, Safari and Opera */
      this.elem.webkitRequestFullscreen();
    } else if (this.elem.msRequestFullscreen) {
      /* IE/Edge */
      this.elem.msRequestFullscreen();
    }

    this.toastService.remove2(Constants.FULL_SCREEN_INDX);

  }

  action(navigate: string): void{

    if (navigate === 'fullscreen'){
      this.fullScreen();
    }else{
      this.router.navigate([navigate]);
      this.toastService.remove2(Constants.BACK_INDX);
    }
  }
}
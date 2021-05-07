// toast.service.ts
import { Injectable, TemplateRef  } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  toasts: any[] = [];
  fullscreen = false;

  // Push new Toasts to array with content and options
  show(textOrTpl: string | TemplateRef<any>, options: any = {}) {
    this.toasts.push({ textOrTpl, ...options });
  }

  // Callback method to remove Toast DOM element from view
  remove(toast): void {
    this.toasts = this.toasts.filter(t => t !== toast);
  }

  show2(): void {
    console.log("toasts: " , this.toasts);
  }
  // Callback method to remove Toast DOM element from view
  remove2(key: number): void  {
    this.toasts = this.toasts.filter(t => t.removeKey !== key);
  }


}

import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PiWebSocketService {

  myWebSocket: WebSocketSubject<any>;
  
  constructor() { }


  connect(): Observable<any> {
    this.myWebSocket = webSocket('ws://192.168.1.110:8081/events/');

    const message = {
      operation: 1,
      userName: 'ang'
    };
    this.sendMessage(message);
    return this.myWebSocket.asObservable();
  }

  sendMessage(message: any): void {
    console.log('Sending: ', message);
    this.myWebSocket.next(message);
  }

  closeSocket(): void {
    this.myWebSocket.complete();
  }
}

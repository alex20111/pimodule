import { TestBed } from '@angular/core/testing';

import { PiWebSocketService } from './pi-web-socket.service';

describe('PiWebSocketService', () => {
  let service: PiWebSocketService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PiWebSocketService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

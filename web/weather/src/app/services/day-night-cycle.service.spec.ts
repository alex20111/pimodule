import { TestBed } from '@angular/core/testing';

import { DayNightCycleService } from './day-night-cycle.service';

describe('DayNightCycleService', () => {
  let service: DayNightCycleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DayNightCycleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

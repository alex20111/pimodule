import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageSensorComponent } from './manage-sensor.component';

describe('ManageSensorComponent', () => {
  let component: ManageSensorComponent;
  let fixture: ComponentFixture<ManageSensorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageSensorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageSensorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

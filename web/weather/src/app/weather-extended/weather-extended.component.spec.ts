import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WeatherExtendedComponent } from './weather-extended.component';

describe('WeatherExtendedComponent', () => {
  let component: WeatherExtendedComponent;
  let fixture: ComponentFixture<WeatherExtendedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WeatherExtendedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WeatherExtendedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

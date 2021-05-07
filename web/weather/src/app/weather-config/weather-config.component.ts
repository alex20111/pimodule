
import { numberValidator } from 'src/app/_helpers/number.validator';
import { City, ConfigService } from './../services/config.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-weather-config',
  templateUrl: './weather-config.component.html',
  styleUrls: ['./weather-config.component.css']
})
export class WeatherConfigComponent implements OnInit {


  cityList: City[] = [];

  configForm: FormGroup;
  error: string;

  submit = false;
  loadingCities = true;


  constructor(private formBuilder: FormBuilder, private cfg: ConfigService, private router: Router) {
    }

  ngOnInit(): void {
    this.configForm = this.formBuilder.group({
      city: [this.cfg.getCurrentCity(), [Validators.required]],
      frm_refresh: [this.cfg.getRefreshTimeMinutes(), [Validators.required, Validators.min(1), Validators.max(60)]]
    }, {
      validator: numberValidator('frm_refresh')
    });

    this.cfg.getEnvCanCity().subscribe(
      cities => {
        this.cityList = cities;
        this.loadingCities = false;
      },
      fetchError => {
        console.log('City fetch error', fetchError);
        this.error = 'Error fetching cities';
      }
    )
  }

  onSubmit(): void {
    console.log('onSubmit', this.configForm.value);

    if (this.configForm.invalid) {
      return;
    }
    this.submit = true;

    const formValue = this.configForm.value;

    this.cfg.updateConfig(formValue.city, Number(formValue.frm_refresh));
    this.router.navigate(['/']);

  }

  onCancel(): void {
    this.router.navigate(['/']);
  }

}

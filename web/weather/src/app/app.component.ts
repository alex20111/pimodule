import { Component, OnInit, Inject } from '@angular/core';
import { Constants } from './_model/constants';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'weather';

  constructor(){
    // get from local storage to see if we have stored a logFileName.
    const logFileName = localStorage.getItem(Constants.LOG_FILE_NAME_STORAGE);
    if (!logFileName){
      const logF = `AngWeatherLog${Math.floor((Math.random() * 1000) + 1)}`;
      localStorage.setItem(Constants.LOG_FILE_NAME_STORAGE, logF);
      Constants.WEATHER_LOG_FILENAME = logF;
    }else{
      Constants.WEATHER_LOG_FILENAME = logFileName;
    }

    console.log('Log name: ' , Constants.WEATHER_LOG_FILENAME);

  }
  ngOnInit(): void {
   
  }



}

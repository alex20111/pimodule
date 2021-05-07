import { Cycle } from './../_enums/cycle';
import { DayNightCycleService } from './day-night-cycle.service';
import { Forecast } from './../_model/forecast';
import { BackImage } from './../_enums/bckImage';
import { Injectable } from '@angular/core';
import { Icons } from '../_enums/icons';
import { getIconsFromString } from './../_helpers/IconHelper';

export class ImageMap {
  public constructor(private imageNameDay: string, private imageNameNight: string) { }

  public getImage(cycle: Cycle): string {
    return cycle === Cycle.NIGHT ? this.imageNameNight : this.imageNameDay;
  }
}

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private envCanWeatherIconMap: Map<Icons, ImageMap>; // map containing name of the weather icon and their file names
  private envCanBackgroundImgMap: Map<BackImage, ImageMap>;

  pathToIcon = 'assets/icon/';
  pathToBackground = 'assets/bck/';

  constructor(private cycleService: DayNightCycleService) {
    // build map for Env canada
    this.buildIconMap();
    this.buildBackgroundMap();
  }

  getWeatherIcon(weather: string): string {
    /* return the weather icon based on the weather condition. Looking for keywords */
    const icn = this.envCanWeatherIconMap.get(getIconsFromString(weather)).getImage(this.cycleService.cycle);

    return this.pathToIcon.concat(icn);
  }

  getIconsForForecast(forecasts: Forecast[]): Forecast[] {

    for (const f of forecasts) {
      if (f.dayOfWeek.search('night') !== -1) {
        f.iconLink = this.pathToIcon + this.envCanWeatherIconMap.get(getIconsFromString(f.weather)).getImage(Cycle.NIGHT);
      } else {
        f.iconLink = this.pathToIcon + this.envCanWeatherIconMap.get(getIconsFromString(f.weather)).getImage(Cycle.DAY);
      }
    }
    return forecasts;
  }

  getBackgroundImage(weather: string): string {

    let image = '';
    if (weather.search('Rain') !== -1) {
      image = this.envCanBackgroundImgMap.get(BackImage.RAIN).getImage(this.cycleService.cycle);
    } else if (weather.search('Sunny') !== -1) {
      image = this.envCanBackgroundImgMap.get(BackImage.SUNNY).getImage(this.cycleService.cycle);
    } else if (weather.search('Cloudy') !== -1) {
      image = this.envCanBackgroundImgMap.get(BackImage.CLOUDY).getImage(this.cycleService.cycle);
    } else if (weather.search('Thunder') !== -1) {
      image = this.envCanBackgroundImgMap.get(BackImage.THUNDER).getImage(this.cycleService.cycle);
    } else if (weather.search('Snow') !== -1) {
      image = this.envCanBackgroundImgMap.get(BackImage.SNOW).getImage(this.cycleService.cycle);
    } else if (weather.search('Fog') !== -1 || weather.search('Mist') !== -1)   {
      image = this.envCanBackgroundImgMap.get(BackImage.FOG).getImage(this.cycleService.cycle);
    } else {
      image = this.envCanBackgroundImgMap.get(BackImage.SUNNY).getImage(this.cycleService.cycle);
    }
    // console.log('getBackgroundImage: ', this.pathToBackground, image, weather);
    return this.pathToBackground.concat(image);
  }

  private buildBackgroundMap(): void {
    this.envCanBackgroundImgMap = new Map([
      [BackImage.CLOUDY, new ImageMap('cloudy-day.jpg', 'cloudy-night.jpg')],
      [BackImage.SUNNY, new ImageMap('sunny.jpg', 'clear-night.jpg')],
      [BackImage.THUNDER, new ImageMap('thunder-day.jpg', 'thunder-night.jpg')],
      [BackImage.SNOW, new ImageMap('snowing-day.jpg', 'snow-night.jpg')],
      [BackImage.RAIN, new ImageMap('rain.jpg', 'rain-night.jpg')],
      [BackImage.FOG, new ImageMap('fog-day.jpg', 'fog-night.jpg')],
    ]);
  }

  private buildIconMap(): void {
    // build map of env canada weather summary to their respective icons file name
    this.envCanWeatherIconMap = new Map([
      [Icons.NA, new ImageMap('not-available.png', 'not-available.png')],
      [Icons.CLOUDY, new ImageMap('cloudy.svg', 'cloudy.svg')],
      [Icons.MOSTLY_CLOUDY, new ImageMap('cloudy-day-3.svg', 'cloudy-night-3.svg')],
      [Icons.SUNNY, new ImageMap('day.svg', 'night.svg')],
      [Icons.MAINLY_SUNNY, new ImageMap('cloudy-day-1.svg', 'cloudy-night-1.svg')],
      [Icons.FOG, new ImageMap('cloudy.svg', 'cloudy.svg')],
      [Icons.LIGHT_RAIN, new ImageMap('rainy-4.svg', 'rainy-4.svg')],
      [Icons.LIGHT_SNOW, new ImageMap('snowy-4.svg', 'snowy-4.svg')],
      [Icons.MIST, new ImageMap('cloudy.svg', 'cloudy.svg')],
      [Icons.PARTYL_CLOUDY, new ImageMap('cloudy-day-2.svg', 'cloudy-night-2.svg')],
      [Icons.RAIN, new ImageMap('rainy-5.svg', 'rainy-5.svg')],
      [Icons.CLEAR, new ImageMap('day.svg', 'night.svg')],
      [Icons.CHANCE_OF_SHOWERS, new ImageMap('rainy-2.svg', 'rainy-5.svg')],
      [Icons.A_MIX_OF_SUN_AND_CLOUD, new ImageMap('cloudy-day-2.svg', 'cloudy-night-2.svg')],
      [Icons.PERIODS_OF_RAIN, new ImageMap('rainy-5.svg', 'rainy-5.svg')],
      [Icons.SHOWERS, new ImageMap('rainy-6.svg', 'rainy-6.svg')],
      [Icons.A_FEW_CLOUDS, new ImageMap('cloudy-day-1.svg', 'cloudy-night-1.svg')],
      [Icons.CLOUDY_PERIODS, new ImageMap('cloudy-day-3.svg', 'cloudy-night-3.svg')],
      [Icons.LIGHT_DRIZZLE, new ImageMap('rainy-4.svg', 'rainy-4.svg')],
      [Icons.MAINLY_CLEAR, new ImageMap('cloudy-day-1.svg', 'cloudy-night-1.svg')],
      [Icons.LIGHT_RAIN_AND_FOG, new ImageMap('rainy-4.svg', 'rainy-4.svg')],
      [Icons.RAIN_OR_DRIZZLE, new ImageMap('rainy-5.svg', 'rainy-5.svg')],
      [Icons.RAIN_AT_TIMES_HEAVY_OR_DRIZZLE, new ImageMap('rainy-6.svg', 'rainy-6.svg')],
      [Icons.RAIN_AT_TIMES_HEAVY, new ImageMap('rainy-6.svg', 'rainy-6.svg')],
      [Icons.A_FEW_SHOWERS_OR_DRIZZLE, new ImageMap('rainy-4.svg', 'rainy-4.svg')],
      [Icons.SHOWERS_OR_DRIZZLE, new ImageMap('rainy-6.svg', 'rainy-6.svg')],
      [Icons.A_FEW_SHOWERS, new ImageMap('rainy-5.svg', 'rainy-5.svg')],
      [Icons.LIGHT_RAINSHOWER, new ImageMap('rainy-4.svg', 'rainy-4.svg')],
      [Icons.HAZE, new ImageMap('cloudy.svg', 'cloudy.svg')],
      [Icons.FOG_PATCHES, new ImageMap('cloudy.svg', 'cloudy.svg')],
      [Icons.CLEARING, new ImageMap('day.svg', 'night.svg')],
      [Icons.FOG_DISSIPATING, new ImageMap('cloudy-day-3.svg', 'cloudy-night-3.svg')],
      [Icons.INCREASING_CLOUDINESS, new ImageMap('cloudy-day-1.svg', 'cloudy-night-1.svg')],
      [Icons.THUNDERSTORM_WITH_LIGHT_RAINSHOWERS, new ImageMap('thunder.svg', 'thunder.svg')],
      [Icons.CHANCE_OF_RAIN_SHOWERS_OR_FLURRIES, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
      [Icons.CHANCE_OF_FLURRIES, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
      [Icons.SNOW_OR_RAIN, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
      [Icons.LIGHT_SNOWSHOWER, new ImageMap('snowy-2.svg', 'snowy-5.svg')],
      [Icons.RAIN_OR_SNOW, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
      [Icons.CHANCE_OF_FLURRIES_OR_RAIN_SHOWERS, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
      [Icons.FLURRIES_OR_RAIN_SHOWERS, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
      [Icons.PERIODS_OF_RAIN_MIXED_WITH_SNOW, new ImageMap('rainy-2.svg', 'rainy-5.svg')],
      [Icons.A_FEW_FLURRIES, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
      [Icons.PERIODS_OF_SNOW, new ImageMap('snowy-2.svg', 'snowy-5.svg')],
      [Icons.PERIODS_OF_RAIN_OR_DRIZZLE, new ImageMap('rainy-2.svg', 'rainy-5.svg')],
      [Icons.CHANCE_OF_DRIZZLE_OR_RAIN, new ImageMap('rainy-2.svg', 'rainy-5.svg')],
      [Icons.SNOW, new ImageMap('snowy-6.svg', 'snowy-6.svg')],
      [Icons.OVERCAST, new ImageMap('cloudy.svg', 'cloudy.svg')]
    ]);
  }

}

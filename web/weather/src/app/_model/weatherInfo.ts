import { LocalSensor } from './localSensor';
import { Forecast } from './forecast';
import { WeatherAlert } from './weatherAlert';


export interface WeatherInfo {
    temperature: string;
    UV: string;
    humidex: string;
    wingChill: string;
    wind: string;
    weather: string;
    humidity: string;
    observationTime: string;
    forecast: Forecast[];
    alert: WeatherAlert;
    localtemp: LocalSensor;
}

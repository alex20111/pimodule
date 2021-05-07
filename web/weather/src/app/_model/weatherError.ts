export class WeatherError{
    key: string;
    title: string;
    message: string;

    public constructor(key: string, title: string, message: string){
        this.key = key;
        this.title = title;
        this.message = message;
    }
}
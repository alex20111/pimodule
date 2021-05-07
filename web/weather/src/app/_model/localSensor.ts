export interface LocalSensor{
    tempSun: string;
    tmpSunUpdDt: string;
    tempShade: string;
    tmpShadeUpdDt: string;
    tempPool: string;
    tmpPoolUpdDt: string;
    tempMap: TempMap;
    tempDateMap: TempDateMap;
}

export interface TempMap{
    GARAGE: string;
}

export interface TempDateMap{
    GARAGE: string;
}
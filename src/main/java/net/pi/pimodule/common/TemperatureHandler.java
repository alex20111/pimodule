package net.pi.pimodule.common;

import java.sql.SQLException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.db.TempEntity;
import net.pi.pimodule.db.TempSql;
import net.pi.pimodule.temperature.Temperature;

public class TemperatureHandler {
	
	private static final Logger logger = LogManager.getLogger(TemperatureHandler.class);
	
	private Temperature temperature;

	private static TemperatureHandler th;	
	
	private TemperatureHandler() {
		temperature = new Temperature();
		
		try {
			temperature = new TempSql().getCurrentStoredTemperature();

		} catch (SQLException | ClassNotFoundException e) {
			logger.error("error in getting old temperature" , e);
		} 
		
		logger.info("Last temperature from DB: " + temperature);
	}
	
	public static TemperatureHandler getInstance(){
		if (th == null){
			synchronized (TemperatureHandler.class) {
				if (th == null){
					th = new TemperatureHandler();
				}
			}
		}
		return th;
	}
	
	
	public Temperature getTemperature() {
		return this.temperature;
	}

	public synchronized  void setTemperature(TempEntity t) {
		this.temperature.setProperties(t);
		
	}
	
	public void setInitTemperature() {
		
	}
	
//	public synchronized void setTempProperties(TempEntity t) {
//		TempRecName rec = TempRecName.valueOf(t.getRecorderName());
//
//		if (rec == TempRecName.Pool) {
//			this.temperature.tmpPoolUpdDt = sdf.format(t.getRecordedDate());
//			this.tempPool = (t.getTempC() != null ? formatter.format(Double.valueOf(t.getTempC())) : "-90" );
//
//		}else if (rec == TempRecName.BB) {
//
//			this.tmpSunUpdDt = sdf.format(t.getRecordedDate());
//			this.tempSun = (t.getTempC() != null ? formatter.format(Double.valueOf(t.getTempC())) : "-90" );
//
//		}else if (rec == TempRecName.AA) {
//
//			this.tmpShadeUpdDt = sdf.format(t.getRecordedDate());
//			this.tempShade = (t.getTempC() != null ? formatter.format(Double.valueOf(t.getTempC())) : "-90" );
//		}
//	}
}

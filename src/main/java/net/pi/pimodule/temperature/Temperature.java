package net.pi.pimodule.temperature;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.db.TempEntity;


public class Temperature {

//	private static final Logger logger = LogManager.getLogger(Temperature.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	private NumberFormat formatter = new DecimalFormat("#0.#");

	private  String tempSun = "-99";
	private  String tmpSunUpdDt = "21:45am";
	private  String tempShade = "-99";
	private  String tmpShadeUpdDt = "10:45am";
	private  String tempPool = "-99";
	private  String tmpPoolUpdDt = "11:45am";
	
	//TODO temperature others? in a map? or re-define to have a list of object?  or keep this as legacy and create a new list of object for the new added temperature.

	public Temperature() {}
	public Temperature(TempEntity t) {		

		setProperties(t);
	}

	public void setProperties(TempEntity t) {
		TempRecName rec = TempRecName.valueOf(t.getRecorderName());

		if (rec == TempRecName.pool) {
			this.tmpPoolUpdDt = sdf.format(t.getRecordedDate());
			this.tempPool = (t.getTempC() != null ? formatter.format(Double.valueOf(t.getTempC())) : "-90" );

		}else if (rec == TempRecName.BB) {

			this.tmpSunUpdDt = sdf.format(t.getRecordedDate());
			this.tempSun = (t.getTempC() != null ? formatter.format(Double.valueOf(t.getTempC())) : "-90" );

		}else if (rec == TempRecName.AA) {

			this.tmpShadeUpdDt = sdf.format(t.getRecordedDate());
			this.tempShade = (t.getTempC() != null ? formatter.format(Double.valueOf(t.getTempC())) : "-90" );
		}
	}
	public String getTempSun() {
		return tempSun;
	}
	public void setTempSun(String tempSun) {
		this.tempSun = tempSun;
	}
	public String getTempShade() {
		return tempShade;
	}
	public void setTempShade(String tempShade) {
		this.tempShade = tempShade;
	}
	public String getTempPool() {
		return tempPool;
	}
	public void setTempPool(String tempPool) {
		this.tempPool = tempPool;
	}

	public String getTmpSunUpdDt() {
		return tmpSunUpdDt;
	}
	public void setTmpSunUpdDt(String tmpSunUpdDt) {
		this.tmpSunUpdDt = tmpSunUpdDt;
	}
	public String getTmpShadeUpdDt() {
		return tmpShadeUpdDt;
	}
	public void setTmpShadeUpdDt(String tmpShadeUpdDt) {
		this.tmpShadeUpdDt = tmpShadeUpdDt;
	}
	public String getTmpPoolUpdDt() {
		return tmpPoolUpdDt;
	}
	public void setTmpPoolUpdDt(String tmpPoolUpdDt) {
		this.tmpPoolUpdDt = tmpPoolUpdDt;
	}
	@Override
	public String toString() {
		return "Temperature [tempSun=" + tempSun + ", tmpSunUpdDt=" + tmpSunUpdDt + ", tempShade=" + tempShade
				+ ", tmpShadeUpdDt=" + tmpShadeUpdDt + ", tempPool=" + tempPool + ", tmpPoolUpdDt=" + tmpPoolUpdDt
				+ "]";
	}


}

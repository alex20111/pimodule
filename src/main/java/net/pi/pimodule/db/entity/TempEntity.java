package net.pi.pimodule.db.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TempEntity {

	public static String TBL_NAME = "temperature";
	public static String ID 		= "id";
	public static String TEMP 		= "temp_c";
	public static String REC_DATE 	= "recorded_date";
	public static String REC_NAME 	= "recorder_name";
	public static String BATT_LVL 	= "battery_level";
	public static String HUMIDITY 	= "humidity";
	
	private int     id = -1;
	private String 	tempC 			= null;	
	private Date	recordedDate	= null;	
	private String  recorderName	= "";	
	private String  batteryLevel	= "";	
	private String  humidity		= "";	
	
	public TempEntity(){}	
	
	public TempEntity(ResultSet rs) throws SQLException{
		this.id = rs.getInt(ID);
		this.tempC = rs.getString(TEMP);
		this.recordedDate = rs.getTimestamp(REC_DATE);
		this.recorderName = rs.getString(REC_NAME);
		this.batteryLevel = rs.getString(BATT_LVL);
		this.humidity     = rs.getString(HUMIDITY);
	}
	
	public String getTempC() {
		return tempC;
	}
	public void setTempC(String tempC) {
		this.tempC = tempC;
	}
	public Date getRecordedDate() {
		return recordedDate;
	}
	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}
	public String getRecorderName() {
		return recorderName;
	}
	public void setRecorderName(String recorderName) {
		this.recorderName = recorderName;
	}
	public String getBatteryLevel() {
		return batteryLevel;
	}
	public void setBatteryLevel(String batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	
	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Temperature [");		
		builder.append(", tempC=");
		builder.append(tempC);
		builder.append(", Humidity=");
		builder.append(humidity);
		builder.append(", recordedDate=");
		builder.append(recordedDate);
		builder.append(", recorderName=");
		builder.append(recorderName);
		builder.append(", batteryLevel=");
		builder.append(batteryLevel);
		builder.append("]");
		return builder.toString();
	}

}


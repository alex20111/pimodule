package net.pi.pimodule.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import net.pi.pimodule.enums.SensorType;

public class SensorEntity {
	public static String TBL_NAME 		= "sensor";
	public static String ID 			= "id";
	public static String SENSOR_ID 		= "sensor_id";
	public static String SENSOR_TYPE 	= "sensor_type";
	public static String TRANS_FREQ 	= "trans_freq";
	public static String LAST_TRANSMIT 	= "last_transmit";
	public static String LAST_UPDATED 	= "last_updated";
	public static String POWER_SAVE 	= "power_save";
	public static String POWER_SAVE_ST 	= "power_save_start";
	public static String POWER_SAVE_END	= "power_save_end";
	public static String POWER_SAVE_TRANS_FREQ 	= "power_save_trans_freq";
	public static String BATT_LVL 		= "battery_level";
	public static String CONFIGURED 	= "configured";
	public static String DESCRIPTION 	= "description";
	public static String ERROR_FIELD    = "error_field";
	
	private int     id = -1;
	private String 	sensorId		= "";
	private SensorType sensorType = SensorType.NONE;
	private int 	transFreq		= -1;
	private Date	lastTransmit	= null;
	private Date	lastUpdated		= null;
	private boolean	powerSave		= false;
	private int     pwSaveStart		= -1;
	private int     pwSaveEnd		= -1;
	private int     pwSaveTransFreq = -1;
	private String  battLvl			= "";	
	private boolean configured 		= false;
	private String description		= "";
	private String errorField		= "";
	
	public SensorEntity(){}	
	
	public SensorEntity(ResultSet rs) throws SQLException{
		this.id = rs.getInt(ID);

		this.sensorId		= rs.getString(SENSOR_ID);
		this.sensorType     = SensorType.valueOf(rs.getString(SENSOR_TYPE));
		this.transFreq		= rs.getInt(TRANS_FREQ);
		this.lastTransmit	= rs.getTimestamp(LAST_TRANSMIT);
		this.lastUpdated		= rs.getTimestamp(LAST_UPDATED);
		this.powerSave		= rs.getBoolean(POWER_SAVE);
		this.pwSaveStart		= rs.getInt(POWER_SAVE_ST);
		this.pwSaveEnd			= rs.getInt(POWER_SAVE_END);
		this.pwSaveTransFreq  	= rs.getInt(POWER_SAVE_TRANS_FREQ);
		this.battLvl			= rs.getString(BATT_LVL);	
		this.configured		= rs.getBoolean(CONFIGURED);
		this.description		= rs.getString(DESCRIPTION);
		this.errorField 		= rs.getString(ERROR_FIELD);
	}

	public int getId() {
		return id;
	}

	public String getSensorId() {
		return sensorId;
	}

	public SensorType getSensorType() {
		return sensorType;
	}

	public int getTransFreq() {
		return transFreq;
	}

	public Date getLastTransmit() {
		return lastTransmit;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public boolean isPowerSave() {
		return powerSave;
	}

	public int getPwSaveStart() {
		return pwSaveStart;
	}

	public int getPwSaveEnd() {
		return pwSaveEnd;
	}

	public int getPwSaveTransFreq() {
		return pwSaveTransFreq;
	}

	public String getBattLvl() {
		return battLvl;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	public void setTransFreq(int transFreq) {
		this.transFreq = transFreq;
	}

	public void setLastTransmit(Date lastTransmit) {
		this.lastTransmit = lastTransmit;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void setPowerSave(boolean powerSave) {
		this.powerSave = powerSave;
	}

	public void setPwSaveStart(int pwSaveStart) {
		this.pwSaveStart = pwSaveStart;
	}

	public void setPwSaveEnd(int pwSaveEnd) {
		this.pwSaveEnd = pwSaveEnd;
	}

	public void setPwSaveTransFreq(int pwSaveTransFreq) {
		this.pwSaveTransFreq = pwSaveTransFreq;
	}

	public void setBattLvl(String battLvl) {
		this.battLvl = battLvl;
	}

	public boolean isConfigured() {
		return configured;
	}

	public void setConfigured(boolean configured) {
		this.configured = configured;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getErrorField() {
		return errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}

	@Override
	public String toString() {
		return "SensorEntity [id=" + id + ", sensorId=" + sensorId + ", sensorType=" + sensorType + ", transFreq="
				+ transFreq + ", lastTransmit=" + lastTransmit + ", lastUpdated=" + lastUpdated + ", powerSave="
				+ powerSave + ", pwSaveStart=" + pwSaveStart + ", pwSaveEnd=" + pwSaveEnd + ", pwSaveTransFreq="
				+ pwSaveTransFreq + ", battLvl=" + battLvl + ", configured=" + configured + ", description="
				+ description + ", errorField=" + errorField + "]";
	}



	
}

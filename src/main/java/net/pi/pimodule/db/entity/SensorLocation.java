package net.pi.pimodule.db.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorLocation {

	public static String TBL_NAME 		= "sensor_location";
	public static String ID 			= "id";
	public static String SENSOR_LOCATION= "sensor_loc";
	public static String SENSOR_DESC    = "sensor_descr";
	public static String SENSOR_ID_FK	= "sensor_ID_fk";	
	
	private int     id = -1;
	private String sensorLocation = "";
	private String description = "";
	private int sensorIdFk = -1;
	
	//Transient --> lazy loaded
	private String sensorName = "";
	private int sensorId 	  = -1;
	
	public SensorLocation(){}	
	
	public SensorLocation(ResultSet rs) throws SQLException{
		this.id = rs.getInt(ID);

		this.sensorIdFk			= rs.getInt(SENSOR_ID_FK);
		this.sensorLocation     = rs.getString(SENSOR_LOCATION);
		this.description        = rs.getString(SENSOR_DESC);
	}

	public String getSensorLocation() {
		return sensorLocation;
	}

	public void setSensorLocation(String sensorLocation) {
		this.sensorLocation = sensorLocation;
	}

	public int getId() {
		return id;
	}

	public int getSensorIdFk() {
		return sensorIdFk;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSensorIdFk(int sensorIdFk) {
		this.sensorIdFk = sensorIdFk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

	@Override
	public String toString() {
		return "SensorLocation [id=" + id + ", sensorLocation=" + sensorLocation + ", description=" + description
				+ ", sensorIdFk=" + sensorIdFk + ", sensorName=" + sensorName + "]";
	}
}

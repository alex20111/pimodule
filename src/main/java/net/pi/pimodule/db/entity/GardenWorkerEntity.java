package net.pi.pimodule.db.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import net.pi.pimodule.enums.ScheduleType;

public class GardenWorkerEntity {

	public static final String TBL_NAME = "garden_worker";
	public static final String ID = "id";
	public static final String SCHED_TYPE = "id";
	public static final String WATERING_DATE = "id";
	public static final String WATERING_DURATION = "id";
	public static final String DESCRIPTION = "id";
	public static final String SENSOR_ID = "id";


	private int id 			  			= -1;
	private ScheduleType scheduleType 	= ScheduleType.NONE; //if daily, weekly, monthly
	private LocalDateTime wateringDate;
	private int wateringDuration 		= -1;
	private String description 			= "";
	private int sensorId 				= -1; //sensor id of the sensor that is controlling the garden.


	public GardenWorkerEntity() {}

	public GardenWorkerEntity(ResultSet rs) throws SQLException {
		this.id = rs.getInt(ID);
		this.scheduleType = ScheduleType.valueOf(rs.getString(SCHED_TYPE));
		this.wateringDate = rs.getTimestamp(WATERING_DATE).toLocalDateTime();
		this.wateringDuration = rs.getInt(WATERING_DURATION);
		this.description = rs.getString(DESCRIPTION);
		this.sensorId = rs.getInt(SENSOR_ID);
	}

	public int getId() {
		return id;
	}
	public ScheduleType getScheduleType() {
		return scheduleType;
	}
	public LocalDateTime getWateringDate() {
		return wateringDate;
	}
	public int getWateringDuration() {
		return wateringDuration;
	}
	public String getDescription() {
		return description;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setScheduleType(ScheduleType scheduleType) {
		this.scheduleType = scheduleType;
	}
	public void setWateringDate(LocalDateTime wateringDate) {
		this.wateringDate = wateringDate;
	}
	public void setWateringDuration(int wateringDuration) {
		this.wateringDuration = wateringDuration;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
}

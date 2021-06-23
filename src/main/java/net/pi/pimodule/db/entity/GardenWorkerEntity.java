package net.pi.pimodule.db.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.pi.pimodule.common.WorkerStatus;
import net.pi.pimodule.enums.ScheduleType;

public class GardenWorkerEntity {

	public static final String TBL_NAME = "garden_worker";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String SCHED_TYPE = "sched_type";
	public static final String WATERING_DATE = "watering_date";
	public static final String WATERING_DURATION = "watering_duration";
	public static final String DESCRIPTION = "description";
	public static final String SENSOR_ID_FK = "sensor_id";


	private int id 			  			= -1;
	private String name					= "";
	private ScheduleType scheduleType 	= ScheduleType.NONE; //if daily, weekly, monthly
	private String wateringDate 		= 		"";
	private int wateringDuration 		= -1;
	private String description 			= "";
	private int sensorIdFk 				= -1; //sensor id of the sensor that is controlling the garden.
	
	//transient
	private WorkerStatus status;


	public GardenWorkerEntity() {}

	public GardenWorkerEntity(ResultSet rs) throws SQLException {
		this.id = rs.getInt(ID);
		this.name = rs.getString(NAME);
		this.scheduleType = ScheduleType.valueOf(rs.getString(SCHED_TYPE));
		this.wateringDate = rs.getString(WATERING_DATE) ;
		this.wateringDuration = rs.getInt(WATERING_DURATION);
		this.description = rs.getString(DESCRIPTION);
		this.sensorIdFk = rs.getInt(SENSOR_ID_FK);
	}

	public int getId() {
		return id;
	}
	public ScheduleType getScheduleType() {
		return scheduleType;
	}
	public String getWateringDate() {
		return wateringDate;
	}
	public int getWateringDuration() {
		return wateringDuration;
	}
	public String getDescription() {
		return description;
	}
	public int getSensorIdFk() {
		return sensorIdFk;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setScheduleType(ScheduleType scheduleType) {
		this.scheduleType = scheduleType;
	}
	public void setWateringDate(String wateringDate) {
		this.wateringDate = wateringDate;
	}
	public void setWateringDuration(int wateringDuration) {
		this.wateringDuration = wateringDuration;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setSensorIdFk(int sensorIdFk) {
		this.sensorIdFk = sensorIdFk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkerStatus getStatus() {
		return status;
	}

	public void setStatus(WorkerStatus status) {
		this.status = status;
	}
	
//	public LocalDateTime getWateringDateAsLicalDateTime() {
//		return this.wateringDate != null ? LocalDateTime.parse(this.wateringDate, Constants.DATE_FORMATTER) : null;
//	}

	@Override
	public String toString() {
		return "GardenWorkerEntity [id=" + id + ", name=" + name + ", scheduleType=" + scheduleType + ", wateringDate="
				+ wateringDate + ", wateringDuration=" + wateringDuration + ", description=" + description
				+ ", sensorIdFk=" + sensorIdFk + ", "
						+ "]";
	}
}

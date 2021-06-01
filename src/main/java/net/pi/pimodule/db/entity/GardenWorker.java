package net.pi.pimodule.db.entity;

import java.time.LocalDateTime;

import net.pi.pimodule.enums.ScheduleType;

public class GardenWorker {

	
	
	private int id 			  		= -1;
	private ScheduleType scheduleType 	= ScheduleType.NONE; //if daily, weekly, monthly
	private LocalDateTime nextWatering;
	private int wateringDuration 	= -1;
	private String description 		= "";
	private int locationId 			= -1;
}

package net.pi.pimodule.enums;

public enum ScheduleType {
	DAILY("Daily"), TWO_DAYS("Every 2 days"),THREE_DAYS("Every 3 days")
	,FOUR_DAYS("Every 4 days"),WEEK("Every week"), NONE("None");
	
	private String desc = "";
	
	private ScheduleType(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}
}

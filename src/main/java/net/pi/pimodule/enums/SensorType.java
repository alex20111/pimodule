package net.pi.pimodule.enums;

public enum SensorType {
	POOL("p"), TEMP("t"), GARDEN("g"), LED("a"), NONE("");
	
	private String type = "";
	
	private SensorType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public static SensorType genEnumType(String type) {
		for(SensorType t: SensorType.values()) {
			if (t.getType().equalsIgnoreCase(type)) {
				return t;
			}
			
		}
		return NONE;
	}
}

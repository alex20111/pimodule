package net.pi.pimodule.enums;

public enum SensorType {
	POOL("p"), TEMPERATURE("t"), GARDEN("g"), LED("a"), NONE("");
	
	private String type = "";
	
	private SensorType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}

package net.pi.pimodule.serial;

public enum Sensor {
	GARAGE_LED("A1"), OUT_TEMP_1("A9");

	private String id = "";

	private Sensor(String id) {
		this.id = id;
	}

	public String getIdentifier() {
		return this.id;
	}
}

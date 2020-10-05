package net.pi.pimodule.service.model;

public class DayNightCycle {
	
	
	private String cycle = "";

	
	public DayNightCycle(Cycle cycle) {
		this.cycle = cycle.name();
	}
	
	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	@Override
	public String toString() {
		return "DayNightCycle [cycle=" + cycle + "]";
	}
	public enum Cycle{
		DAY, NIGHT, NA;
	}

}

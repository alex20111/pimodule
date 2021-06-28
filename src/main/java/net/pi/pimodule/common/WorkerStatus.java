package net.pi.pimodule.common;


public class WorkerStatus {
	
	private int workerId = -1;
	private boolean watering = false;
	private boolean doNotWater = false;
	private String lastUpdate = "";
	private boolean alive = false;
	
	
	public boolean isWatering() {
		return watering;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setWatering(boolean watering) {
		this.watering = watering;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public int getWorkerId() {
		return workerId;
	}
	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}
	public boolean isDoNotWater() {
		return doNotWater;
	}
	public void setDoNotWater(boolean doNotWater) {
		this.doNotWater = doNotWater;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	@Override
	public String toString() {
		return "WorkerStatus [workerId=" + workerId + ", watering=" + watering + ", doNotWater=" + doNotWater
				+ ", lastUpdate=" + lastUpdate + ", alive=" + alive + "]";
	}

}

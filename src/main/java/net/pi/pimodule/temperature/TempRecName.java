package net.pi.pimodule.temperature;

public enum TempRecName {

	AA("AA"), BB("BB"), pool("Pool");
	
	private String recorderName;
	
	private TempRecName(String recName){
		recorderName = recName;
	}
	
	public String getRecName(){
		return this.recorderName;
	}
}

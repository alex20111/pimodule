package net.pi.pimodule.serial;

public interface Command {
	public static String START_MARKER = "<";
	public static String END_MARKER = ">";

	public String sendCommand();
	public String handleDataReceived(String data);
	
}

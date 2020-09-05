package net.pi.pimodule.websocket;

public class Data {
    public static final int IDENTIFICATION = 1;
    public static final int GARAGE_FUNCTION = 2;
    public static final int HEART_BEAT     = 20;
    public static final int TERMINATE_SESSION = 99;
    
    
    public static final int MESSAGING_SEND = 101;
    
    public int operation;
    public String userName = "";
    public int garageDoorStatus = -1;
    
//    public String session;
	@Override
	public String toString() {
		return "Data [operation=" + operation + ", userName=" + userName + " GarageDoorStatus: " + garageDoorStatus + "]";
	}
    
    
}
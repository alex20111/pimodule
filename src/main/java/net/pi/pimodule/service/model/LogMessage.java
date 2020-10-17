package net.pi.pimodule.service.model;

//{'className':'className', 'errorMessage':'broken please fix', 'fullErrorStack': 'full error stack ex'}
public class LogMessage {
	
	private String className ="";
	private String errorMessage = "";
	private String fullErrorStack = "";
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getFullErrorStack() {
		return fullErrorStack;
	}
	public void setFullErrorStack(String fullErrorStack) {
		this.fullErrorStack = fullErrorStack;
	}
	
	

}

package net.pi.pimodule.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.service.model.Message;

public class SharedData {
	
	private static final Logger logger = LogManager.getLogger(SharedData.class);
	
	private static SharedData sharedData;
	private boolean runningInProd = true;	

	private Map<String, Object> shareableDataMap = null;

	
	public static SharedData getInstance() {
		if (sharedData == null) {
			synchronized (SharedData.class) {
				if(sharedData == null) {
					logger.info( "SharedData initialized");
					sharedData = new SharedData();
				}
			}
		}
		return sharedData;
	}	
	private SharedData() {
		shareableDataMap = new WeakHashMap<>();
	}
	

	
	public boolean isRunningInProd() {
		return runningInProd;
	}
	public void setRunningInProd(boolean runningInProd) {
		this.runningInProd = runningInProd;
	}
	
	public void addToMessage(Message msg) {
		@SuppressWarnings("unchecked")
		List<Message> messages = (List<Message>)SharedData.getInstance().getSharedObject(Constants.MESSAGE_ERROR);
		if (messages == null) {
			messages = new ArrayList<>();
		}		
		
		messages.add(msg);
		putSharedObject(Constants.MESSAGE_ERROR, messages);
	}
	
	
	/** returns replaced object. null if no previous value */
	public Object putSharedObject(String key, Object value)  {
//		Object oldValue = getSharedObject(key);

		Object removedObject = shareableDataMap.put(key, value);

//		firePropertyChange(key, oldValue, value, this);
		return removedObject;
	}
	
	public Object getSharedObject(String key) {
		return shareableDataMap.get(key);
	}

}

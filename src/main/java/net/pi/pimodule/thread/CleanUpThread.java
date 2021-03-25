package net.pi.pimodule.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CleanUpThread implements Runnable{
	
	private static final Logger logger = LogManager.getLogger(CleanUpThread.class);

	@Override
	public void run() {
		logger.debug("Clean up thread running");
		
		
		logger.debug("Only keeping temperature for 2 month");
		
		logger.debug("Temperature db rows deleted: ");
	}

}

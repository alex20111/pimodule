package net.pi.pimodule.thread;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.db.TempSql;


public class CleanUpThread implements Runnable{

	private static final Logger logger = LogManager.getLogger(CleanUpThread.class);

	@Override
	public void run() {
		logger.debug("Clean up thread running");		

		try {
			LocalDateTime deleteDate = LocalDateTime.now().minusMonths(3);
			logger.debug("Only keeping temperature for 3 month. Now deleting everything before: " + deleteDate);

			TempSql sql = new TempSql();

			int nbrRowsDel = sql.cleanUpTempDbByDate(deleteDate);

			logger.debug("Temperature db rows deleted: " + nbrRowsDel);
		}catch(Exception ex) {
			logger.error("Error in clean up thread.." , ex);
		}
	}

}

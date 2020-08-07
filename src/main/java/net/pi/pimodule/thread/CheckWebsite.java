package net.pi.pimodule.thread;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckWebsite implements Runnable{

	private static final Logger logger = LogManager.getLogger(CheckWebsite.class);

	public CheckWebsite() {
		
	}

	@Override
	public void run() {

		boolean active = websiteUp("https://www.boudreault.xyz");

		if (!active) {
			//send e-mail to notify that website is not reacheable..
			logger.info("!!!!!!Website unreacheable");
		}
	}

	private boolean websiteUp(String url)  {

		boolean active = false;
		try {
			URL urlObj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod("GET");
			// Set connection timeout
			con.setConnectTimeout(3000);
			con.connect();

			int code = con.getResponseCode();
			if (code == HttpURLConnection.HTTP_OK) {
				active = true;
				
			}
		} catch (Exception e) {
			active = false;
		}
		return active;
	}
}

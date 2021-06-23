import java.time.LocalDateTime;

import net.pi.pimodule.common.Constants;

public class Locadate {

	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		
		String dtst = now.format(Constants.DATE_FORMATTER);
		
		System.out.println("Date string: " + dtst);
	}

}

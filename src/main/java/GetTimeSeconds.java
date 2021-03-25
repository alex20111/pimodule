import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class GetTimeSeconds {

	public static void main(String[] args) {
		System.out.println(new Date());


		int hour = 1000*60*60*4;

		System.out.println(( new Date().getTime() - (hour) ) / 1000 +  "  h: " + hour);


		System.out.println(String.format("%03d", 22));



		LocalDateTime now = LocalDateTime.now();

		LocalDateTime future = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59);

		//			LocalDateTime localDateTime = // implementation details
		ZonedDateTime zdtNow = ZonedDateTime.of(now, ZoneId.systemDefault());

		ZonedDateTime zdtFuture = ZonedDateTime.of(future, ZoneId.systemDefault());
		//					Assert.assertEquals(millis, zdt.toInstant().toEpochMilli());

		long delay = zdtFuture.toInstant().toEpochMilli() - zdtNow.toInstant().toEpochMilli();

		System.out.println("Delay: " + delay);
		
		
		
		String batt = "43";
		float fmtBatt = 0f;
		int battLen = batt.length();
		if (battLen == 4) {
			fmtBatt = Float.valueOf(batt) / 1000;
		}else if (battLen == 3) {
			fmtBatt = Float.valueOf(batt) / 100;
		}else if (battLen == 2) {
			fmtBatt = Float.valueOf(batt) / 10;
		}else if (battLen == 1){
			Float.valueOf(batt);
		}
		
		System.out.println(fmtBatt);
		
		int start = 15;
		int end = 4;
		
//		LocalDateTime now2 = LocalDateTime.now();
		LocalDateTime now2 = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 12, 0, 0);
//		LocalDateTime future2 = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), end, 0, 0);
		
	
		if (now2.getHour() > start && now2.getHour() < end) {
			System.out.println("Bingo1");
		}else if(now2.getHour() > start) {
			System.out.println("Bingo2");
		}
		
		
		
		

	}

}

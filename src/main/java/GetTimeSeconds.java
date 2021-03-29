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
	
		int s = 15;
		int e = 4;
		
		int t[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,0};
		
		for(int i = 0 ; i < t.length; i++) {
			System.out.println( t[i]+ " in between " + s + " and " + e + " ? " + powerSave(t[i], s, e));
		}
		

	}
	
	private static boolean powerSave(int t, int s, int e) {
		if ( ( s < e && t >= s && t < e)  || 
			 (s > e && ( t >= s || t < e ) ) ) {	
				return true;
		
		}
		
		
		return false;
	}

}

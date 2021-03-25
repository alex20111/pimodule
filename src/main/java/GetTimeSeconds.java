import java.util.Date;

public class GetTimeSeconds {

	public static void main(String[] args) {
		System.out.println(new Date());
		
		
		int hour = 1000*60*60*4;
		
		System.out.println(( new Date().getTime() - (hour) ) / 1000 +  "  h: " + hour);


		 System.out.println(String.format("%03d", 22)); 

	}

}

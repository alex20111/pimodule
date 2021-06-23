import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test121 {
	
	private static Object testWait = new Object();
	protected final static BlockingQueue<Boolean> sensorReplied =  new ArrayBlockingQueue<>(1);

	public static void main(String[] args) {
		System.out.println("start");
		Thread t = new Thread(new Runnable () {

			@Override
			public void run() {
				try {
					System.out.println("threadStart");
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("end");
				
			}
			
		});
		
		t.start();
		
		
		synchronized(testWait) {
			try {
				Boolean answer = sensorReplied.poll(2000, TimeUnit.MILLISECONDS);
				
				System.out.println("Answer: " + answer);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("progend");

	}

}

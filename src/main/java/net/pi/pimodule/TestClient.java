package net.pi.pimodule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



public class TestClient {

	private static int t = 1;
	private static 		boolean needToLogIn = true;

	public static void main(String args[]) throws InterruptedException {


		//		postM();
		//		
		//		//GET
		//		
		//		Client client = ClientBuilder.newClient();
		//		
		//		WebTarget webTarget 
		//		  = client.target("http://localhost:8080/webservice/webapi");
		//		
		//		WebTarget tempTarget 
		//		  = webTarget.path("temperature/temp");
		//		
		//		Invocation.Builder invocationBuilder = tempTarget.request(MediaType.APPLICATION_JSON);
		//		
		//		Response response = invocationBuilder.get();
		//
		////		System.out.println(response.toString());
		//		System.out.println(response.readEntity(String.class));
		//		
		Client client = ClientBuilder.newClient();//( new ClientConfig().register( LoggingFilter.class ) );

		WebTarget webTarget = client.target("http://localhost:8080/webservice/webapi"); 


		String token = "";

		while(true) {

			if (needToLogIn) {
				token = login(webTarget);
			}
			sendData(webTarget, token);
		}


		//		http://localhost:8080/webservice/webapi/temperature/temp

	}
	public static void postM() {






	}


	private static String buildtemp() {

		//		 tempSun = "-99";
		//			private  String  = "21:45am";
		//			private  String  = "-99";
		//			private  String  = "10:45am";
		//			private  String  = "-99";
		//			private  String  = "11:45am";

		t++;

		return "{ \"tempSun\":\""+t+"\",\"tmpSunUpdDt\":\"21:45\",\"tempShade\":\"23\", \"tmpShadeUpdDt\":\"12:45\",\"tempPool\":\"1\",\"tmpPoolUpdDt\":\"08:33\" } ";
	}


	private static String login(WebTarget webTarget) {
		//LOGIN
		WebTarget loginTarget  = webTarget.path("auth/login");

		String passHashed = "0299c438465ea02b89cd201b20932ca8b5c4a0937f971853abbf349763eab3dabf8b7a9b9b3e301fafe05fd484093fc944e53651df253c4cf0d1e7e9605cef13";

		String str = "{ \"userName\":\"weatherBot\",\"password\":\""+passHashed+"\",\"websiteToAccess\":\"HEADLESS\" } ";

		Invocation.Builder invocationBuilder =  loginTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(str, MediaType.APPLICATION_JSON));

		User u = response.readEntity(User.class);

		System.out.println("Got user: " + u);
		needToLogIn = false;

		return u.getAuthToken();
	}
	private static void sendData(WebTarget webTarget, String token) {
		//UPDATE
		WebTarget tempUpdTarget  = webTarget.path("temperature/update");

		String tempPost = buildtemp();
		//		 
		Invocation.Builder invBuilderTemp = tempUpdTarget.request(MediaType.APPLICATION_JSON);
		Response responseT = invBuilderTemp.header("Authorization", "Bearer " + token).post(Entity.entity(tempPost, MediaType.APPLICATION_JSON));

//		System.out.println("Response: " + responseT.readEntity(String.class));
		String resp = responseT.readEntity(String.class);

		if (resp.contains("Invalid user token")) {
			//this means we need to re-login
			needToLogIn = true;
		}

	}

}

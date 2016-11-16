package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EchoClient {
	private String url = "http://192.168.0.49:8000/echo.php";

	public void setURLParam(String param){
		url = url + "?message="+param;
	}
	
	public static void main(String[] args) {
		EchoClient server = new EchoClient();
		server.setURLParam("Hello");
		
		try {
			String responseStr1 = server.sendRequestToPHPServer();
			System.out.println("Response for Java API is: "+responseStr1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String sendRequestToPHPServer() throws Exception {
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		System.out.println("\nSending 'GET' request to URL : " + url);
		int responseCode = con.getResponseCode();		
		System.out.println("Response Code : " + responseCode);
		System.out.println("Response: ");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response.toString();
	}
}

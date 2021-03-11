package client;

import java.net.Socket;

public class ClientServ {
	public String[][] airplaneConfiguration;
	public int numberOfAirplanesHit=0;
	public String userName="";
	public Socket socket;
	public boolean isChoosingName=true;
	
	public ClientServ() {

	}
	
	public ClientServ(String[][] airplanes, Socket socket) {
		this.airplaneConfiguration=airplanes;
		this.socket=socket;  
	}
}

package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.AirplaneConfiguration;
import common.Transport;
import server.Server;

public class Client implements AutoCloseable {

	public static List<Client> clients=new ArrayList<Client>();
	public interface ClientCallback{
		void onTalk(String message);
	}
	public String[][] airplaneConfiguration;
	public int numberOfAirplanesHit=0;
	public String userName="";
	public Socket socket;
	public boolean isChoosingName=true;
	
	public Client(String host, int port,ClientCallback callback) throws UnknownHostException, IOException {

		Client.clients.add(this);
		socket=new Socket(host,port);
		new Thread(()->{
			while(socket!=null && !socket.isClosed())
			{
				try {
					callback.onTalk(Transport.receive(socket));
				}catch (Exception e) {
				}
			}
		}).start();
	}
	
	public void send(String message) throws IOException
	{
		Transport.send(message, socket);
	}
	
	@Override
	public void close() throws Exception {
		socket.close();
		socket=null;
	}
}

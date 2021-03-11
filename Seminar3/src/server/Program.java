package server;

import client.Client;
import common.Settings;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Server server=new Server();){
			server.start(Settings.PORT);
			System.out.println("Server is running!");
			while(true)
			{
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		finally {
			System.exit(0);
		}
	}

}

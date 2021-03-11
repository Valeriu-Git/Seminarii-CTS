package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.Client;
import client.ClientServ;
import common.AirplaneConfiguration;
import common.Transport;
import common.Utils;

public class Server implements AutoCloseable {

	public  static List<ClientServ> test=new ArrayList<ClientServ>();
	private ServerSocket serverSocket;
	private ExecutorService executorService;

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
	}

	public void start(int port) throws IOException {
		stop();
		serverSocket = new ServerSocket(port);
		executorService = Executors.newFixedThreadPool(50 * Runtime.getRuntime().availableProcessors());
		
		Random random= new Random();
		List<String> userNames=new ArrayList<String>();
		executorService.execute(() -> {
			while (serverSocket != null && !serverSocket.isClosed()) {
				try {
					final Socket socket = serverSocket.accept();
					executorService.submit(() -> {
						try {
							String[][] airplaneConfiguration=AirplaneConfiguration.airplanesConfigurations[random.nextInt(4)];
							for(int j=0;j<10;j++)
							{
								for(int k=0;k<10;k++)
								{
									System.out.printf("%s ",airplaneConfiguration[j][k]);
								}
								System.out.println();
							}
							System.out.println();
							ClientServ client=new ClientServ(airplaneConfiguration,socket);
							test.add(client);
							Transport.send("Pentru a castiga trebuie sa nimeresti capetele celor 3 avioane asezate pe o suprafata 10X10.Odata ce un utilizator a castigat progresul este resetat.", socket);
							Transport.send("Introduceti username-ul", socket);
							while (socket != null && !socket.isClosed()) {
								try {
									ClientServ currentUser=test.stream().filter(cl->cl.socket==socket).findFirst().get();
									String message = Transport.receive(socket);
									if(currentUser.isChoosingName)
									{
										boolean isUsernameTaken=false;
										for(int i=0;i<userNames.size();i++)
										{
											if(message.equals(userNames.get(i)))
											{
												isUsernameTaken=true;
												break;
											}
										}
										if(!isUsernameTaken)
										{
										currentUser.userName=message;
										currentUser.isChoosingName=false;
										userNames.add(currentUser.userName);
										Transport.send("Username:"+currentUser.userName, socket);
										}
										else
										{
											Transport.send("Numele introdus nu este valabil", socket);
										}
									}
									else
									{
										String[] checkCoordinates=message.split(" ");
										for(String coordinate :checkCoordinates)
										{
											if(!Utils.isNumber(coordinate)) {
												throw new Exception("The input should have the following format:number number");
											}
										}
										int[] coordinates=new int[2];
										for(int i=0;i<2;i++)
										{
											coordinates[i]=Integer.parseInt(message.split(" ")[i])-1;
										}
										String chosenSpot=currentUser.airplaneConfiguration[coordinates[0]][coordinates[1]];
										if(!Utils.isNumber(chosenSpot))
										{
											Transport.send("You hit the head at the following coordinates:"+(coordinates[0]+1)+" "+(coordinates[1]+1),socket);
											currentUser.numberOfAirplanesHit++;
											if(currentUser.numberOfAirplanesHit==3)
											{
												String winnerName=currentUser.userName;
					
												for(int i=0;i<test.size();i++)
												{
													test.get(i).numberOfAirplanesHit=0;
													test.get(i).airplaneConfiguration=AirplaneConfiguration.airplanesConfigurations[random.nextInt(4)];
													System.out.println(test.get(i).userName);
												for(int j=0;j<10;j++)
													{
														for(int k=0;k<10;k++)
														{
															System.out.printf("%s ",test.get(i).airplaneConfiguration[j][k]);
														}
														System.out.println();
													}
													System.out.println();
													try {
														if(test.get(i).socket!=socket)
														{
														Transport.send(winnerName+" won", test.get(i).socket);
														}
														else
														{
															Transport.send("You won", test.get(i).socket);
														}
													} catch (IOException e) {
														e.printStackTrace();
													}finally {
														Transport.send("Game Reset", test.get(i).socket);
													}
													
												}
											}
										}
										else if(Integer.parseInt(chosenSpot)!=0)
										{
											Transport.send("You hit part of an airplane at the following coordinates:"+(coordinates[0]+1)+" "+(coordinates[1]+1),socket);
										}
										else
										{
											Transport.send("You didn't hit anything at the following coordinates:"+(coordinates[0]+1)+" "+(coordinates[1]+1),socket);
										}
									}
								} catch (Exception e) {
									// TODO: handle exception
									System.out.println(e.getMessage());
									if(e.getMessage().equals("-1"))
									{
										break;
									}
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
						} finally {
							System.out.println("Successfully removed client");
							}
					});
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
		});
	}

	public void stop() throws IOException {
		if (serverSocket != null) {
			serverSocket.close();
			serverSocket = null;
		}
		if (executorService != null) {
			executorService.shutdown();
			executorService = null;
		}
	}
}

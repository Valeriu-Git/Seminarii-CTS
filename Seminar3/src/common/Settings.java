package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Settings {
	

	public static final String HOST;
	public static final int PORT;
	public static  final String[][][] airplanesConfigurations=new String[2][10][10];
	
	static {
		ResourceBundle bundle=ResourceBundle.getBundle("settings");
		HOST=bundle.getString("host");
		PORT=Integer.parseInt(bundle.getString("port"));
//		System.out.println(new File("airplanes_configuration.txt").getAbsolutePath());
//		try {
//			Scanner input =new Scanner(new File("airplanes_configuration.txt"));
//			int index=0;
//			while(input.hasNext())
//			{
//				int size=input.nextInt();
//				String[][] array=new String[size][size];
//				for(int i=0;i<size;i++)
//				{
//					for(int j=0;j<size;j++)
//					{
//						try {
//							array[i][j]=input.next();
//						}catch (Exception e) {
//							// TODO: handle exception
//						}
//					}
//				}
//	            airplanesConfigurations[index]=array;
//	            index++;
//			}
//			System.out.println("Airplanes configuration");
//			for(int i=0;i<2;i++)
//			{
//				for(int j=0;j<10;j++)
//				{
//					for(int k=0;k<10;k++)
//					{
//						System.out.printf("%s ",airplanesConfigurations[i][j][k]);
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
//		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}

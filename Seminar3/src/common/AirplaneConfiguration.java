package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AirplaneConfiguration {
	public static final int  configurationSize=2;
public static  final String[][][] airplanesConfigurations=new String[4][10][10];
	
	static {
		try {
			Scanner input =new Scanner(new File("airplanes_configuration.txt"));
			int index=0;
			while(input.hasNext())
			{
				int size=input.nextInt();
				String[][] array=new String[size][size];
				for(int i=0;i<size;i++)
				{
					for(int j=0;j<size;j++)
					{
						try {
							array[i][j]=input.next();
						}catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
	            airplanesConfigurations[index]=array;
	            index++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

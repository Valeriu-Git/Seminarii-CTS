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
	}
}

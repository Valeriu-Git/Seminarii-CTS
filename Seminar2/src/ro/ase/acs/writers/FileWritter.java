package ro.ase.acs.writers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileWritter extends ConsoleWriter {

	@Override
	public void print(String message)
	{
		OutputStreamWriter osWriter=null;
		try {
			FileOutputStream fileOutputStream=new FileOutputStream("output.txt");
			osWriter=new OutputStreamWriter(fileOutputStream);
			osWriter.write(message);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(osWriter!=null)
			{
				try {
					osWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

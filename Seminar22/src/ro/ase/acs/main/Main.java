package ro.ase.acs.main;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import ro.ase.acs.container.IoCContainer;
import ro.ase.acs.contracts.Writable;
import ro.ase.acs.readers.Reader;
import ro.ase.acs.writers.ConsoleWriter;
import ro.ase.acs.writers.FileWritter;

public class Main {


	
	public static void main(String[] args)
	{	 
		IoCContainer container= new IoCContainer();
		container.register(Readable.class, Reader.class);
		Reader reader= new Reader();
		ConsoleWriter consoleWriter=new ConsoleWriter();
		container.register(Writable.class, ConsoleWriter.class);
		Orchestrator orchestrator;
		try {
			orchestrator = new Orchestrator(container.resolve(Readable.class),container.resolve(Writable.class));
			orchestrator.execute();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	

	
}

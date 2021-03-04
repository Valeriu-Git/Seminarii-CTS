package ro.ase.acs.main;

import ro.ase.acs.contracts.Readable;
import ro.ase.acs.contracts.Writable;
import ro.ase.acs.readers.Reader;
import ro.ase.acs.writers.ConsoleWriter;

public class Orchestrator {

	private Readable reader;
	private Writable writer;
	
	public Orchestrator(Readable reader, Writable writer)
	{
		this.reader=reader;
		this.writer=writer;
	}
	
	public void execute() {
		writer.print(reader.read());
	}
}

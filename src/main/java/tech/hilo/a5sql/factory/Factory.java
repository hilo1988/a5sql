package tech.hilo.a5sql.factory;

import tech.hilo.a5sql.reader.CsvReader;
import tech.hilo.a5sql.reader.impl.CsvReaderImpl;
import tech.hilo.a5sql.valueobject.Table;
import tech.hilo.a5sql.writer.TableWriter;
import tech.hilo.a5sql.writer.impl.TableWriterImpl;

public class Factory {

	private Factory(){}
	
	private static CsvReader reader;
	
	public static CsvReader getCsvReader() {
		if (reader == null) {
			reader = new CsvReaderImpl();
		}
		return reader;
	}
	
	
	public static TableWriter getTableWriter(Table table) {
		return new TableWriterImpl(table);
	}
}

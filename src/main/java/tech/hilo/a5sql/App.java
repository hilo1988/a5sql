package tech.hilo.a5sql;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import tech.hilo.a5sql.factory.Factory;
import tech.hilo.a5sql.reader.impl.TableReaderImpl;
import tech.hilo.a5sql.valueobject.Table;
import tech.hilo.a5sql.writer.TableWriter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException, URISyntaxException {
		List<Table> tables = new TableReaderImpl()
			.readTable(ClassLoader.getSystemResource("csv.zip"), "SHIFT_JIS");
		
		TableWriter writer = Factory.getTableWriter(tables.get(0));
		writer.write("aaa");
	}
}

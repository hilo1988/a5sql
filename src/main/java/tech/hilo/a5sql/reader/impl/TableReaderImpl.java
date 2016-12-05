package tech.hilo.a5sql.reader.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import tech.hilo.a5sql.factory.Factory;
import tech.hilo.a5sql.reader.TableReader;
import tech.hilo.a5sql.valueobject.Column;
import tech.hilo.a5sql.valueobject.Table;

public class TableReaderImpl implements TableReader {
	
	List<Table> tables;
	
	List<Column> columns;

	@Override
	public List<Table> readTable(URL input, String charset) throws IOException, URISyntaxException {
		
		try (ZipFile zip  = new ZipFile(new File(input.toURI()))) {
			Enumeration<? extends ZipEntry> enm = zip.entries();
			while (enm.hasMoreElements()) {
				ZipEntry entry = enm.nextElement();
				final String name = entry.getName();
				
				
				if (name.contains("TABLES")) {
					createTable(zip.getInputStream(entry), charset);
					continue;
				}
				
				if (name.contains("COLUMNS")) {
					createColumn(zip.getInputStream(entry), charset);
					continue;
				}				
			}
		}

		tables.forEach(table -> {
			columns.stream().filter(c -> c.getTableName().equals(table.getName()))
			.forEach(c -> table.addColumn(c));
		});
		
		
		return tables;
	}
	
	private void createTable(InputStream input, String charset) throws IOException {
		tables = Factory.getCsvReader().readCsv(input, charset, row -> new Table(row));
	}
	
	private void createColumn(InputStream input, String charset) throws IOException {
		columns = Factory.getCsvReader()
					.readCsv(input, charset, row -> new Column(row));
	}
	


}

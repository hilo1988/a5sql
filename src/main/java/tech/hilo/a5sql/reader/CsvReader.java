package tech.hilo.a5sql.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import tech.hilo.a5sql.parser.CsvParser;
import tech.hilo.a5sql.valueobject.CsvRow;

public interface CsvReader {
	List<CsvRow> readCsv(InputStream input, String charset) throws IOException;
	
	<T> List<T> readCsv(InputStream input, String charset, CsvParser<T> parser) throws IOException;
	
}

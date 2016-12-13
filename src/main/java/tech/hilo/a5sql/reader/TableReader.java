package tech.hilo.a5sql.reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import tech.hilo.a5sql.valueobject.Table;

public interface TableReader {


	List<Table> readTable(URL input, String charset) throws IOException, URISyntaxException;
}

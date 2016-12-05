package tech.hilo.a5sql.reader;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;
import tech.hilo.a5sql.reader.impl.CsvReaderImpl;
import tech.hilo.a5sql.valueobject.CsvRow;

@RunWith(JUnit4.class)
public class CsvReaderTest extends TestCase {

	@Test
	public void test() throws IOException {
		CsvReader reader = new CsvReaderImpl();
		List<CsvRow> rows = reader.readCsv(ClassLoader.getSystemResourceAsStream("a5m2_COLUMNS.csv"), "SHIFT-JIS");
		rows.forEach(System.out::println);
		
	}
}

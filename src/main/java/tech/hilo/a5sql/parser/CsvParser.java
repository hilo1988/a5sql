package tech.hilo.a5sql.parser;

import tech.hilo.a5sql.valueobject.CsvRow;

public interface CsvParser<T> {

	T parseRow(CsvRow row);
}

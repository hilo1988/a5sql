package tech.hilo.a5sql.builder;

import tech.hilo.a5sql.valueobject.Table;

public interface ColumnBuilder {

	String build(Table table);
}

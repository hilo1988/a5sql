package tech.hilo.a5sql.builder.impl;

import tech.hilo.a5sql.builder.ColumnBuilder;
import tech.hilo.a5sql.valueobject.Column;
import tech.hilo.a5sql.valueobject.Table;


public class ColumnBuilderImpl implements ColumnBuilder {
	
	private StringBuilder columnSb = new StringBuilder();

	@Override
	public String build(Table table) {
		table.getColumns().forEach(this::createColumn);
		return columnSb.toString();
	}
	
	private void createColumn(Column column) {
		if (column.isId()) {
			append("@Id");
		}
		
		append(column.getAnnotation());
		append(column.getField());
		newLine();
	}
	
	
	
	private void append(Object o) {
		columnSb.append(o)
			.append("\n");
	}
	
	private void newLine() {
		columnSb.append("\n");
	}
	

	
}

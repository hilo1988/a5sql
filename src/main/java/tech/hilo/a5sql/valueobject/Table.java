package tech.hilo.a5sql.valueobject;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * テーブル情報
 * @author hilo
 *
 */
@Data
public class Table {

	private String name;
	
	private String comment;
	
	private List<Column> columns;
	
	
	public Table(CsvRow row) {
		this.name = row.getValue("TABLE_NAME");
		this.comment = row.getValue("LOGICAL_NAME");
		this.columns = new ArrayList<>();
	}
	
	public void addColumn(Column column) {
		columns.add(column);
		}
}

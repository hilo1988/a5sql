package tech.hilo.a5sql.valueobject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CsvRow {

	/**
	 * CSVの行
	 * K:ヘッダのカラム名
	 * V:値
	 */
	private final Map<String, String> row;
	
	private CsvRow(Map<String, String> row){
		this.row = row;
	}
	
	public String getValue(String columnName) {
		return row.get(columnName);
	}
	
	public static CsvRow newInstance(Map<Integer, String> header, List<String> row) {
		Map<String, String> map = new HashMap<>();
		int index = 0;
		for (String value : row) {
			map.put(header.get(index++), value);
		}
		return new CsvRow(map);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

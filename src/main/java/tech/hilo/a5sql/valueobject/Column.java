package tech.hilo.a5sql.valueobject;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.CaseFormat;

import lombok.Data;
import tech.hilo.a5sql.creator.JavaTypeConverter;
import tech.hilo.a5sql.util.Utils;

/**
 * カラム情報
 * @author hilo
 */
@Data
public class Column {

	/** カラム名 */
	private final String name;
	
	/** テーブル名 */
	private final String tableName;
	
	private final String comment;
	
	private final String dbType;
	
	private final JavaType javaType;
	
	private final int order;
	
	/** NULL許可 */
	private final boolean isNullable;
	
	/** idか */
	private final boolean isId;
	
	public Column(CsvRow row) {
		this.tableName = row.getValue("TABLE_NAME");
		this.name = row.getValue("COLUMN_NAME");
		this.comment = row.getValue("LOGICAL_NAME");
		this.order = Integer.parseInt(row.getValue("ORDINAL_POSITION"));
		this.isNullable = Utils.yesNoBoolValue(row.getValue("IS_NULLABLE"));
		this.dbType = row.getValue("DATA_TYPE").toUpperCase();
		this.javaType = JavaTypeConverter.getInstance().convert(dbType);
		String keyPosition = row.getValue("KEY_POSITION");
		this.isId = NumberUtils.isDigits(keyPosition);
	}
	
	
	public String getAnnotation() {
		List<String> list = new ArrayList<>();
		list.add(String.format("name = \"%s\"", name));
		list.add(String.format("nullable = %s", isNullable));
		if (javaType.getLength() > 0) {
			list.add(String.format("length = %d", javaType.getLength()));
		}
		
		return String.format("@Column(%s)", StringUtils.join(list, ", "));
	}
	
	public String getField() {
		return String.format("%s %s;", javaType.getName(), CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name));
	}
	
	public boolean isSerial() {
		return dbType.contains("SERIAL");
	}
}

package tech.hilo.a5sql.converter.impl;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tech.hilo.a5sql.converter.JavaTypeConverter;
import tech.hilo.a5sql.valueobject.JavaType;

public class JavaTypeConverterImpl implements JavaTypeConverter {
	private static Pattern LENGTH_PATTERN = Pattern.compile("\\((\\d+)\\)");

	@Override
	public JavaType convert(String dbType) {
		if (isInteger(dbType)) {
			return JavaType.integerType();
		}
		
		if (isLong(dbType)) {
			return JavaType.longType();
		}
		
		if (isString(dbType)) {
			return JavaType.stringType(getLength(dbType));
		}
		
		if (isTimestamp(dbType)) {
			return JavaType.timestampType();
		}
		
		if (isDate(dbType)) {
			return JavaType.dateType();
		}
		
		// unknown
		return new JavaType(dbType);
	}
	
	
	
	private static boolean isLong(String dbType) {
		return getLongList()
				.contains(dbType);
	}
	private static List<String> getLongList() {
		return Arrays.asList("BIGSERIAL",
					"BIGINT");
	}
	
	private static boolean isInteger(String dbType) {
		return getIntegerList()
				.contains(dbType);
	}
	
	
	private static List<String> getIntegerList() {
		return Arrays.asList("INTEGER",
				"SMALLINT",
				"SERIAL");
	}

	private static boolean isString(String dbType) {
		return getStringList()
			.stream()
			.anyMatch(prefix -> dbType.startsWith(prefix));
	}
	
	private static List<String> getStringList() {
		return Arrays.asList("TEXT",
				"CHAR",
				"VARCHAR");
	}
	
	
	private static boolean isDate(String dbType) {
		return dbType.startsWith("DATE");
	}
	
	private static boolean isTimestamp(String dbType) {
		return dbType.startsWith("TIMESTAMP");
	}
	
	private static int getLength(String dbType) {
		Matcher matcher = LENGTH_PATTERN.matcher(dbType);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		}
		return -1;
	}
}

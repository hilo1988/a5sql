package tech.hilo.a5sql.valueobject;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JavaType {

	private final String name;
	
	private final String packageName;
	
	private final int length;
	
	public JavaType(String name) {
		this(name, null);
	}
	
	public JavaType(String name, String packageName) {
		this(name, packageName, -1);
	}
	
	
	
	public static JavaType integerType() {
		return new JavaType("Integer");
	}
	
	public static JavaType longType() {
		return new JavaType("Long");
	}
	
	public static JavaType stringType() {
		return new JavaType("String");
	}
	
	public static JavaType stringType(int length) {
		return new JavaType("String", null, length);
	}
	
	public static JavaType timestampType() {
		return new JavaType("Timestamp", "java.sql.Timestamp");
	}
	
	public static JavaType dateType() {
		return new JavaType("Date", "java.sql.Date");
	}
	
	
}

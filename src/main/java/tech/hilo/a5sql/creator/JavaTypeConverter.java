package tech.hilo.a5sql.creator;

import tech.hilo.a5sql.creator.impl.JavaTypeConverterImpl;
import tech.hilo.a5sql.valueobject.JavaType;

public interface JavaTypeConverter {
	
	JavaType convert(String dbType);
	
	public static JavaTypeConverter getInstance() {
		return new JavaTypeConverterImpl();
	}

}

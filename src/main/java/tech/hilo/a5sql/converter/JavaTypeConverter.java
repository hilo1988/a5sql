package tech.hilo.a5sql.converter;

import tech.hilo.a5sql.valueobject.JavaType;

/**
 * SQLの型をJavaの型に変換する
 */
public interface JavaTypeConverter {

    /**
     * 変換処理
     */
	JavaType convert(String dbType);
	
}

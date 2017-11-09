package tech.hilo.a5sql.factory;

import tech.hilo.a5sql.converter.JavaTypeConverter;
import tech.hilo.a5sql.converter.impl.JavaTypeConverterImpl;
import tech.hilo.a5sql.creator.ZipCreator;
import tech.hilo.a5sql.creator.impl.EloquentEntityCreatorImpl;
import tech.hilo.a5sql.creator.impl.ZipCreatorImpl;
import tech.hilo.a5sql.facade.A5SqlFacade;
import tech.hilo.a5sql.facade.impl.A5SqlFacadeImpl;
import tech.hilo.a5sql.form.A5SqlForm;
import tech.hilo.a5sql.reader.CsvReader;
import tech.hilo.a5sql.reader.TableReader;
import tech.hilo.a5sql.reader.impl.CsvReaderImpl;
import tech.hilo.a5sql.reader.impl.TableReaderImpl;
import tech.hilo.a5sql.valueobject.OrmType;
import tech.hilo.a5sql.valueobject.Table;
import tech.hilo.a5sql.creator.EntityCreator;
import tech.hilo.a5sql.creator.impl.EntityCreatorImpl;

import javax.servlet.http.HttpSession;

/**
 * ファクトリ
 */
public class Factory {

	private Factory(){}


    /**
     * CSVリーダの取得
     */
	public static CsvReader getCsvReader() {
        return new CsvReaderImpl();
	}

    /**
     * テーブルリーダーの取得
     */
	public static TableReader getTableReader() {
        return new TableReaderImpl();
    }

    /**
     * Javaの型変換を取得
     */
    public static JavaTypeConverter getJavaTypeConverter() {
	    return new JavaTypeConverterImpl();
    }

    /**
     * テーブルライターの取得
     */
	public static EntityCreator getTableWriter(Table table, int ormType) {
	    switch (ormType) {
            case OrmType.ELOQUENT:
                return new EloquentEntityCreatorImpl(table);
            default:
                return new EntityCreatorImpl(table);
        }

	}

    /**
     * zipファイル作成クラスの取得
     */
	public static ZipCreator getZipCreator() {
	    return new ZipCreatorImpl();
    }

    public static A5SqlFacade getA5SqlFacade(A5SqlForm form, HttpSession session) {
	    return new A5SqlFacadeImpl(form ,session);
    }
}

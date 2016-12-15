package tech.hilo.a5sql.reader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import tech.hilo.a5sql.valueobject.Table;

/**
 * ZIPファイルからテーブルの一覧を作成
 */
public interface TableReader {


    /**
     * テーブルの読み込み
     */
	List<Table> readTable(URL input, String charset) throws IOException, URISyntaxException;

    /**
     * テーブルの読み込み
     */
	List<Table> readTable(File file, String charset) throws IOException;
}

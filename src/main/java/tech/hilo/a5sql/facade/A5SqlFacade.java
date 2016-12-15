package tech.hilo.a5sql.facade;

import tech.hilo.a5sql.form.A5SqlForm;

import java.io.IOException;

/**
 * CSVからテーブルエンティティを作成するファサード
 * Created by hilo on 2016/12/15.
 */
public interface A5SqlFacade {

    /**
     * CSVからテーブルエンティティのzipを作成
     */
    byte[] convert() throws IOException;
}

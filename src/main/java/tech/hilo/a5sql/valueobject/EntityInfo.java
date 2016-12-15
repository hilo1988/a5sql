package tech.hilo.a5sql.valueobject;

import lombok.Data;

import java.io.Serializable;

/**
 * テーブル情報
 * Created by hilo on 2016/12/15.
 */
@Data
public class EntityInfo implements Serializable {


    private static final long serialVersionUID = -5643597217085428537L;

    /** ファイル名 */
    private final String fileName;

    /** 内容 */
    private final String content;

    public String getFileName() {
        return fileName + ".java";
    }
}

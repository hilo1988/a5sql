package tech.hilo.a5sql.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * a5sql用フォーム
 * Created by hilo on 2016/12/13.
 */
@Data
public class A5SqlForm implements Serializable {


    private static final long serialVersionUID = 2288324225888520044L;

    private MultipartFile zip;

    private String baseClassName;

    private String packageName;

    private String charset = "SHIFT_JIS";

    private Integer ormType;
}

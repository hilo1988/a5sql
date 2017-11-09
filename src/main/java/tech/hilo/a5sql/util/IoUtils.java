package tech.hilo.a5sql.util;

import tech.hilo.a5sql.creator.impl.EloquentEntityCreatorImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IoUtils {

    private IoUtils(){}

    public static String readResourceFile(String path) {
        try (InputStream stream = EloquentEntityCreatorImpl.class.getClassLoader().getResourceAsStream(path)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int val;
            while ((val = stream.read()) >= 0) {
                baos.write(val);
            }
            return new String(baos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(String.format("リソースファイル[%s] が見つかりません。",
                    path));
        }
    }
}

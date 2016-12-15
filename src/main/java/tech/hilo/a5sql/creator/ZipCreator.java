package tech.hilo.a5sql.creator;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hilo on 2016/12/15.
 */
public interface ZipCreator {

    byte[] create(File zipFile, List<File> files) throws IOException;
}

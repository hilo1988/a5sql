package tech.hilo.a5sql.creator.impl;

import tech.hilo.a5sql.creator.ZipCreator;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by hilo on 2016/12/15.
 */
public class ZipCreatorImpl implements ZipCreator {

    @Override
    public byte[] create(File zipFile, List<File> files) throws IOException {

        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(zipFile))){
            for (File file : files) {
                ZipEntry entry = new ZipEntry(file.getName());
                zip.putNextEntry(entry);

                InputStream input = new FileInputStream(file);
                int val;
                while ((val = input.read()) >= 0) {
                    zip.write(val);
                }
            }

            zip.flush();
            zip.close();
        }

        try (InputStream input = new FileInputStream(zipFile)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int val;
            while ((val = input.read()) >= 0) {
                baos.write(val);
            }
            return baos.toByteArray();
        }

    }
}

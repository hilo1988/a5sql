package tech.hilo.a5sql.facade.impl;

import tech.hilo.a5sql.creator.ZipCreator;
import tech.hilo.a5sql.exception.IORuntimeException;
import tech.hilo.a5sql.facade.A5SqlFacade;
import tech.hilo.a5sql.factory.Factory;
import tech.hilo.a5sql.form.A5SqlForm;
import tech.hilo.a5sql.valueobject.EntityInfo;
import tech.hilo.a5sql.valueobject.Table;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A5SqlFacadeの実装クラス
 * Created by hilo on 2016/12/15.
 */
public class A5SqlFacadeImpl implements A5SqlFacade {

    private final A5SqlForm form;

    private final HttpSession session;

    private final File outputDir;

    public A5SqlFacadeImpl(A5SqlForm form, HttpSession session) {
        this.form = form;
        this.session = session;
        this.outputDir = createOutputDir(session);
    }

    @Override
    public byte[] convert() throws IOException{
        File tempInputFile = createTempFile();
        List<Table> tables = Factory.getTableReader()
                .readTable(tempInputFile, form.getCharset());

        List<File> outputFiles = tables.stream()
                .map(table -> Factory.getTableWriter(table, form.getOrmType()).write(form.getPackageName(), form.getBaseClassName()))
                .map(this::createEntityFile)
                .collect(Collectors.toList());

        File zip = new File(outputDir, "entities.zip");
        ZipCreator creator = Factory.getZipCreator();
        byte[] result = creator.create(zip, outputFiles);

        return result;
    }




    /**
     * 一時保存ファイルの作成
     */
    private File createTempFile() throws IOException{

        String tempDir = System.getProperty("java.io.tmpdir");
        StringBuilder sb = new StringBuilder(tempDir);
        if (!tempDir.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        sb.append("a5sqlInput");

        File dir = new File(sb.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, session.getId());

        try (OutputStream out = new FileOutputStream(file)) {
            out.write(form.getZip().getBytes());
            out.flush();
            out.close();
        }

        return file;
    }

    /**
     * 出力用ディレクトリ作成
     */
    private File createOutputDir(HttpSession session) {
        String tempDir = System.getProperty("java.io.tmpdir");
        StringBuilder sb = new StringBuilder(tempDir);
        if (!tempDir.endsWith(File.separator)) {
            sb.append(File.separator);
        }

        sb.append("a5sqlOutput")
            .append(File.separator)
            .append(session.getId())
            .append("entities");

        File dir = new File(sb.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    private File createEntityFile(EntityInfo info) {
        File file = new File(outputDir, info.getFileName());
        try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            out.write(info.getContent());
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return file;
    }

}

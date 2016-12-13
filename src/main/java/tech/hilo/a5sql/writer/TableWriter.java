package tech.hilo.a5sql.writer;

import java.io.IOException;

public interface TableWriter {

	void write(String packageName) throws IOException;
}


package tech.hilo.a5sql.reader.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import tech.hilo.a5sql.parser.CsvParser;
import tech.hilo.a5sql.reader.CsvReader;
import tech.hilo.a5sql.valueobject.CsvRow;

public class CsvReaderImpl implements CsvReader {
	
	

	@Override
	public List<CsvRow> readCsv(InputStream input, String charset) throws IOException {
		return readCsv(input, charset, row -> row);
	}
	
	@Override
	public <T> List<T> readCsv(InputStream input, String charset, CsvParser<T> parser) throws IOException {
		List<T> rows = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, charset))) {
			Map<Integer, String> header = new HashMap<>();
			String line;
			int index = 0;
			while ((line = reader.readLine()) != null) {
				
				// 1行目はヘッダ
				if (index++ == 0) {
					header = createHeader(line);
					continue;
				}
				
				CsvRow row = CsvRow.newInstance(header, parseLine(line));
				rows.add(parser.parseRow(row));
			}
		}
		return rows;
	}
	
	/**
	 * ヘッダの作成
	 * @param line
	 */
	private Map<Integer, String> createHeader(String line) {
		Map<Integer, String> header = new HashMap<>();
		int index = 0;
		for (String name : parseLine(line)) {
			header.put(index++, name);
		}
		return header;
	}
	
	private static List<String> parseLine(String line) {
		if (StringUtils.isBlank(line)) {
			return Collections.emptyList();
		}
		
		return Arrays.asList(line.split(","));
	}
}

package com.mooreb.basic_stats.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVDataSource {
    private final File file;
    public CSVDataSource(final File file) {
        this.file = file;
    }

    public List<CSVRecord> getCSVRecords() throws IOException {
        final Reader in = new FileReader(file);
        final CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        List<CSVRecord> list = parser.getRecords();
        return Collections.unmodifiableList(list);
    }
}

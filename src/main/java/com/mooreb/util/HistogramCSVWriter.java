package com.mooreb.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class HistogramCSVWriter<T extends Comparable<T>> {
    private final String fileName;

    public HistogramCSVWriter(final String fileName) {
        if(null == fileName) throw new IllegalArgumentException("fileName cannot be null");
        this.fileName = fileName;
    }

    public void write(final Histogram<T> histogram) throws IOException {
        if(null == histogram) throw new IllegalArgumentException("histogram cannot be null");
        final FileWriter fileWriter = new FileWriter(fileName);
        CSVPrinter printer = new CSVPrinter(fileWriter, CSVFormat.EXCEL);
        final List<Counted<Bucket<T>>> buckets = histogram.getBucketsByNaturalOrdering();
        printer.printRecord("label", "low", "high", "count");
        for(final Counted<Bucket<T>> countedBucket : buckets) {
            final long count = countedBucket.getCount();
            final Bucket<T> bucket = countedBucket.getT();
            final String label = bucket.reify(count);
            printer.printRecord(label, bucket.getLow(), bucket.getHigh(), count);
        }
        printer.flush();
        printer.close();
    }
}

package com.tone;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.*;

public class TestCSV {
    @Test
    public void readCsv1() throws IOException {
        Reader in = new FileReader("/Users/Dongsys/Downloads/2017-03/000776_2017-03-01.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
//            String lastName = record.get("Last Name");
//            String firstName = record.get("First Name");
            System.out.println(record);
        }
    }

    @Test
    public void readCsv2() throws IOException {
        final Reader reader = new FileReader("/Users/Dongsys/Downloads/2017-01/002590_2017-01-26.csv");
        final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
        try {
            for (final CSVRecord record : parser) {
                final String time = record.get("time");
                final String price = record.get("price");
                final String change = record.get("change");
                final String volume = record.get("volume");
                final String amount = record.get("amount");
                final String type = record.get("type");
                System.out.println("time = " + time + ", price = " + price + ", change = " + change + ", volume = " + volume + ", amount = " + amount + ", type = " + type);
                System.out.println(record);
            }
        } finally {
            parser.close();
            reader.close();
        }
    }

    @Test
    public void writeCsv1() throws IOException {
        final Appendable out = new StringBuilder();
        out.append("1").append("2");
        final CSVPrinter printer = CSVFormat.DEFAULT.withHeader("H1", "H2").print(out);
    }

}

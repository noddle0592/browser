package com.tone.back;

import com.tone.gf.AppInfo;
import com.tone.gf.strategy.StrategyT1;
import com.tone.gf.strategy.model.ModelT1;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.After;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * T1策略回溯
 * 注意：1. 测试时，关闭T1策略的while START判断，以及sleep设置
 */
public class T1BackTest {
    private final String code = "000776";
//    private final String code = "600297";
//    private final String code = "002590";
    private final double floatPrice = 0.1;
    private final int amount = 10;
    private BackConsume backConsume;

    @After
    public void after() throws InterruptedException {
        Thread.sleep(2000);
        // back sum
        backConsume.getSummary(floatPrice, amount);
    }

    @Test
    public void back20170301() throws IOException {
        final String fileName = String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-01.csv", code);
        final double closePrice = 17.67;
        this.backTest(closePrice, fileName);
    }

    @Test
    public void back20170302() throws IOException {
        final String fileName = String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-02.csv", code);
        final double closePrice = 17.6;
        this.backTest(closePrice, fileName);
    }

    @Test
    public void back20170303() throws IOException {
        final String fileName = String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-03.csv", code);
        final double closePrice = 17.77;
        this.backTest(closePrice, fileName);
    }

    @Test
    public void back20170306() throws IOException {
        final String fileName = String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-06.csv", code);
        final double closePrice = 17.6;
        this.backTest(closePrice, fileName);
    }

    @Test
    public void back20170307() throws IOException {
        final String fileName = String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-07.csv", code);
        final double closePrice = 17.6;
        this.backTest(closePrice, fileName);
    }

    @Test
    public void back201701() throws IOException {
        final String[] fileNames = new String[]{
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-03.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-04.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-05.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-06.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-09.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-10.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-11.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-12.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-13.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-16.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-17.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-18.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-19.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-20.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-23.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-24.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-25.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-01/%s_2017-01-26.csv", code)
        };
//        final double closePrice = 16.88;
        final double closePrice = 20.42;
        AppInfo.CLOSE_PRICES.put(code, closePrice);
        this.backTest(closePrice, fileNames);
    }

    @Test
    public void back201702() throws IOException {
        final String[] fileNames = new String[]{
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-03.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-06.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-07.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-08.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-09.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-10.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-13.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-14.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-15.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-16.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-17.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-20.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-21.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-22.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-23.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-24.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-27.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-28.csv", code)
        };
        final double closePrice = 17.58;
        AppInfo.CLOSE_PRICES.put(code, closePrice);
        this.backTest(closePrice, fileNames);
    }

    @Test
    public void back201703() throws IOException {
        final String[] fileNames = new String[]{
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-01.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-02.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-03.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-06.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-07.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-08.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-09.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-10.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-13.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-14.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-15.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-16.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-17.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-20.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-21.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-22.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-23.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-24.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-27.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-28.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-29.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-30.csv", code),
            String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-31.csv", code)
        };
        final double closePrice = 17.53;
//        final double closePrice = 9.68;
        AppInfo.CLOSE_PRICES.put(code, closePrice);
        this.backTest(closePrice, fileNames);
    }

    @Test
    public void back201704() throws IOException {
        final String[] fileNames = new String[]{
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-05.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-06.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-07.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-10.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-11.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-12.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-13.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-14.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-17.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-18.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-19.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-20.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-21.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-24.csv", code)
        };
        final double closePrice = 17.1;
        AppInfo.CLOSE_PRICES.put(code, closePrice);
        this.backTest(closePrice, fileNames);
    }

    @Test
    public void back2017020304() throws IOException {
        final String[] fileNames = new String[]{
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-03.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-06.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-07.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-08.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-09.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-10.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-13.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-14.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-15.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-16.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-17.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-20.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-21.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-22.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-23.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-24.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-27.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-02/%s_2017-02-28.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-01.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-02.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-03.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-06.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-07.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-08.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-09.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-10.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-13.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-14.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-15.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-16.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-17.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-20.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-21.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-22.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-23.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-24.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-27.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-28.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-29.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-30.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-03/%s_2017-03-31.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-05.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-06.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-07.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-10.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-11.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-12.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-13.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-14.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-17.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-18.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-19.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-20.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-21.csv", code),
                String.format("/Users/Dongsys/Downloads/2017-04/%s_2017-04-24.csv", code)
        };
        final double closePrice = 19.4;
        AppInfo.CLOSE_PRICES.put(code, closePrice);
        this.backTest(closePrice, fileNames);
    }

    private void backTest(double closePrice, String... fileNames) throws IOException {
        ModelT1 model = new ModelT1(closePrice, floatPrice, amount);
        model.setCode(code);
        backConsume = new BackConsume();
        StrategyT1 strategyT1 = new StrategyT1(model);
        if (fileNames != null) {
            for (String fileName : fileNames) {
                final Reader reader = new FileReader(fileName);
                final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
                try {
                    double lastPrice = 0;
                    for (final CSVRecord record : parser) {
                        final Double price = Double.parseDouble(record.get("price"));
                        AppInfo.PRICES.put(code, price);
                        strategyT1.run();
                        backConsume.checkReal(price);
                        lastPrice = price;
                    }
                    AppInfo.CLOSE_PRICES.put(code, lastPrice);
                } finally {
                    parser.close();
                    reader.close();
                }
                backConsume.oneDayClean();
            }
        }
    }
}

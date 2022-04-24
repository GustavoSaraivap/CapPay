package com.capgemini.cappay.utils;

import com.capgemini.cappay.model.Account;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Configuration
public class CsvHelper {

    @Bean
    public static ByteArrayInputStream accountToCSV(List<Account> accountList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Account account : accountList) {
                List<? extends Serializable> data = Arrays.asList(
                        String.valueOf(account.getId()),
                        account.getAccountStatus(),
                        account.getAccountType(),
                        account.getBalance()
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}

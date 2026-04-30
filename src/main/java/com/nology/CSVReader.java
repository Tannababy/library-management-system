package com.nology;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    public static void main(String[] args) {


        // csv file path
        String csvPath = "com/nology/books_data.csv";

        List<List<String>> data = new ArrayList<>();



        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] values = line.split(",");
                List<String> lineData = Arrays.asList(values);

                data.add(lineData);

            }

            System.out.println("Data read from CSV file");

            for (int i = 0; i < data.size(); i++) {

                List<String> row = data.get(i);
                System.out.println("Row " + i + ": " + String.join(",", row));
            }


        } catch (IOException e) {

            System.err.println("CSV file not found: " + e.getMessage());
            e.printStackTrace();
        }

    }
}

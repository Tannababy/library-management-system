package com.nology;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    public static void main(String[] args) {


        // csv file path
        String csvPath = "books_data.csv";

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


                String bookTitle = row.get(1);
                String bookAuthor = row.get(2);
                System.out.println("Row " + i + ": Book: " + bookTitle + ", By: " + bookAuthor);


            }


        } catch (IOException e) {

            System.err.println("CSV file not found: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // [
    // [ 0, 1, 3, 4  ]
    // [ 0, 1, 3, 4  ]
    // [ 0, 1, 3, 4  ]
    // ]
}

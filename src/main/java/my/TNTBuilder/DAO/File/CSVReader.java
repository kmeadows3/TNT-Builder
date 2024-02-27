package my.TNTBuilder.DAO.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {


    public List<String> readCsvFile(File itemCSV) throws FileNotFoundException {
        List<String> inputLines = new ArrayList<>();
        try(Scanner reader = new Scanner(itemCSV)){
            while (reader.hasNextLine()){
                inputLines.add(reader.nextLine());
            }
        }
        return inputLines;
    }
}

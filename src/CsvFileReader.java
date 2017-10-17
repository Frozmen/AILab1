import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 22.11.2016.
 */
public class CsvFileReader {

    //CSV file header
    private static final String[] FILE_HEADER_MAPPING = {"Name", "SpAtk", "SpDef", "Speed"};


    public static List<PokemonModel> readCsvFile() {

        FileReader fileReader = null;
        CSVParser csvFileParser = null;
        //Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
        List<PokemonModel> data = new ArrayList<>();

        try {
            //Create a new list of student to be filled by CSV file data
            List pokemons = new ArrayList();
            //initialize FileReader object
            fileReader = new FileReader("pokemon.csv");
            //initialize CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            //Get a list of CSV file records
            List<CSVRecord> csvRecords = csvFileParser.getRecords();
            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                record.get(FILE_HEADER_MAPPING[0]);
                PokemonModel pokemon = new PokemonModel(record.get(FILE_HEADER_MAPPING[0]), record.get(FILE_HEADER_MAPPING[1]), record.get(FILE_HEADER_MAPPING[2]), record.get(FILE_HEADER_MAPPING[3]));
                data.add(pokemon);
                System.out.println(pokemon.toString());
            }
        } catch (Exception e) {
            System.out.println("Error in utils.CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }
        return data;
    }
}

package net.sasconsul.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

@SpringBootApplication
public class FlagsParserApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FlagsParserApplication.class, args);
    }

    ArrayList<Continent> continents = new ArrayList<>();

    @Override
    public void run(String[] args) throws Exception {

        FileWriter continentsWriter = new FileWriter("./contient.csv");
        PrintWriter continentsCsv = new PrintWriter(continentsWriter);
        continentsCsv.printf("id;continent%n");

        FileWriter countriesWriter = new FileWriter("./country.csv");
        PrintWriter countriesCsv = new PrintWriter(countriesWriter);
        countriesCsv.printf("id;name;flag;continent_id%n");

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        /*=========JSON FILE TO TREE MODEL=============*/

        //read customer.json file into tree model
        JsonNode dateFile = objectMapper.readTree(new File("continents.json"));
        if( dateFile.isArray() != true) {
            throw new Exception("File does not start with a JSONArray");
        }
        int continentsIdx = 0;
        int countryIdx = 0;
        Iterator<JsonNode> continentsItr = dateFile.iterator();
        while (continentsItr.hasNext()) {
            JsonNode continentNode = continentsItr.next();
            String cName = continentNode.get("continent").textValue();
            System.out.println("continent: "+ cName);
            continentsCsv.printf("%d;%s%n",continentsIdx, cName);

            Iterator<JsonNode> countries = continentNode.get("countries").iterator();
            ArrayList<Country> countriesArray = new ArrayList<>();

            while (countries.hasNext()) {
                JsonNode countryNode = countries.next();
                Country country = new Country(countryNode.get("name").asText(), countryNode.get("flag").asText());
                countriesArray.add(country);
                countriesCsv.printf("%d;%s;%s;%d%n",
                        countryIdx, country.getName(), country.getFlag(), continentsIdx);
                countryIdx++;

            }
            Continent continent = new Continent(continentsIdx, cName, countriesArray);
            continents.add(continent);
            continentsIdx++;
        }
        countriesCsv.close();
        continentsCsv.close();
    }

}

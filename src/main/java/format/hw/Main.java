package format.hw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;

import java.lang.reflect.Type;
import java.util.List;

public class Main {
    private static final String PATH_TO_CSV_FILE = "fromJavaToCsv.csv";
    private static final String PATH_TO_JSON_FILE = "fromJavaToJson";

    public static void main(String[] args) throws FileNotFoundException {
        generateCsvFile(generateListEmployee());
        generateJsonFromJava(readFromCsvFile());
        for (Employee employee:readFromJsonToJavaClass()) {
            System.out.println(employee);
        }

    }

    private static List<Employee> generateListEmployee() {
        return List.of(
                new Employee(1, "name1", "lastName1", "country1", 18),
                new Employee(2, "name2", "lastName2", "country2", 20),
                new Employee(3, "name3", "lastName3", "country3", 22));
    }

    private static void generateCsvFile(List<Employee> list) {
        ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Employee.class);
        strategy.setColumnMapping("id", "name", "lastname", "country", "age");
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(PATH_TO_CSV_FILE, false))) {
            StatefulBeanToCsv<Employee> statefulBeanToCsv = new StatefulBeanToCsvBuilder<Employee>(csvWriter)
                    .withMappingStrategy(strategy)
                    .build();
            statefulBeanToCsv.write(list);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Employee> readFromCsvFile() {
        List<Employee> list = null;
        ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Employee.class);
        strategy.setColumnMapping("id", "name", "lastname", "country", "age");
        try (CSVReader csvReader = new CSVReader(new FileReader(PATH_TO_CSV_FILE))) {
            CsvToBean<Employee> personCsvToBean = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();

            list = personCsvToBean.parse();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    private static void generateJsonFromJava(List<Employee> list) {
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        try (FileWriter fileWriter = new FileWriter(PATH_TO_JSON_FILE, false)) {
            fileWriter.write(gson.toJson(list, listType));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Employee> readFromJsonToJavaClass() throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_JSON_FILE))){
           String str;
           while ((str = br.readLine()) != null){
               stringBuilder.append(str);
           }
        }catch (IOException e){
            System.out.println(e);
        }
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        return gson.fromJson(String.valueOf(stringBuilder),new TypeToken<List<Employee>>() {
        }.getType());

    }
}

package сsv;

import com.opencsv.*;
import com.opencsv.bean.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //writeNext();
        //readNext();
        //readAll();
        //readWithBuilder();
        //readWithBuilder();
       // readWithParseToObjectClass();
        writeWithParseToObjectClass();
    }

    public static void writeWithParseToObjectClass(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"name1","age1",23));
        personList.add(new Person(2,"name2","age2",33));
        //создаем стратегию парсинга

        ColumnPositionMappingStrategy<Person> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Person.class);
        strategy.setColumnMapping("id","name","age","count");
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("new_staffFile.csv"))){
            StatefulBeanToCsv<Person> statefulBeanToCsv = new StatefulBeanToCsvBuilder<Person>(csvWriter)
                    .withMappingStrategy(strategy)
                    .build();
            statefulBeanToCsv.write(personList);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
    public static void readWithParseToObjectClass(){
        try(CSVReader csvReader = new CSVReader(new FileReader("staff.csv"))) {
             //задаем стратегию парсинга объекта
            ColumnPositionMappingStrategy<Person> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Person.class);
            strategy.setColumnMapping("id","name","age","count");

            CsvToBean<Person> personCsvToBean = new CsvToBeanBuilder<Person>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();
            // List<workTithCsv.Person> list = personCsvToBean.parse();
            //list. ...
            personCsvToBean.parse().forEach(System.out::println);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public  static void readWithBuilder(){
        CSVParser csvParserBuilder = new CSVParserBuilder()
                .withSeparator('|')
                .build();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader("staff.csv"))
                .withCSVParser(csvParserBuilder)
                .build()){
         String [] array;
         while ((array = csvReader.readNext()) != null){
             System.out.println(Arrays.toString(array));
         }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void readAll(){
        try (CSVReader csvReader= new CSVReader(new FileReader("staff.csv"))){
            List<String[]> listWithArray = csvReader.readAll();
            for (String [] arr: listWithArray) {
                System.out.println(Arrays.toString(arr));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void readNext() {
        try (
                CSVReader csvReader = new CSVReader(new FileReader("staff.csv"))
        ) {
            String[] strLine;
            while ((strLine = csvReader.readNext()) != null) {
                System.out.println(Arrays.toString(strLine));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeNext() {
        String[] arrayStr = "1,Name,Age,26".split(",");
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("staff.csv"))) {
            csvWriter.writeNext(arrayStr);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //codeWars
    public static int zeros(int n) {
        int point = 5;
        int step = 5;
        int answer = 0;
        while (n >= point) {
            int localAnswer = n / point;
            answer = answer + localAnswer;
            point = point * step;
        }
        return answer;
    }
}

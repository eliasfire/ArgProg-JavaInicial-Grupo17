package com.argpro.pronostico.main;

import com.argpro.pronostico.model.Partido;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import java.io.FileReader;
import java.net.URL;
import java.util.List;

public class CSVMappedToJavaBeanExample {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) throws Exception {

        URL fileUrl = CSVMappedToJavaBeanExample.class.getClassLoader().getResource("pronostico.csv");
        CSVReader csvReader = new CSVReader(new FileReader(fileUrl.getFile()));

        CsvToBean csv = new CsvToBean();
        csv.setCsvReader(csvReader);
        csv.setMappingStrategy(setColumMapping());

        List list = csv.parse();

        for (Object object : list) {
            Partido employee = (Partido) object;
            System.out.println(employee);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ColumnPositionMappingStrategy setColumMapping() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Partido.class);
        String[] columns = new String[]{"id", "firstName", "lastName", "country", "age"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}
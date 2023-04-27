package com.argpro.pronostico.main;

import com.argpro.pronostico.model.Pronosticos;
import com.argpro.pronostico.model.Resultados;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub

        List<Resultados> listaDeResultados;
        List<Pronosticos> listaDePronosticos;

        listaDeResultados = new CsvToBeanBuilder(new FileReader(args[0], StandardCharsets.UTF_8))
                .withSkipLines(1)
                .withType(Resultados.class)
                .build()
                .parse();

        listaDePronosticos = new CsvToBeanBuilder(new FileReader(args[1], StandardCharsets.UTF_8))
                .withSkipLines(1)
                .withType(Pronosticos.class)
                .build()
                .parse();

        URL fileUrl = CSVMappedToJavaBeanExample.class.getClassLoader().getResource("pronosticos.csv");
        CSVReader csvReader = new CSVReader(new FileReader(fileUrl.getFile()));

        CsvToBean csv = new CsvToBean();
        csv.setCsvReader(csvReader);
        csv.setMappingStrategy(setColumMapping());

        List list = csv.parse();

        for (Object object : list) {
            Pronosticos employee = (Pronosticos) object;
            System.out.println(employee.getParticipante());

            System.out.println(employee.getEquipo1());
            System.out.println(employee.getEquipo2());

        }
    }

        @SuppressWarnings({"rawtypes", "unchecked"})
        private static ColumnPositionMappingStrategy setColumMapping() {
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(Pronosticos.class);
            String[] columns = new String[]{"id", "firstName", "lastName", "country", "age"};
            strategy.setColumnMapping(columns);
            return strategy;
        }

    public List<Pronosticos> simplePositionBeanExample() throws Exception {
        Path path = Paths.get(
                ClassLoader.getSystemResource("csv/twoColumn.csv").toURI());
        return BeanExamples.beanBuilderExample(path, Pronosticos.class);
    }

    }

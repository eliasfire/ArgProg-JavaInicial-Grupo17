package com.argpro.pronostico.main;

import com.argpro.pronostico.model.Participante;
import com.argpro.pronostico.model.Pronosticos;
import com.argpro.pronostico.model.Resultados;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Participante> participantes = new ArrayList<Participante>();

    public static void main(String[] args) throws IOException {

        List<Resultados> listadoResultados = getResultados();
        List<Pronosticos> listadoPronosticos = getPronosticos();

        //iteramos sobre pronosticos
        for (Pronosticos pronostico : listadoPronosticos) {

            Participante participante = new Participante(pronostico.getParticipante(), 0);
            //si no existe lo agrego
            if (!ExisteParticipante(participante))
                participantes.add(participante);

            //recorremos Resultados buscando el partido correspondiente al pronostico
            for (Resultados resultado : listadoResultados) {

                //Buscamos el partido
                if (resultado.getEquipo1().equalsIgnoreCase(pronostico.getEquipo1()) && resultado.getEquipo2().equalsIgnoreCase(pronostico.getEquipo2())) {

                    //comparo los resultados
                    if (resultado.resultadoEquipo1() == pronostico.resultadoEquipo1()) {
                        sumar(participante);
                    }
                }
            }
        }

        //Imprimos los aciertos de cada participante
        for (Participante participante : participantes) {
            System.out.println("Aciertos de " + participante.get_nombre() + ": " + participante.get_puntos());
        }

    }

    public static List<Pronosticos> getPronosticos() throws IOException {
        URL CSV_PRONOSTICOS = Main.class.getClassLoader().getResource("pronosticos.csv");
        return new CsvToBeanBuilder(new FileReader(CSV_PRONOSTICOS.getFile(), StandardCharsets.UTF_8))
                .withSkipLines(1)
                .withType(Pronosticos.class)
                .build()
                .parse();
    }

    private static List<Resultados> getResultados() throws IOException {
        URL CSV_RESULTADOS = Main.class.getClassLoader().getResource("resultados.csv");
        return new CsvToBeanBuilder(new FileReader(CSV_RESULTADOS.getFile(), StandardCharsets.UTF_8))
                .withSkipLines(1)
                .withType(Resultados.class)
                .build()
                .parse();
    }

    private static boolean ExisteParticipante(Participante participante) {
        boolean existe = false;
        for (int i = 0; i < participantes.size(); i++) {
            if (participantes.get(i).get_nombre().trim().equals(participante.get_nombre().trim())) {
                existe = true;
                break;
            }
        }
        return existe;
    }
    private static void sumar(Participante participante) {
        for (int i = 0; i < participantes.size(); i++) {
            if (participantes.get(i).get_nombre().trim().equals(participante.get_nombre().trim())) {
                participantes.get(i).set_puntos(participantes.get(i).get_puntos() + 1);
                break;
            }
        }
    }
}

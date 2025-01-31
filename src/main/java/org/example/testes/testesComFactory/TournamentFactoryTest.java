package org.example.testes.testesComFactory;

import org.example.simulation.geneticOperatorFactory.TournamentWithReplacementInstantiator;

import java.util.HashMap;
import java.util.Map;

public class TournamentFactoryTest {

    public static void main(String [] args) throws InstantiationException {

        TournamentWithReplacementInstantiator tournament = new TournamentWithReplacementInstantiator();

        Map<String, Object> argumentos = new HashMap<>();

        argumentos.put("seed", (long)10);
        argumentos.put("tournamentSize", 100);

        var t = tournament.build(argumentos);

        tournament.getParametersList().forEach((k, v) -> System.out.print(v));
    }
}

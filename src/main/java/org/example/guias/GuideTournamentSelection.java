package org.example.guias;

import org.example.cost.DefaultCostCalculator;
import org.example.geneticOperators.selection.TournamentWithReplacement;
import org.example.individual.Individual;
import org.example.individual.Population;
import org.example.movimentation.DefaultMovimentation;
import org.example.scenario.Scenario;
import org.example.uteis.GerarAleatorios;

import java.awt.*;
import java.util.List;

public class GuideTournamentSelection {

    private static Scenario scenario = new Scenario(10, 10, 30l);

    static {

        scenario.setCostInScenario(100, 1000);
    }

    private static Point startPoint = new Point(0, 0);
    private static Point endPoint = new Point(9,9);
    private static DefaultMovimentation defaultMovimentation = new DefaultMovimentation(scenario, startPoint, endPoint);
    private static DefaultCostCalculator defaultCostCalculator = new DefaultCostCalculator(defaultMovimentation.getScenario(),
            defaultMovimentation);

    private static GerarAleatorios gerarAleatorios = new GerarAleatorios(defaultCostCalculator, defaultMovimentation,
            10l);

    public static void main(String [] args){

        //tournamentsize = quantos indivíduos são selecionados para cada torneio
        //Exemplo: se tournamentsize for 10, então a cada torneio, são selecionados 10 indivíduos. O vencedor
        //sai desses 10
        TournamentWithReplacement tournamentWithReplacement = new TournamentWithReplacement(10);

        //gerando uma população aleatória:
        List<Individual> p = gerarAleatorios.gerarAleatorios(startPoint, endPoint, 100, 10000);

        Population population = new Population();
        population.addAll(p);

        //selecionando 2 indivídos atrás do torneio:
        Population selected = tournamentWithReplacement.select(population, 2);

        System.out.println("Custo dos indivíduos selecionados:");

        for (Individual individual : population) {

            System.out.println(individual.getCost());
        }

        System.out.println("Resto da população:");

    }
}

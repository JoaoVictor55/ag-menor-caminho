package org.geneticAlgorithm.guias;

import org.geneticAlgorithm.cost.DefaultCostCalculator;
import org.geneticAlgorithm.geneticOperators.selection.TournamentWithReplacement;
import org.geneticAlgorithm.guias.tools.BuildExamples;
import org.geneticAlgorithm.individual.Individual;
import org.geneticAlgorithm.individual.Population;
import org.geneticAlgorithm.movimentation.DefaultMovimentation;
import org.geneticAlgorithm.scenario.Scenario;
import org.geneticAlgorithm.uteis.GerarAleatorios;

import java.awt.*;
import java.util.List;

public class GuideTournamentSelection {

    private static Scenario scenario = new Scenario(10, 10, 30l);

    static {

        scenario.setCostInScenario(100, 1000);
    }

    private static Point startPoint = new Point(0, 0);
    private static Point endPoint = new Point(9,9);
    private static DefaultMovimentation defaultMovimentation = BuildExamples.buildDefautMovimentation(startPoint, endPoint, scenario);
    private static DefaultCostCalculator defaultCostCalculator = BuildExamples.buildDefaultCostCalculator(defaultMovimentation.getScenario(),
            defaultMovimentation);

    private static GerarAleatorios gerarAleatorios = new GerarAleatorios(defaultCostCalculator, defaultMovimentation,
            10l);

    public static void main(String [] args){

        //tournamentsize = quantos indivíduos são selecionados para cada torneio
        //Exemplo: se tournamentsize for 10, então a cada torneio, são selecionados 10 indivíduos. O vencedor
        //sai desses 10
        TournamentWithReplacement tournamentWithReplacement = new TournamentWithReplacement(10);

        //gerando uma população aleatória:
        List<Individual> p = gerarAleatorios.gerarAleatorios(startPoint, endPoint, 30, 10000);

        Population population = new Population();
        population.addAll(p);


        //selecionando 2 indivídos atrás do torneio:
        Population selected = tournamentWithReplacement.select(population, 2);

        System.out.println("Custo dos indivíduos selecionados:");

        for (Individual individual : selected) {

            System.out.println(individual.getCost());
        }

        System.out.println("\nResto da população:");
        for(Individual individual : population){

            System.out.println(individual.getCost());
        }

    }
}

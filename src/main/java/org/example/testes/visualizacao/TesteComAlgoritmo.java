package org.example.testes.visualizacao;

import org.example.geneticAlgorithm.GeneticAlgorithm;
import org.example.apresentacao.VisualizarCenario;
import org.example.scenario.Scenario;
import org.example.cost.CostCalculator;
import org.example.cost.DefaultCostCalculator;
import org.example.individual.Individual;
import org.example.individual.Population;
import org.example.movimentation.Movimentation;
import org.example.movimentation.DefaultMovimentation;
import org.example.geneticOperators.crossover.Crossover;
import org.example.geneticOperators.crossover.crossoverRecombinacao.CrossoverEdgeRecombination;
import org.example.geneticOperators.mutation.Mutation;
import org.example.geneticOperators.mutation.singlePointMutation.SinglePointMutation;
import org.example.geneticOperators.selection.Selection;
import org.example.geneticOperators.selection.TournamentWithReplacement;
import org.example.uteis.GerarAleatorios;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TesteComAlgoritmo {

    public static void main(String [] args) {

        long semente = (long) 0;

        Scenario scenario = new Scenario(10, 10, semente);

        scenario.setCostInScenario(1, 2);

 //       cenario.colocarBloqueios(0.6f);

        List<Point> bloqueios = new ArrayList<Point>();

        bloqueios.add(new Point(2, 1));
        bloqueios.add(new Point(2, 2));
        bloqueios.add(new Point(2, 3));
        bloqueios.add(new Point(2, 4));
        bloqueios.add(new Point(2, 5));

        bloqueios.add(new Point(7, 3));
        bloqueios.add(new Point(7, 4));
        bloqueios.add(new Point(7, 5));
        bloqueios.add(new Point(7, 6));
        bloqueios.add(new Point(7, 7));

        bloqueios.add(new Point(2, 8));
        bloqueios.add(new Point(3, 8));
        bloqueios.add(new Point(4, 8));

        scenario.putBlocking(bloqueios);



        Point inicio = new Point(0, 0);
        Point fim = new Point(9, 9);
        Movimentation movimentation = new DefaultMovimentation(scenario, inicio, fim);
        CostCalculator costCalculator = new DefaultCostCalculator(scenario, movimentation);

        GerarAleatorios gerarAleatorios = new GerarAleatorios(costCalculator, movimentation, semente);

        List<Individual> i = gerarAleatorios.gerarAleatorios(inicio, fim, 10, 10000);

        Selection selection = new TournamentWithReplacement(5, semente);
        Mutation mutation = new SinglePointMutation(semente, movimentation, 0.06f);
        Crossover crossover = new CrossoverEdgeRecombination(semente, movimentation);

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(selection, mutation, crossover,semente);

        Population population = new Population();
        Population populationInicial = new Population();
        populationInicial.addIndividual(i);

        Population populationAtual;

        Population populationPrintar = new Population();



        for(int a = 0; a < 10; ++a){

            populationPrintar.pushIndividual(populationInicial.getBest());
            populationAtual = geneticAlgorithm.generatePopulation(populationInicial, 10,3);
            populationPrintar.pushIndividual(populationAtual.getBest());
            populationInicial = populationAtual;
        }

        new VisualizarCenario(scenario, populationPrintar);

    }
}

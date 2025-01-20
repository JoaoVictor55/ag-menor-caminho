package org.example.testes;

import org.example.geneticAlgorithm.GeneticAlgorithm;
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

import java.awt.*;
import java.util.List;

public class TesteAlgoritmoGenetico {

    public static void main(String[] args){

    long semente = (long) 5;

    Scenario scenario = new Scenario(50, 50, semente);

    scenario.setCostInScenario(0,950);
    Point inicio = new Point(0, 0);
    Point fim = new Point(49, 49);
    Movimentation movimentation = new DefaultMovimentation(scenario, inicio, fim);
    CostCalculator costCalculator = new DefaultCostCalculator(scenario, movimentation);

    Selection selection = new TournamentWithReplacement(50, semente);

    GerarAleatorios gerarAleatorios = new GerarAleatorios(costCalculator, movimentation,semente);

    Mutation mutation = new SinglePointMutation(semente, movimentation, 0.01f, 10000);

    Crossover crossover = new CrossoverEdgeRecombination(semente, movimentation);

    List<Individual> populacao = gerarAleatorios.gerarAleatorios(inicio, fim, 100, 10000);

    Population populationInicial = new Population();
    populationInicial.addIndividual(populacao);

    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(selection, mutation, crossover, semente);
    Population novaPop;

    for(int a = 0; a < 6; ++a){

        novaPop = geneticAlgorithm.generatePopulation(populationInicial,10, 10);
        System.out.println(a);
        System.out.println("Nova populacao fitness: "+novaPop.getBest().getCost());
        System.out.println("Antiga populacao fitness: "+ populationInicial.getBest().getCost());
        System.out.println("\n");

        populationInicial = novaPop;
    }


}
}

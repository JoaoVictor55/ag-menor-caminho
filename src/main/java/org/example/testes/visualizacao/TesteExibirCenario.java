package org.example.testes.visualizacao;

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
import org.example.uteis.GerarAleatorios;

import java.awt.*;
import java.util.List;

public class TesteExibirCenario {

    public static void main(String [] args){

        long semente = (long)0;

        Scenario scenario = new Scenario(10, 10, semente);

        scenario.setCostInScenario(10, 300);
        scenario.putBlocking(0.3f);

        Point inicio =  new Point(0, 0);
        Point fim = new Point(9, 9);
        Movimentation movimentation = new DefaultMovimentation(scenario,inicio, fim);
        CostCalculator costCalculator = new DefaultCostCalculator(scenario, movimentation);
        GerarAleatorios gerarAleatorios = new GerarAleatorios(costCalculator, movimentation, semente);

        List<Individual> individuals = gerarAleatorios.gerarAleatorios(inicio, fim, 10, 1000);

        Population population = new Population();

        population.addIndividual(individuals);

        Mutation mutation = new SinglePointMutation(semente, movimentation, 1.0f);

        Crossover crossover = new CrossoverEdgeRecombination(semente, movimentation);

        VisualizarCenario visualizarCenario = new VisualizarCenario(scenario, population);
    }
}

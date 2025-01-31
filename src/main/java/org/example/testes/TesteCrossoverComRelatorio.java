package org.example.testes;

import org.example.geneticOperators.OperatorWithReport;
import org.example.geneticOperators.crossover.Crossover;
import org.example.scenario.Scenario;
import org.example.cost.CostCalculator;
import org.example.cost.DefaultCostCalculator;
import org.example.individual.Individual;
import org.example.movimentation.Movimentation;
import org.example.movimentation.DefaultMovimentation;
import org.example.geneticOperators.crossover.crossoverRecombinacao.CrossoverEdgeRecombination;
import org.example.geneticOperators.crossover.crossoverRecombinacao.EdgeRecombination;
import org.example.geneticOperators.crossover.crossoverRecombinacao.ReportEdgeRecombinationComposer;
import org.example.uteis.GerarAleatorios;

import java.awt.*;
import java.util.List;

public class TesteCrossoverComRelatorio {

    public static void main(String [] args){

        long semente = 1;

        Scenario scenario = new Scenario(6, 6, semente);

        scenario.setCostInScenario(130, 200);
        Point inicio =  new Point(0, 0);
        Point fim = new Point(5, 5);
        Movimentation movimentation = new DefaultMovimentation(scenario,inicio, fim);
        CostCalculator costCalculator = new DefaultCostCalculator(scenario, movimentation);

        GerarAleatorios gerarAleatorios = new GerarAleatorios(costCalculator, movimentation,semente);
        java.util.List<Individual> populacao = gerarAleatorios.gerarAleatorios(inicio, fim, 2, 999999);

        //Populacao populacao1 = new Populacao(populacao, semente);

        CrossoverEdgeRecombination crossover = new CrossoverEdgeRecombination(semente, movimentation);

        Individual pai = new Individual(movimentation, costCalculator);
        pai.pushPosition(inicio);
        pai.pushPosition(new Point(0,1));
        pai.pushPosition(new Point(0,2));
        pai.pushPosition(new Point(0,3));
        pai.pushPosition(new Point(0,4));
        pai.pushPosition(new Point(0,5));
        pai.pushPosition(new Point(1,5));
        pai.pushPosition(new Point(2,5));
        pai.pushPosition(new Point(3,5));
        pai.pushPosition(new Point(4,5));
        pai.pushPosition(fim);

        Individual mae = new Individual(movimentation, costCalculator);
        mae.pushPosition(inicio);

        mae.pushPosition(new Point(1,1));
        mae.pushPosition(new Point(2,2));
        mae.pushPosition(new Point(2,3));
        mae.pushPosition(new Point(2,4));
        mae.pushPosition(new Point(3,4));
        mae.pushPosition(new Point(4,4));
        /*mae.adicionarPosicao(new Point(3,3));
        mae.adicionarPosicao(new Point(4,4));*/
        mae.pushPosition(fim);


        //var mae = populacao.get(1);
        //var pai = populacao.get(0);

        crossover.setActiveReport(true);
        List<Individual> i = crossover.crossover(pai, mae);

        var custoFilho = i.get(0).getCost();
        var tamanhoFilho = i.get(0).getSize();

        var reportEdgeRecombinationComposer = crossover.getReport();


        for(int a = 0; a < reportEdgeRecombinationComposer.reasons().size(); ++a){

            System.out.println(reportEdgeRecombinationComposer.reasons().get(a));
            System.out.println(reportEdgeRecombinationComposer.adjacent().get(a));
            System.out.println(reportEdgeRecombinationComposer.adjacentTo().get(a));
        }

        crossover.setActiveReport(false);


    }
}

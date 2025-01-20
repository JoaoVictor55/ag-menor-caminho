package org.example.testes;

import org.example.scenario.Scenario;
import org.example.cost.CostCalculator;
import org.example.cost.DefaultCostCalculator;
import org.example.individual.Individual;
import org.example.individual.Population;
import org.example.movimentation.Movimentation;
import org.example.movimentation.DefaultMovimentation;
import org.example.uteis.GerarAleatorios;

import java.awt.*;
import java.util.List;

public class TesteMetricas {

    public static void main(String [] args) throws CloneNotSupportedException {

        long semente = (long)0;

        Scenario scenario = new Scenario(10, 10, semente);

        scenario.setCostInScenario(100, 950);
        Point inicio =  new Point(0, 0);
        Point fim = new Point(9, 9);
        Movimentation movimentation = new DefaultMovimentation(scenario,inicio, fim);
        CostCalculator costCalculator = new DefaultCostCalculator(scenario, movimentation);

        Individual i = new Individual(movimentation, costCalculator);


        GerarAleatorios gerarAleatorios = new GerarAleatorios(costCalculator, movimentation, semente);

        List<Individual> individualList = gerarAleatorios.gerarAleatorios(inicio, fim, 1, 10000);


        Population population = new Population(individualList,semente);

        System.out.println(population.getBest().getCost());

        System.out.println(population.getWorst().getCost());

        List<Point> pos = population.getWorst().getPath();


        System.out.println(population.getAverage());

        System.out.println(population.getSd());

        population.sort();




    }
}

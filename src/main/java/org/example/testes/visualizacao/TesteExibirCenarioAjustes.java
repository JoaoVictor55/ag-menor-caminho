package org.example.testes.visualizacao;

import org.example.apresentacao.VisualizarCenario;
import org.example.scenario.Scenario;
import org.example.cost.CostCalculator;
import org.example.cost.DefaultCostCalculator;
import org.example.individual.Individual;
import org.example.individual.Population;
import org.example.movimentation.Movimentation;
import org.example.movimentation.DefaultMovimentation;

import java.awt.*;

public class TesteExibirCenarioAjustes {

    public static void main(String [] args) {

        long semente = (long) 0;

        Scenario scenario = new Scenario(10, 10, semente);

        scenario.setCostInScenario(10, 300);
        scenario.putBlocking(0.3f);

        Point inicio = new Point(0, 0);
        Point fim = new Point(9, 9);
        Movimentation movimentation = new DefaultMovimentation(scenario, inicio, fim);
        CostCalculator costCalculator = new DefaultCostCalculator(scenario, movimentation);

        Population population = new Population();
        Individual individual = new Individual(movimentation, costCalculator);

        individual.pushPosition(inicio);
        individual.pushPosition(fim);

        population.pushIndividual(individual);

        VisualizarCenario visualizarCenario = new VisualizarCenario(scenario, population);
    }
}

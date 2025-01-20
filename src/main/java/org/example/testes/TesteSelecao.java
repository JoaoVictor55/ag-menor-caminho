package org.example.testes;

import org.example.scenario.Scenario;
import org.example.cost.CostCalculator;
import org.example.cost.DefaultCostCalculator;
import org.example.individual.Individual;
import org.example.individual.Population;
import org.example.movimentation.Movimentation;
import org.example.movimentation.DefaultMovimentation;
import org.example.geneticOperators.selection.Selection;
import org.example.geneticOperators.selection.TournamentWithReplacement;
import org.example.uteis.GerarAleatorios;

import java.awt.Point;
import java.util.List;

public class TesteSelecao {

    public static void main(String [] args){

        long semente = (long)0;

        Scenario scenario = new Scenario(15, 10, semente);

        scenario.setCostInScenario(100, 950);
        Point inicio =  new Point(0, 0);
        Point fim = new Point(14, 9);
        Movimentation movimentation = new DefaultMovimentation(scenario,inicio, fim);
        CostCalculator costCalculator = new DefaultCostCalculator(scenario, movimentation);

        GerarAleatorios gerarAleatorios = new GerarAleatorios(costCalculator, movimentation,semente);
        List<Individual> populacao = gerarAleatorios.gerarAleatorios(inicio, fim, 10, 10000);

        Population population1 = new Population(populacao, semente);

        Selection selection = new TournamentWithReplacement(10, semente);

        Population selecionados = selection.select(population1, 10);

        System.out.println(selecionados.getBest().getCost());
        System.out.println(selecionados.getWorst().getCost());

    }
}

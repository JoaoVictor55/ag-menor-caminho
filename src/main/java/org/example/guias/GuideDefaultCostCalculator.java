package org.example.guias;

import org.example.cost.DefaultCostCalculator;
import org.example.guias.tools.BuildExamples;
import org.example.individual.Individual;
import org.example.movimentation.DefaultMovimentation;
import org.example.scenario.Scenario;

import java.awt.*;

public class GuideDefaultCostCalculator {

    public static void main(String [] args){

        Scenario scenario = BuildExamples.buildScanerio(10, 10); //definindo o cenário

        //definindo os pontos iniciais e finais do cenário
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(9,9);

        //construindo a movimentação
        DefaultMovimentation defaultMovimentation = BuildExamples.buildDefautMovimentation(startPoint, endPoint, scenario);

        DefaultCostCalculator defaultCostCalculator = new DefaultCostCalculator(defaultMovimentation.getScenario(),
                defaultMovimentation);

        //definindo o indivíduo
        Individual individual = new Individual(defaultMovimentation, defaultCostCalculator);

        individual.add(startPoint);
        individual.add(new Point(1,1));
        individual.add(new Point(2,2));
        individual.add(new Point(3,3));
        individual.add(new Point(4,4));
        individual.add(new Point(5,5));
        individual.add(new Point(6,6));
        individual.add(new Point(7,7));
        individual.add(new Point(8,8));
        individual.add(endPoint);

        //calculando o custo de um indivíduo:
        System.out.println(defaultCostCalculator.calculateCost(individual));

    }
}

package org.example.testes;

import org.example.scenario.Scenario;
import org.example.cost.CostCalculator;
import org.example.cost.DefaultCostCalculator;
import org.example.individual.Individual;
import org.example.individual.Population;
import org.example.movimentation.Movimentation;
import org.example.movimentation.DefaultMovimentation;
import org.example.geneticOperators.mutation.Mutation;
import org.example.geneticOperators.mutation.singlePointMutation.SinglePointMutation;

import java.awt.*;

public class TesteMutacao {

    public static void main(String [] args) throws CloneNotSupportedException {

        long semente = (long) 0;

        Scenario scenario = new Scenario(10, 10, semente);

        scenario.setCostInScenario(100, 950);
        Point inicio = new Point(0, 0);
        Point fim = new Point(9, 9);
        Movimentation movimentation = new DefaultMovimentation(scenario, inicio, fim);
        CostCalculator costCalculator = new DefaultCostCalculator(scenario, movimentation);

        Mutation mutation = new SinglePointMutation(semente, movimentation, 1.0f);

        Individual i = new Individual(movimentation, costCalculator);
        i.pushPosition(inicio);
        i.pushPosition(new Point(1,1));
        i.pushPosition(new Point(1,2));
        i.pushPosition(new Point(1,3));
        i.pushPosition(new Point(1,4));
        i.pushPosition(new Point(1,5));
        i.pushPosition(new Point(1,6));
        i.pushPosition(new Point(1,7));
        i.pushPosition(new Point(2,7));
        i.pushPosition(new Point(3,7));
        i.pushPosition(new Point(4,7));
        i.pushPosition(new Point(5,7));
        i.pushPosition(new Point(6,7));
        i.pushPosition(new Point(7,7));
        i.pushPosition(new Point(8,8));
        i.pushPosition(fim);

        Population population = new Population();
        population.pushIndividual(i);

        System.out.println(i.getCost());
        mutation.mutate(i);
        System.out.println(i.getCost());


    }
}

package org.example.guias.tools;

import org.example.cost.DefaultCostCalculator;
import org.example.geneticOperators.mutation.singlePointMutation.SinglePointMutation;
import org.example.movimentation.DefaultMovimentation;
import org.example.movimentation.Movimentation;
import org.example.scenario.Scenario;

import java.awt.Point;

public class BuildExamples {

    public static Scenario buildScanerio(){

        return new Scenario(10, 10);
    }

    public static DefaultMovimentation buildDefautMovimentation(Point start, Point end, Scenario scenario){

        return new DefaultMovimentation(scenario, start, end);

    }

    public static DefaultCostCalculator buildDefaultCostCalculator(Scenario scenario, Movimentation movimentation){

        return new DefaultCostCalculator(scenario, movimentation);
    }

    public static SinglePointMutation buildSinglePointMutation(Movimentation movimentation, double mutationProbability,
                                                               Long maxIterations){

        return null;
    }

}

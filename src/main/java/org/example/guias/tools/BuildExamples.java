package org.example.guias.tools;

import org.example.cost.DefaultCostCalculator;
import org.example.geneticOperators.crossover.crossoverRecombinacao.CrossoverEdgeRecombination;
import org.example.geneticOperators.mutation.singlePointMutation.SinglePointMutation;
import org.example.geneticOperators.selection.TournamentWithReplacement;
import org.example.movimentation.DefaultMovimentation;
import org.example.movimentation.Movimentation;
import org.example.scenario.Scenario;

import java.awt.Point;

public class BuildExamples {


    public static Scenario buildScanerio(int maxHeight, int maxLenght){

        Scenario scenario = new Scenario(10, 10);

        scenario.setCostInScenario(10, 100);

        return scenario;
    }

    public static DefaultMovimentation buildDefautMovimentation(Point start, Point end, Scenario scenario){

        return new DefaultMovimentation(scenario, start, end);

    }

    public static DefaultCostCalculator buildDefaultCostCalculator(Scenario scenario, Movimentation movimentation){

        return new DefaultCostCalculator(scenario, movimentation);
    }

    public static SinglePointMutation buildSinglePointMutation(Movimentation movimentation, double mutationProbability,
                                                               int maxIterations){

        return new SinglePointMutation(movimentation, mutationProbability, maxIterations);
    }

    public static CrossoverEdgeRecombination builCrossoverEdgeRecombination(Movimentation movimentation){

        return new CrossoverEdgeRecombination(movimentation);
    }

    public static TournamentWithReplacement buildTournamentWithReplacement(int tournamentSize){

        return new TournamentWithReplacement(tournamentSize);
    }

}

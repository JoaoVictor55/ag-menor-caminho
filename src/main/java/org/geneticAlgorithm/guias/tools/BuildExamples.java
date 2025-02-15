package org.geneticAlgorithm.guias.tools;

import org.geneticAlgorithm.cost.DefaultCostCalculator;
import org.geneticAlgorithm.geneticOperators.crossover.crossoverRecombination.CrossoverEdgeRecombination;
import org.geneticAlgorithm.geneticOperators.mutation.singlePointMutation.SinglePointMutation;
import org.geneticAlgorithm.geneticOperators.selection.TournamentWithReplacement;
import org.geneticAlgorithm.movimentation.DefaultMovimentation;
import org.geneticAlgorithm.movimentation.Movimentation;
import org.geneticAlgorithm.scenario.Scenario;

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

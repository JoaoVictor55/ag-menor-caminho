package org.geneticAlgorithm.geneticOperators.crossover;

import org.geneticAlgorithm.individual.Individual;
import org.geneticAlgorithm.geneticOperators.StochasticOperator;

import java.util.List;

public interface Crossover extends StochasticOperator {

    List<Individual> crossover(Individual pai, Individual mae);
}

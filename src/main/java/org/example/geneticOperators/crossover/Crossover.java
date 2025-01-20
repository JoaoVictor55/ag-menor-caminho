package org.example.geneticOperators.crossover;

import org.example.individual.Individual;
import org.example.geneticOperators.StochasticOperator;

import java.util.List;

public interface Crossover extends StochasticOperator {

    List<Individual> crossover(Individual pai, Individual mae);
}

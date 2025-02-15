package org.geneticAlgorithm.geneticOperators.mutation;

import org.geneticAlgorithm.individual.Individual;
import org.geneticAlgorithm.geneticOperators.StochasticOperator;

public interface Mutation extends StochasticOperator {

    void mutate(Individual individual);
    void setMutationRate(Double rate);
    Double getMutationRate();

}
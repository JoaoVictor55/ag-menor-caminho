package org.example.geneticOperators.mutation;

import org.example.individual.Individual;
import org.example.geneticOperators.StochasticOperator;

public interface Mutation extends StochasticOperator {

    void mutate(Individual individual);
    void setMutationRate(Double rate);
    Double getMutationRate();

}
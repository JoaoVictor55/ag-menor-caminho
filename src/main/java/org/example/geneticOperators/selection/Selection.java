package org.example.geneticOperators.selection;

import org.example.individual.Population;
import org.example.geneticOperators.StochasticOperator;

public interface Selection extends StochasticOperator {

     Population select(Population population, int number);
}


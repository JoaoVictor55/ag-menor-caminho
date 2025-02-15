package org.geneticAlgorithm.geneticOperators.selection;

import org.geneticAlgorithm.individual.Population;
import org.geneticAlgorithm.geneticOperators.StochasticOperator;

public interface Selection extends StochasticOperator {

     Population select(Population population, int number);
}


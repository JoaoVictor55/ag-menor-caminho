package org.geneticAlgorithm.geneticOperators.selection;

import org.geneticAlgorithm.individual.Population;

public abstract class SelectionBase implements Selection{

    public static double selectionIntensityCalculator(Population parents, Population offspring){

        double parentsAverage = parents.getAverage();
        double offspringAverage = offspring.getAverage();
        double parentsSd = parents.getSd();

        return (offspringAverage -  parentsAverage)/parentsSd;

    }
}

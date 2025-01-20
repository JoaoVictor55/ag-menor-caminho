package org.example.cost;


import org.example.individual.Individual;

import java.util.Comparator;

public interface CostCalculator extends Comparator<Individual> {

    Double calculateCost(Individual individual);

    int compare(Individual i1, Individual i2);
}

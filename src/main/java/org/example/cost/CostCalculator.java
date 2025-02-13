package org.example.cost;


import org.example.individual.Individual;

import java.util.Comparator;

/**
 * O custo de um caminho de um individuo
 * */
public interface CostCalculator extends Comparator<Individual> {

    Double calculateCost(Individual individual);


    int compare(Individual i1, Individual i2);
}

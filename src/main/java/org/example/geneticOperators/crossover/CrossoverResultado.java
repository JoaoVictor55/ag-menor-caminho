package org.example.geneticOperators.crossover;

import org.example.individual.Individual;

import java.util.List;


public record CrossoverResultado(
        Individual pai, Individual mae, List<Individual> filhos
) {
}

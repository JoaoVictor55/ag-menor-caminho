package org.geneticAlgorithm.geneticAlgorithm;

import org.geneticAlgorithm.individual.Individual;

import java.util.List;

public record OffspringGenerationSteps(Individual father, Individual mother, List<Individual> offspring) {
}

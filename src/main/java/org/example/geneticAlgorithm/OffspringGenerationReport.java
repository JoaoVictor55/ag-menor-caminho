package org.example.geneticAlgorithm;

import org.example.individual.Individual;

import java.util.List;

public record OffspringGenerationReport(Individual father, Individual mother, List<Individual> offspring) {
}

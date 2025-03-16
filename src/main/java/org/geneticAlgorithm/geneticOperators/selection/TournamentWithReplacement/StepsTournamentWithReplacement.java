package org.geneticAlgorithm.geneticOperators.selection.TournamentWithReplacement;

import org.geneticAlgorithm.individual.Individual;
import org.geneticAlgorithm.individual.Population;

import java.util.List;

public record StepsTournamentWithReplacement(
        Population original,
        Population selected,
        List<Individual[]> parentsMatched
) {
}

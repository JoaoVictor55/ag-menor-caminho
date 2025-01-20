package org.example.geneticAlgorithm;

import org.example.geneticOperators.StochasticOperator;
import org.example.geneticOperators.selection.TournamentWithReplacement;

public class GeneticAlgorithmManager implements StochasticOperator {


    private TournamentWithReplacement createTournamentSelection(){

        return new TournamentWithReplacement(100);

    }

    @Override
    public Long getSeed() {
        return 0l;
    }

    @Override
    public void setSeed(long seed) {

    }
}

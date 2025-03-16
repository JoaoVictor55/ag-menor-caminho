package org.geneticAlgorithm.geneticOperators.selection.TournamentWithReplacement;

import lombok.Getter;
import lombok.Setter;
import org.geneticAlgorithm.geneticOperators.OperatorWithSteps;
import org.geneticAlgorithm.geneticOperators.selection.SelectionBase;
import org.geneticAlgorithm.individual.Individual;
import org.geneticAlgorithm.individual.Population;

import java.security.SecureRandom;
import java.util.*;

public class TournamentWithReplacement extends SelectionBase implements OperatorWithSteps<StepsTournamentWithReplacement> {

    private final Random random = new SecureRandom();

    @Getter
    @Setter
    private int tournamentSize;

    @Getter
    private Long seed;

    private boolean recordSteps;

    private Population selected;
    private Population original;
    private List<Individual[]> matched;

    public TournamentWithReplacement(int tournamentSize){



        if(tournamentSize < 0){
            throw new IllegalArgumentException("O tamanho do torneio tem que ser positivo");
        }

        this.tournamentSize = tournamentSize;


    }

    public void setSeed(long seed){

        this.seed = seed;
        random.setSeed(this.seed);
    }


    @Override
    public Population select(Population population, int number) {

        Population selected = new Population();

        while(selected.size() < number){

            Optional<Individual> parents = Arrays.stream(getSample(population.toList(), this.tournamentSize)).min(Individual::compareTo);

            parents.ifPresent(selected::add);
        }

        if(isStepsRecordActive()){

            this.selected = selected;
        }
        return selected;
    }

    @Override
    public Individual[] selectParents(Population population) {

        Individual father = population.remove(random.nextInt(0, population.size()));
        Individual mother = population.remove(random.nextInt(0, population.size()));

        Individual[] buff = new Individual[]{father, mother};

        if(isStepsRecordActive()){

            this.matched.add(buff);
        }

        return buff;
    }

    private Individual[] getSample(List<Individual> population, int size){

        Individual[] sample = new Individual[size];

        for(int a = 0; a < size; ++a){

            int bff = this.random.nextInt(0, population.size());
            sample[a] = population.get(bff);

        }

        return sample;
    }

    @Override
    public void recordSteps(boolean activate) {

        this.recordSteps = activate;

        if(activate){

            this.matched = new ArrayList<>();
        }
    }

    @Override
    public boolean isStepsRecordActive() {
        return this.recordSteps;
    }

    @Override
    public StepsTournamentWithReplacement getSteps() {

        StepsTournamentWithReplacement stepsTournamentWithReplacement = new StepsTournamentWithReplacement(this.original, this.selected, this.matched);

        this.original = null;
        this.selected = null;
        this.matched = new ArrayList<>();

        return stepsTournamentWithReplacement;
    }
}

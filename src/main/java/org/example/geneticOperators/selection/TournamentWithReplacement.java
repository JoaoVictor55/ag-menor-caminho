package org.example.geneticOperators.selection;

import lombok.Getter;
import lombok.Setter;
import org.example.individual.Individual;
import org.example.individual.Population;

import java.security.SecureRandom;
import java.util.*;

public class TournamentWithReplacement extends SelectionBase {

    private final Random random = new SecureRandom();

    @Getter
    @Setter
    private int tournamentSize;

    @Getter
    private Long seed;

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

        return selected;
    }

    private Individual[] getSample(List<Individual> population, int size){

        Individual[] sample = new Individual[size];

        for(int a = 0; a < size; ++a){

            int bff = this.random.nextInt(0, population.size());
            sample[a] = population.get(bff);

        }

        return sample;
    }
}

package org.geneticAlgorithm.geneticAlgorithm;

import lombok.Getter;
import lombok.Setter;
import org.geneticAlgorithm.individual.Individual;
import org.geneticAlgorithm.individual.Population;
import org.geneticAlgorithm.geneticOperators.StochasticOperator;
import org.geneticAlgorithm.geneticOperators.crossover.Crossover;
import org.geneticAlgorithm.geneticOperators.selection.Selection;
import org.geneticAlgorithm.geneticOperators.mutation.Mutation;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GeneticAlgorithm implements StochasticOperator {

    private Selection selection;
    private Mutation mutation;
    private Crossover crossover;
    private Long seed;

    private SecureRandom random;

    @Setter
    @Getter
    private boolean makeOffspringReport;

    private List<OffspringGenerationSteps> offspringGenerationSteps;

    public GeneticAlgorithm(Selection selection, Mutation mutation, Crossover crossover){
        this.selection = selection;
        this.mutation = mutation;
        this.random = new SecureRandom();
        this.crossover = crossover;

    }



    public Population generatePopulation(Population parentsPopulation, int sizeGeneratedPopulation,
                                         int elitism){

        Population newPopulation = new Population();

        parentsPopulation.sort();

        for(int a = 0; a < elitism; ++a){

            newPopulation.add(parentsPopulation.get(a));
        }

        Population parents  = selection.select(parentsPopulation, 2*sizeGeneratedPopulation);

        while(newPopulation.size() < sizeGeneratedPopulation){

            //Individual father = parents.get(this.random.nextInt(0, parents.size()));
            //Individual mother = parents.get(this.random.nextInt(0, parents.size()));

            Individual [] parentsSelected = selection.selectParents(parents);

           List<Individual> offspring = crossover.crossover(parentsSelected[0], parentsSelected[1]);

            makeOffspringGenerationReport(parentsSelected[0], parentsSelected[1], offspring);

            for(Individual i : offspring){

                mutation.mutate(i);
            }

            newPopulation.addAll(offspring);

        }

        return newPopulation;
    }


    private void makeOffspringGenerationReport(Individual father, Individual mother, List<Individual> offsprings) {

        if(makeOffspringReport){

            Individual fatherCopy = new Individual(father.getMovimentation(), father.getCostCalculator(), father.getPath());
            Individual motherCopy = new Individual(mother.getMovimentation(), mother.getCostCalculator(), mother.getPath());
            List<Individual> offspringsCopy = new ArrayList<>();
            for(Individual offspring : offsprings){
                offspringsCopy.add(new Individual(offspring.getMovimentation(), offspring.getCostCalculator(),
                        offspring.getPath()));
            }

            offspringGenerationSteps.add(new OffspringGenerationSteps(fatherCopy, motherCopy, offspringsCopy));

        }
    }


    @Override
    public Long getSeed() {
        return seed;
    }

    @Override
    public void setSeed(long seed) {

        this.random.setSeed(seed);
        selection.setSeed(seed);
        mutation.setSeed(seed);
        crossover.setSeed(seed);
    }
}

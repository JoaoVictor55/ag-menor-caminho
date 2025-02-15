package org.example.geneticAlgorithm;

import lombok.Getter;
import lombok.Setter;
import org.example.individual.Individual;
import org.example.individual.Population;
import org.example.geneticOperators.StochasticOperator;
import org.example.geneticOperators.crossover.Crossover;
import org.example.geneticOperators.selection.Selection;
import org.example.geneticOperators.mutation.Mutation;

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

    public GeneticAlgorithm(Selection selection, Mutation mutation, Crossover crossover, Long seed){
        this.selection = selection;
        this.mutation = mutation;
        this.random = new SecureRandom();
        this.crossover = crossover;
        if(seed != null){
            this.setSeed(seed);
            this.seed = seed;

        }
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

            Individual father = parents.get(this.random.nextInt(0, parents.size()));
            Individual mother = parents.get(this.random.nextInt(0, parents.size()));
           List<Individual> offspring = crossover.crossover(father, mother);

            makeOffspringGenerationReport(father, mother, offspring);

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

    public List<OffspringGenerationSteps> getOffspringGenerationSteps(){

        if(!isMakeOffspringReport()) return null;

        List<OffspringGenerationSteps> offspringGenerationReportsCopy = this.offspringGenerationSteps;
        this.offspringGenerationSteps = new ArrayList<>();
        return offspringGenerationReportsCopy;
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

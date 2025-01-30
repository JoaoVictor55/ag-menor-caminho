package org.example.geneticAlgorithm;

import lombok.Getter;
import lombok.Setter;
import org.example.individual.Individual;
import org.example.individual.Population;
import org.example.geneticOperators.StochasticOperator;
import org.example.geneticOperators.crossover.Crossover;
import org.example.geneticOperators.selection.Selection;
import org.example.geneticOperators.mutation.Mutation;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GeneticAlgorithm implements StochasticOperator {

    private Selection selection;
    private Mutation mutation;
    private Crossover crossover;
    private Long seed;

    @Setter
    @Getter
    private boolean makeOffspringReport;

    private List<OffspringGenerationReport> offspringGenerationReports;

    public GeneticAlgorithm(Selection selection, Mutation mutation, Crossover crossover, Long seed){
        this.selection = selection;
        this.mutation = mutation;
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

            newPopulation.pushIndividual(parentsPopulation.obterIndividuos().get(a));
        }

        Population parents  = selection.select(parentsPopulation, 2*sizeGeneratedPopulation);

        while(newPopulation.getSize() < sizeGeneratedPopulation){

            Individual father = parents.getRandomIndividual();
            Individual mother = parents.getRandomIndividual();
           List<Individual> offspring = crossover.crossover(father, mother);

            makeOffspringGenerationReport(father, mother, offspring);

            for(Individual i : offspring){

                mutation.mutate(i);
            }

            newPopulation.addIndividual(offspring);

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

            offspringGenerationReports.add(new OffspringGenerationReport(fatherCopy, motherCopy, offspringsCopy));

        }
    }

    public List<OffspringGenerationReport> getOffspringGenerationReport(){

        if(!isMakeOffspringReport()) return null;

        List<OffspringGenerationReport> offspringGenerationReportsCopy = this.offspringGenerationReports;
        this.offspringGenerationReports = new ArrayList<>();
        return offspringGenerationReportsCopy;
    }

    @Override
    public Long getSeed() {
        return seed;
    }

    @Override
    public void setSeed(long seed) {

        selection.setSeed(seed);
        mutation.setSeed(seed);
        crossover.setSeed(seed);
    }
}

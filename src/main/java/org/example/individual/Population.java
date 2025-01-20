package org.example.individual;

import lombok.Getter;
import lombok.ToString;
import org.example.geneticOperators.StochasticOperator;

import java.util.*;

@ToString
public class Population implements StochasticOperator {

    @ToString.Exclude
    private final List<Individual> population;

    @ToString.Exclude
    private final Random random;

    @Getter
    private Long seed;

    private Double average;
    private Double sd;

    private Individual best;
    private Individual worst;

    private boolean reCalculateAverage = true;
    private boolean reCalculateSd = true;
    private boolean reCheckWorst = true;
    private boolean reCheckBest = true;

    public Population(Long seed){

        this(new ArrayList<>(), seed);
    }

    public Population(){
        this(new ArrayList<>(),null);
    }

    public Population(List<Individual> population, Long seed){

        this.population = population;
        random = new Random();

        if (seed != null)
            random.setSeed(seed);
    }

    public int getSize(){

        return this.population.size();
    }

    public void addIndividual(List<Individual> individuals){

        this.population.addAll(individuals);
        populationWasChanged();

    }

    public void pushIndividual(Individual i){

        population.add(i);


        if(best == null){
            best = i;
        }
        if(worst == null){
            worst = i;
        }

        if(best.compareTo(i) > 0){
            best = i;
        }
        else if(worst.compareTo(i) < 0){
            worst = i;
        }

        populationWasChanged();
        reCheckBest = false;
        reCheckWorst = false;


    }

    public Individual getRandomIndividual(){
        populationWasChanged();
        return this.population.get(random.nextInt(0, this.population.size()));
    }

    public void shuffle(){

        Collections.shuffle(population);
    }

    public Individual removeRandomIndividual(){
        populationWasChanged();
        return this.population.remove(random.nextInt(0, this.population.size()));
    }

    public Individual removeIndividualWhere(int where){
        populationWasChanged();
        return this.population.remove(where);
    }


    public void sort(){

        Collections.sort(population);
    }

    public Double getAverage(){

        if(reCalculateAverage){
            calculateAverage();
        }
        return average;
    }

    public Double getSd(){

        if(reCalculateSd){
            calculateSd();
        }

        return sd;
    }

    public Individual getBest(){

        if(reCheckBest){

            findBest();
        }

        return best;
    }

    public Individual getWorst(){

        if(reCheckWorst){
            findWorst();
        }

        return worst;
    }

    @Override
    public void setSeed(long seed){

        this.seed = seed;
        random.setSeed(seed);
    }


    public List<Individual> obterIndividuos(){

        populationWasChanged();
        return population;
    }


    private void findBest(){

        try{
            best = Collections.min(this.population);
        }catch (NoSuchElementException e){

            best = null;
        }

        reCheckBest = false;
    }

    private void populationWasChanged(){

        reCheckWorst = true;
        reCalculateAverage = true;
        reCheckBest = true;

    }

    private void calculateAverage(){

        average = 0.0;
        for(Individual i : population){

            if(i.getCost() != Double.POSITIVE_INFINITY){
                average += i.getCost();
            }
        }

        average /= population.size();
        reCalculateAverage = false;

    }

    private void calculateSd(){

        calculateAverage();
        double diferencas = 0.0;
        for(Individual i : population){
            if(i.getCost() != Double.POSITIVE_INFINITY)
                diferencas += ((i.getCost() - average) * (i.getCost() - average));
        }

        sd = diferencas/Math.sqrt(population.size() - 1);
        reCalculateSd = false;

    }

    private void findWorst(){

        try{

            worst = Collections.max(this.population);
        }catch (NoSuchElementException e){

            worst = null;
        }

        reCheckWorst = false;
    }

}

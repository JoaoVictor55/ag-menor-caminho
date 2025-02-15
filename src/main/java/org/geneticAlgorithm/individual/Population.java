package org.geneticAlgorithm.individual;

import lombok.ToString;

import java.util.*;

@ToString
public class Population implements  Iterable<Individual>{

    @ToString.Exclude
    private final List<Individual> population;

    private Double average;
    private Double sd;

    private Individual best;
    private Individual worst;

    private boolean reCalculateAverage = true;
    private boolean reCalculateSd = true;
    private boolean reCheckWorst = true;
    private boolean reCheckBest = true;


    public Population(){
        this(new ArrayList<>());
    }

    public Population(List<Individual> population){

        this.population = population;
    }

    public int size(){

        return this.population.size();
    }

    public void addAll(List<Individual> individuals){

        this.population.addAll(individuals);
        populationWasChanged();

    }

    public void add(Individual i){

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


    public Individual get(int where){
        populationWasChanged();
        if(where < 0){

            return this.population.get(this.size() + where + 1);
        }

        return this.population.get(where);

    }

    @Deprecated
    public void shuffle(){

        Collections.shuffle(population);
    }

    public Individual remove(int where){
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

    public List<Individual> toList(){

        populationWasChanged();
        return this.population;
    }

    public Iterator<Individual> iterator(){

        return this.population.iterator();
    }


    /*
    public List<Individual> getIndividuals(){

        populationWasChanged();
        return population;
    }*/


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

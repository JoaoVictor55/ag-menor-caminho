package org.example.geneticOperators.mutation.singlePointMutation;

import lombok.Getter;
import lombok.Setter;
import org.example.individual.Individual;
import org.example.movimentation.Movimentation;

import java.awt.*;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SinglePointMutation implements MutationWithReport {

    private final Random random = new SecureRandom();
    private final Movimentation movimentation;
    @Getter
    private Long seed;

    @Getter
    @Setter
    private int maxIterations;

    private ReportMutation reportMutation = null;
    private Boolean useReports = false;

    @Getter
    @Setter
    private double mutationProbability;

    public SinglePointMutation(Long seed, Movimentation movimentation, double mutationProbability
                         ){

        this(seed, movimentation, mutationProbability, 10000);
    }

    public SinglePointMutation(Long seed, Movimentation movimentation, double mutationProbability,
                               int maxIterations){

        this.movimentation = movimentation;

        if(seed != null){
            this.random.setSeed(seed);
        }

        this.seed = seed;
        this.mutationProbability = mutationProbability;
        this.maxIterations = maxIterations;
    }


    @Override
    public void setSeed(long seed){

        this.seed = seed;
        random.setSeed(seed);
    }


    private boolean doMutation(Individual individual, int where){

        Point position = individual.getPosition(where);
        List<Point> possibilities = this.movimentation.possiblePaths(position);
        Point newPoint = possibilities.get(random.nextInt(possibilities.size()));

        boolean success;
        while(true){

            if (movimentation.isPossible(individual.getPosition(where-1), newPoint) && movimentation.isPossible(newPoint, individual.getPosition(where+1))){

                Point oldPoint = new Point(individual.getPosition(where));

                writeReport(oldPoint, newPoint, where);

                individual.getPosition(where).x = newPoint.x;
                individual.getPosition(where).y = newPoint.y;
                success = true;

                break;
            }

            possibilities.remove(newPoint);

            if(possibilities.isEmpty()){
                success = false;
                break;
            }

            newPoint = possibilities.get(random.nextInt(0,possibilities.size()));
        }

        return success;
    }

    @Override
    public void mutate(Individual individual) {

        if(random.nextFloat() < mutationProbability){

            int where = random.nextInt(1, individual.getSize()-1);
            Set<Integer> numbersAlreadyDrawn = new HashSet<>();
            numbersAlreadyDrawn.add(where);
            while(!doMutation(individual,where)){

                --maxIterations;
                if (maxIterations <= 0){
                    break;
                }
                while(numbersAlreadyDrawn.contains(where)){
                    --maxIterations;
                    where = random.nextInt(1, individual.getSize()-1);
                    if(maxIterations <= 0){
                        break;
                    }
                }
                numbersAlreadyDrawn.add(where);
            }

        }

    }

    @Override
        public void setMutationRate(Double rate) {
        this.mutationProbability = rate;
    }

    @Override
    public Double getMutationRate() {
        return this.mutationProbability;
    }

    private void writeReport(Point substituted, Point substitute, int where){

        if(this.useReports){
            if (substituted != null){

                if(substitute != null){
                    this.reportMutation = new ReportMutation(where, substituted, substitute,
                            Type.REPLACED);
                }
                else{

                    this.reportMutation = new ReportMutation(where, substituted, null,
                            Type.REMOVED);
                }
            }
            else{
                this.reportMutation = new ReportMutation(where, null, null,
                        Type.NO_MUTATION);
            }

        }

    }

    @Override
    public boolean isReportActive(){

        return this.useReports;
    }

    @Override
    public void activateReport(boolean activate) {

        this.useReports = activate;
    }

    @Override
    public ReportMutation getResult() {

        return reportMutation;
    }
}

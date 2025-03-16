package org.geneticAlgorithm.individual;

import lombok.Getter;
import lombok.Setter;
import org.geneticAlgorithm.cost.CostCalculator;
import org.geneticAlgorithm.movimentation.Movimentation;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma solução individual
 * */
public class Individual implements Comparable<Individual> {

    @Setter
    @Getter
    private Movimentation movimentation;

    @Getter
    private final CostCalculator costCalculator;

    private final List<Point> path;

    private Double cost;

    private boolean reCalculateCost;
    private Boolean isValid = null;
    private boolean reCheckValidity;

    public Individual(Movimentation movimentation, CostCalculator costCalculator){
        this(movimentation, costCalculator, new ArrayList<>());
    }

    public Individual(Movimentation movimentation, CostCalculator costCalculator, List<Point> path){

        this.costCalculator = costCalculator;
        this.movimentation = movimentation;
        this.path = new ArrayList<>(path);

        this.reCheckValidity = true;
        this.reCalculateCost = true;
    }

    public void addPositionAt(int where, Point position){

        pathWasChanged();

        if(where >= 0)
            this.path.add(where, position);
        else{
            this.path.add(this.path.size() + where+1, position);
        }
    }

    public List<Point> getPath(){

        List<Point> copyOfPath = new ArrayList<>();

        for(Point p : this.path){

            copyOfPath.add(new Point(p.x, p.y));
        }

        return copyOfPath;
    }

    public void add(Point position){
        pathWasChanged();
        this.path.add(position);
    }

    public Point remove(int where){
        pathWasChanged();
        if(where >= 0)
            return this.path.remove(where);
        else
            return this.path.remove(this.path.size() + where);

    }

    public Point getPosition(int where){
        pathWasChanged();
        if(where >= 0)
            return this.path.get(where);
        else {
            return this.path.get(path.size() + where);
        }
    }

    private void setCost(){

        this.cost = costCalculator.calculateCost(this.path);
        this.reCalculateCost = false;
    }

    public Double getCost(){

        if(this.reCalculateCost){
            setCost();
        }
        return this.cost;
    }

    public int size(){

        return this.path.size();
    }

    public boolean isValid(){

        if(this.reCheckValidity){

            isValid = this.movimentation.isValid(path);
            reCheckValidity = false;
        }

        return isValid;
    }


    @Override
    public int compareTo(Individual o) {

        return costCalculator.compare(this.path, o.getPath());
    }

    /*
    @Override
    public String toString(){

        StringBuilder inString = new StringBuilder();

        for(Point p : this.path){

            inString.append(String.format("[%f, %f] ", p.getY(), p.getX()));
        }

        inString.append("custo: ");
        inString.append(getCost());
        inString.append(" é solução: ");
        inString.append(isValid());

        return inString.toString();
    }*/



    private void pathWasChanged(){


        this.reCalculateCost = true;
        this.reCheckValidity = true;

    }


}

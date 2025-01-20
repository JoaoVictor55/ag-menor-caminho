package org.example.individual;

import lombok.Getter;
import lombok.Setter;
import org.example.cost.CostCalculator;
import org.example.movimentation.Movimentation;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Individual implements Comparable<Individual> {

    @Setter
    @Getter
    private Movimentation movimentation;

    @Getter
    private final CostCalculator costCalculator;

    @Getter
    private final List<Point> path;

    private Double cost;

    private boolean reCalculateCost = true;
    private boolean reCheckCost = true;


    private Boolean isValid = null;

    public Individual(Movimentation movimentation, CostCalculator costCalculator){
        this(movimentation, costCalculator, new ArrayList<>());
    }

    public Individual(Movimentation movimentation, CostCalculator costCalculator, List<Point> path){

        this.costCalculator = costCalculator;
        this.movimentation = movimentation;
        this.path = new ArrayList<>(path);
    }

    public void addPositionAt(int where, Point position){

        pathWasChanged();

        if(where >= 0)
            this.path.add(where, position);
        else{
            this.path.add(this.path.size() + where, position);
        }
    }

    public void pushPosition(Point posicao){
        pathWasChanged();
        this.path.add(posicao);
    }

    public Point removePosition(int where){
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

        this.cost = costCalculator.calculateCost(this);
        reCalculateCost = false;
    }

    public Double getCost(){

        if(reCalculateCost){
            setCost();
        }
        return this.cost;
    }

    public int getSize(){

        return this.path.size();
    }

    public boolean isValid(){

        if(reCheckCost){

            isValid = this.movimentation.isValid(path);
            reCheckCost = false;
        }

        return isValid;
    }


    @Override
    public int compareTo(Individual o) {

        return costCalculator.compare(this, o);
    }

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
    }


    private void pathWasChanged(){


        this.reCalculateCost = true;
        this.reCheckCost = true;

    }


}

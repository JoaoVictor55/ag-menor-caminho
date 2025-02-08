package org.example.cost;

import lombok.NonNull;
import org.example.scenario.Scenario;
import org.example.individual.Individual;
import org.example.movimentation.Movimentation;

import java.awt.*;
import java.util.List;

public class DefaultCostCalculator implements CostCalculator {

    private final Scenario scenario;
    private final Movimentation movimentation;


    public DefaultCostCalculator(@NonNull Scenario scenario, @NonNull Movimentation movimentation){

        this.scenario = scenario;
        this.movimentation = movimentation;
    }

    @Override
    public Double calculateCost(Individual individual) {

        List<Point> path = individual.getPath();

        if(path.isEmpty()) return 0.0;

        Point startPoint = path.get(0);
        double total = scenario.getCost(startPoint);

        for(Point p : path.subList(1, path.size())){

            if(movimentation.isPossible(startPoint, p)){
                total += scenario.getCost(p);
            }
            else return Double.POSITIVE_INFINITY;
            startPoint = p;
        }
        return total;
        //return caminho.stream().reduce(0.0, (p1, p2)-> p1 + cenario.obterCusto(p2),Double::sum);
    }

    @Override
    public int compare(Individual i1, Individual i2) {

        return Double.compare(this.calculateCost(i1), this.calculateCost(i2 ));
    }

}

package org.example.movimentation;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.example.scenario.Scenario;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class DefaultMovimentation implements Movimentation {

    @ToString.Exclude
    private static final Double twoSqrt = Math.sqrt(2);

    private final Scenario scenario;

    private final Point startPoint;
    private final Point endPoint;
    public DefaultMovimentation(@NonNull Scenario scenario,
                                @NonNull Point startPoint,
                                @NonNull Point endPoint){

        this.scenario = scenario;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public boolean isPossible(Point from, Point to) {

        Double distancia = from.distance(to);
        return !(scenario.isOutsideBoundaries(from) || scenario.isOutsideBoundaries(to)) && (distancia == 1 || distancia.equals(twoSqrt));
    }

    @Override
    public List<Point> possiblePaths(Point from) {
        List<Point> possibilities = new ArrayList<>();

        Point p = new Point(from);
        p.translate(1, 0);
        if(scenario.isUnblocked(p))
            possibilities.add(p);

        p = new Point(from);
        p.translate(0, 1);
        if(scenario.isUnblocked(p))
            possibilities.add(p);

        p = new Point(from);
        p.translate(1,1);
        if(scenario.isUnblocked(p))
            possibilities.add(p);

        p = new Point(from);
        p.translate(-1,0);
        if(scenario.isUnblocked(p))
            possibilities.add(p);

        p = new Point(from);
        p.translate(0,-1);
        if(scenario.isUnblocked(p))
            possibilities.add(p);

        p = new Point(from);
        p.translate(-1,-1);
        if(scenario.isUnblocked(p))
            possibilities.add(p);

        p = new Point(from);
        p.translate(-1,1);
        if(scenario.isUnblocked(p))
            possibilities.add(p);

        p = new Point(from);
        p.translate(1,-1);
        if(scenario.isUnblocked(p))
            possibilities.add(p);

        return possibilities;
    }

    @Override
    public boolean isValid(List<Point> path) {

        Point currentPoint = path.get(0);

        if (!currentPoint.equals(this.startPoint) && !path.get(path.size()-1).equals(this.endPoint)){

            return false;
        }

        for (Point p : path.subList(1, path.size())) {

            if (!isPossible(currentPoint, p)){
                return  false;
            }
            currentPoint = p;
        }

        return true;
    }



}

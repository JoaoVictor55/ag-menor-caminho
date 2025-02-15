package org.geneticAlgorithm.geneticOperators.crossover.crossoverRecombination;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public record StepsEdgeRecombination(
        ArrayList<String> reasons,
        ArrayList<Point> chosen,
        ArrayList<Point> adjacentTo,
        ArrayList<List<Point>> adjacent
) {
}

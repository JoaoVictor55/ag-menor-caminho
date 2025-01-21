package org.example.geneticOperators.crossover.crossoverRecombinacao;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public record ReportEdgeRecombination(
        ArrayList<String> reasons,
        ArrayList<Point> chosen,
        ArrayList<Point> adjacentTo,
        ArrayList<List<Point>> adjacent
) {
}

package org.geneticAlgorithm.cost;


import org.geneticAlgorithm.individual.Individual;

import java.awt.Point;
import java.util.Comparator;
import java.util.List;

/**
 * O custo de um caminho de um individuo
 * */
public interface CostCalculator extends Comparator<List<Point>> {

    Double calculateCost(List<Point> path);


    int compare(List<Point> path1, List<Point> path2);
}

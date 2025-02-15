package org.geneticAlgorithm.guias;

import org.geneticAlgorithm.cost.DefaultCostCalculator;
import org.geneticAlgorithm.geneticOperators.crossover.crossoverRecombination.CrossoverEdgeRecombination;
import org.geneticAlgorithm.guias.tools.BuildExamples;
import org.geneticAlgorithm.individual.Individual;
import org.geneticAlgorithm.movimentation.Movimentation;
import org.geneticAlgorithm.scenario.Scenario;

import java.awt.Point;
import java.util.List;

public class GuideEdgeRecombinationCrossover {


    private static Point startPoint = new Point(0, 0);
    private static Point endPoint = new Point(9,9);
    private static Scenario scenario = BuildExamples.buildScanerio(10, 10);
    private static Movimentation movimentation = BuildExamples.buildDefautMovimentation(startPoint, endPoint, scenario);
    private static DefaultCostCalculator costCalculator = BuildExamples.buildDefaultCostCalculator(scenario, movimentation);


    public static void main(String [] args){

        //edge recombination (recombinação de aresta) combina dois caminhos para formar um
        CrossoverEdgeRecombination crossoverEdgeRecombination = new CrossoverEdgeRecombination(movimentation);

        //gerando dois pais:
        Individual father = new Individual(movimentation, costCalculator);
        father.add(startPoint);
        father.add(new Point(1,0));
        father.add(new Point(2,0));
        father.add(new Point(3,0));
        father.add(new Point(4,0));
        father.add(new Point(4,1));
        father.add(new Point(4,2));
        father.add(new Point(4,3));
        father.add(new Point(4,4));
        father.add(new Point(5,5));
        father.add(new Point(6,6));
        father.add(new Point(7,7));
        father.add(new Point(8,7));
        father.add(new Point(8,8));
        father.add(endPoint);

        Individual mother = new Individual(movimentation, costCalculator);
        mother.add(startPoint);
        mother.add(new Point(1,1));
        mother.add(new Point(2,1));
        mother.add(new Point(3,1));
        mother.add(new Point(4,1));
        mother.add(new Point(4,2));
        mother.add(new Point(4,3));
        mother.add(new Point(4,4));
        mother.add(new Point(4,5));
        mother.add(new Point(5,5));
        mother.add(new Point(6,5));
        mother.add(new Point(7,5));
        mother.add(new Point(8,5));
        mother.add(new Point(8,6));
        mother.add(new Point(8,7));
        mother.add(new Point(8,8));
        mother.add(endPoint);

        //esse crossover gera apenas um filho
        List<Individual> offspring = crossoverEdgeRecombination.crossover(father, mother);

        System.out.printf("Custo do pai: %f\nPai:",father.getCost());
        for(int a = 0; a < father.size(); ++a){
            System.out.println(father.getPosition(a));
        }

        System.out.printf("Custo do mãe: %f\nMãe:",mother.getCost());
        for(int a = 0; a < mother.size(); ++a){
            System.out.println(mother.getPosition(a));
        }

        System.out.printf("Custo do filho: %f\nFilho:",offspring.get(0).getCost());
        for(int a = 0; a < offspring.get(0).size(); ++a){
            System.out.println(offspring.get(0).getPosition(a));
        }
    }
}


package org.geneticAlgorithm.guias.guidesWithSteps;

import org.geneticAlgorithm.cost.DefaultCostCalculator;
import org.geneticAlgorithm.geneticOperators.crossover.crossoverRecombination.CrossoverEdgeRecombination;
import org.geneticAlgorithm.geneticOperators.crossover.crossoverRecombination.StepsEdgeRecombination;
import org.geneticAlgorithm.guias.tools.BuildExamples;
import org.geneticAlgorithm.individual.Individual;
import org.geneticAlgorithm.movimentation.Movimentation;
import org.geneticAlgorithm.scenario.Scenario;

import java.awt.*;
import java.util.List;

public class GuideEdgeRecombinationCrossoverWithSteps {

    private static Point startPoint = new Point(0, 0);
    private static Point endPoint = new Point(9,9);
    private static Scenario scenario = BuildExamples.buildScanerio(10, 10);
    private static Movimentation movimentation = BuildExamples.buildDefautMovimentation(startPoint, endPoint, scenario);
    private static DefaultCostCalculator costCalculator = BuildExamples.buildDefaultCostCalculator(scenario, movimentation);


    public static void main(String [] args){

        //edge recombination (recombinação de aresta) combina dois caminhos para formar um
        CrossoverEdgeRecombination crossoverEdgeRecombination = new CrossoverEdgeRecombination(movimentation);

        //ativamos o registro do passo a passo
        //cada vez que ativamos o registro, o anterior é apagado
        crossoverEdgeRecombination.recordSteps(true);

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

        List<Individual> offspring = crossoverEdgeRecombination.crossover(father, mother);

        //o registro é apagado após ser coletado; ele não se acumula
        StepsEdgeRecombination stepsEdgeRecombination = crossoverEdgeRecombination.getSteps();

        //para um filho é registrado qual ponto foi escolhido, qual a razão e quais as possibilidades
        //consulte como funciona esse crossover para mais detalhes

        for(int a = 0; a < stepsEdgeRecombination.chosen().size(); ++a){

            System.out.println("Escolhido: "+stepsEdgeRecombination.chosen().get(a)); //qual o ponto escolhido para integrar o filho
            System.out.println("Razão: "+stepsEdgeRecombination.reasons().get(a)); //qual a razão da escolha (nó comum, unica opção...)
            System.out.println("Adjacente de: "+stepsEdgeRecombination.adjacentTo().get(a)); //de quem ele é adjacente
        }

        //o record é apagado após ler obtido:
        StepsEdgeRecombination edgeRecombinationComposer = crossoverEdgeRecombination.getSteps();

        System.out.println(edgeRecombinationComposer.chosen().size());
    }
}

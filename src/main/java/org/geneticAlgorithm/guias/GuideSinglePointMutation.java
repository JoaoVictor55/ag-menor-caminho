package org.geneticAlgorithm.guias;

import org.geneticAlgorithm.cost.DefaultCostCalculator;
import org.geneticAlgorithm.geneticOperators.mutation.singlePointMutation.SinglePointMutation;
import org.geneticAlgorithm.guias.tools.BuildExamples;
import org.geneticAlgorithm.individual.Individual;
import org.geneticAlgorithm.movimentation.Movimentation;
import org.geneticAlgorithm.scenario.Scenario;
import org.geneticAlgorithm.uteis.GerarAleatorios;

import java.awt.*;

public class GuideSinglePointMutation {

    private static Point startPoint = new Point(0, 0);
    private static Point endPoint = new Point(9,9);
    private static Scenario scenario = BuildExamples.buildScanerio(10, 10);
    private static Movimentation movimentation = BuildExamples.buildDefautMovimentation(startPoint, endPoint, scenario);
    private static DefaultCostCalculator costCalculator = BuildExamples.buildDefaultCostCalculator(scenario, movimentation);
    private static GerarAleatorios gerarAleatorios = new GerarAleatorios(costCalculator, movimentation);

    public static void main(String [] args){


        //definimos a mutação e a probabilidade dela ocorrer [0, 1]
        //a mutação seleciona um ponto aleatório no caminho e o troca por seu vizio.
        //Por exemplo: seleciona o ponto (1,2) e o troca por (2,3). Quem define esse ponto é o movimentation
        SinglePointMutation singlePointMutation = BuildExamples.buildSinglePointMutation(movimentation, 1, 10000);

        //gerando indivíduo para o exemplo:
        Individual toMutate = new Individual(movimentation, costCalculator);

        toMutate.add(startPoint);
        toMutate.add(new Point(1,0));
        toMutate.add(new Point(2,0));
        toMutate.add(new Point(3,0));
        toMutate.add(new Point(4,0));
        toMutate.add(new Point(4,1));
        toMutate.add(new Point(4,2));
        toMutate.add(new Point(4,3));
        toMutate.add(new Point(4,4));
        toMutate.add(new Point(5,5));
        toMutate.add(new Point(6,6));
        toMutate.add(new Point(7,7));
        toMutate.add(new Point(8,7));
        toMutate.add(new Point(8,8));
        toMutate.add(endPoint);

        System.out.println("Antes da mutação: ");
        System.out.printf("Custo: %f\nCaminho:", toMutate.getCost());
        for (int a = 0; a < toMutate.size(); ++a){

            System.out.println(toMutate.getPosition(a));
        }

        singlePointMutation.mutate(toMutate);
        System.out.println("Após da mutação: ");
        System.out.printf("Custo: %f\nCaminho:", toMutate.getCost());
        for (int a = 0; a < toMutate.size(); ++a){

            System.out.println(toMutate.getPosition(a));
        }



    }
}

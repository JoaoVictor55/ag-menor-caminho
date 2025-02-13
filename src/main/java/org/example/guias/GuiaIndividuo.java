package org.example.guias;

import org.example.cost.DefaultCostCalculator;
import org.example.individual.Individual;
import org.example.movimentation.DefaultMovimentation;
import org.example.scenario.Scenario;

import java.awt.*;
import java.util.Arrays;

public class GuiaIndividuo {

    public static void main(String [] args){

        Scenario scenario = new Scenario(10, 10, 30l); //definindo o cenário

        //definindo os pontos iniciais e finais do cenário
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(9,9);

        scenario.setCostInScenario(10, 300);
        scenario.putBlockingExceptIn(0.01f,Arrays.asList(startPoint, endPoint));

        //construindo a movimentação
        DefaultMovimentation defaultMovimentation = new DefaultMovimentation(scenario, startPoint, endPoint);

        DefaultCostCalculator defaultCostCalculator = new DefaultCostCalculator(defaultMovimentation.getScenario(),
                defaultMovimentation);

        //definindo o indivíduo
        Individual individual = new Individual(defaultMovimentation, defaultCostCalculator);

        //adicionando posições ao caminho do indivíduo
        individual.add(startPoint);
        individual.add(new Point(1,1));
        individual.add(new Point(2,2));
        individual.add(new Point(3,3));
        individual.add(new Point(4,4));
        individual.add(new Point(5,5));
        individual.add(new Point(6,6));
        individual.add(new Point(7,7));
        individual.add(new Point(8,8));
        individual.add(endPoint);

        //obtendo o custo do indivíduo:
        System.out.println(individual.getCost());

        //comparando indivíduos:
        Individual individual2 = new Individual(defaultMovimentation, defaultCostCalculator);

        //adicionando posições ao caminho do indivíduo
        individual2.add(startPoint);
        individual2.add(new Point(1,1));
        individual2.add(new Point(2,2));
        individual2.add(new Point(3,3));
        individual2.add(new Point(4,4));
        individual2.add(new Point(5,5));
        individual2.add(new Point(6,6));
        individual2.add(new Point(7,7));
        individual2.add(new Point(7,8));
        individual2.add(new Point(8,8));
        individual2.add(endPoint);

        //printando individuo
        //   System.out.println(individual);

        System.out.println(individual.compareTo(individual2));

        //printando os dados:
        System.out.println(individual.getPath());
        //verificando validade do indivíduo (de acordo com a movimentation):
        System.out.println(individual.isValid());
        System.out.println(individual.getCost());

        //removendo ponto do indivíduo:
        Point removido = individual.remove(-1);
        System.out.println(removido);
        //verificando a validade do indivíduo após a remoção
        System.out.println(individual.isValid());

        //adicionado ponto a uma posição do indivíduo:
        individual.addPositionAt(-1, removido);

        //printando os dados:
        System.out.println(individual.getPath());
        System.out.println(individual.isValid());
        System.out.println(individual.getCost());

    }
}


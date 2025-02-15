package org.geneticAlgorithm.guias;

import org.geneticAlgorithm.cost.DefaultCostCalculator;
import org.geneticAlgorithm.guias.tools.BuildExamples;
import org.geneticAlgorithm.individual.Individual;
import org.geneticAlgorithm.individual.Population;
import org.geneticAlgorithm.movimentation.DefaultMovimentation;
import org.geneticAlgorithm.scenario.Scenario;
import org.geneticAlgorithm.uteis.GerarAleatorios;

import java.util.Iterator;
import java.util.List;
import java.awt.*;

public class GuidePopulation {

    private static Scenario scenario = BuildExamples.buildScanerio(10, 10);
    private static Point startPoint = new Point(0, 0);
    private static Point endPoint = new Point(9,9);
    private static DefaultMovimentation defaultMovimentation = BuildExamples.buildDefautMovimentation(startPoint, endPoint, scenario);
    private static DefaultCostCalculator defaultCostCalculator = BuildExamples.buildDefaultCostCalculator(defaultMovimentation.getScenario(),
            defaultMovimentation);

    private static GerarAleatorios gerarAleatorios = new GerarAleatorios(defaultCostCalculator, defaultMovimentation,
            10l);

    public static void main(String [] args){

        scenario.setCostInScenario(100, 999);

        //podemos definir a seed
        Population population = new Population();

        List<Individual> individuos = gerarAleatorios.gerarAleatorios(startPoint, endPoint,10, 1000);

        //adicionando uma lista de invididuos:
        population.addAll(individuos);

        //printando os custos dos indivíduos:
        System.out.println("Custos da população");
//        population.iterator().forEach(i -> System.out.println(i.getCost()));
        Iterator<Individual> iterator = population.iterator();
        while(iterator.hasNext()){

            System.out.println(iterator.next().getCost());
        }

        //obtendo estatísticas da população:
        System.out.println("média: "+population.getAverage());
        System.out.println("desvio padrão: "+population.getSd());
        System.out.println("custo do melhor: "+population.getBest().getCost());
        System.out.println("custo do pior: "+population.getWorst().getCost());

        //ordenando a população
        population.sort();
        System.out.println("Custos da população (ordenado)");
        iterator = population.iterator();
        while(iterator.hasNext()){

            System.out.println(iterator.next().getCost());
        }

        //Removendo um indivíduo
        Individual removido = population.remove(1);
        System.out.println("removido: "+removido.getCost());


    }
}

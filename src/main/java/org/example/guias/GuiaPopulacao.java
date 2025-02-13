package org.example.guias;

import org.example.cost.DefaultCostCalculator;
import org.example.individual.Individual;
import org.example.individual.Population;
import org.example.movimentation.DefaultMovimentation;
import org.example.scenario.Scenario;
import org.example.uteis.GerarAleatorios;

import java.util.Iterator;
import java.util.List;
import java.awt.*;

public class GuiaPopulacao {

    private static Scenario scenario = new Scenario(10, 10, 30l);
    private static Point startPoint = new Point(0, 0);
    private static Point endPoint = new Point(9,9);
    private static DefaultMovimentation defaultMovimentation = new DefaultMovimentation(scenario, startPoint, endPoint);
    private static DefaultCostCalculator defaultCostCalculator = new DefaultCostCalculator(defaultMovimentation.getScenario(),
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

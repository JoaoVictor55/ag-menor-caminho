package org.example.testes;

import org.example.scenario.Scenario;
import org.example.cost.CostCalculator;
import org.example.cost.DefaultCostCalculator;
import org.example.individual.Individual;
import org.example.movimentation.Movimentation;
import org.example.movimentation.DefaultMovimentation;
import org.example.uteis.GerarAleatorios;

import java.awt.*;

public class TesteGeradorAleatorio {

    public static void main(String [] args){
        long semente = (long)0;

        Scenario scenario = new Scenario(15, 10, semente);

        scenario.setCostInScenario(100, 950);
        Point inicio =  new Point(0, 0);
        Point fim = new Point(14, 9);
        Movimentation movimentation = new DefaultMovimentation(scenario,inicio, fim);
        CostCalculator costCalculator = new DefaultCostCalculator(scenario, movimentation);

        scenario.putBlocking(0.5f);

        System.out.println(scenario.getCost(fim));


        for(int x = 0; x < scenario.getMaxHeight(); ++x){
            for(int y = 0; y < scenario.getMaxLength(); ++y){
                System.out.print(scenario.getCost(new Point(x, y))+" ");
            }
            System.out.println();
        }


       GerarAleatorios gerarAleatorios = new GerarAleatorios(costCalculator, movimentation, semente);

        java.util.List<Individual> individualList = gerarAleatorios.gerarAleatorios(inicio, fim, 1, 10000);



        int t = 0;


    }
}

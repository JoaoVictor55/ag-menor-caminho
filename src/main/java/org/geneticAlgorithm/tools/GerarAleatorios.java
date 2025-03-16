package org.geneticAlgorithm.tools;

import lombok.Getter;
import org.geneticAlgorithm.cost.CostCalculator;
import org.geneticAlgorithm.individual.Individual;
import org.geneticAlgorithm.movimentation.Movimentation;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerarAleatorios {

    @Getter
    private CostCalculator costCalculator;
    @Getter
    private Movimentation movimentation;
    private Random random;

    @Getter
    private Long semente;

    public GerarAleatorios(CostCalculator costCalculator, Movimentation movimentation, Long semente){

        this.costCalculator = costCalculator;
        this.movimentation = movimentation;
        this.random = new SecureRandom();
        this.semente = semente;

        if(semente != null)
            random.setSeed(semente);
    }

    public GerarAleatorios(CostCalculator costCalculator, Movimentation movimentation){
        this(costCalculator, movimentation, null);
    }

    public List<Individual> gerarAleatorios(Point inicio, Point fim, int quantidade, long maximoInteracoes){

        List<Individual> gerados = new ArrayList<>();
        for(int a = 0; a < quantidade; ++a){

            Individual individual = new Individual(movimentation, costCalculator);
            individual.add(inicio);

            List<Point> posiveis = movimentation.possiblePaths(inicio);

            Point posivel = posiveis.get(random.nextInt(0, posiveis.size()));

            individual.add(posivel);
            gerados.add(individual);

            for(long iteracoes = 0;!posivel.equals(fim) && iteracoes <= maximoInteracoes; ++iteracoes){

                posiveis = movimentation.possiblePaths(posivel);
                posivel = posiveis.get(random.nextInt(0, posiveis.size()));

                individual.add(posivel);

            }


        }

        return gerados;
    }
}

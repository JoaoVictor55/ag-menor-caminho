package org.example.uteis;

import lombok.Getter;
import org.example.cost.CostCalculator;
import org.example.individual.Individual;
import org.example.movimentation.Movimentation;

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
            individual.pushPosition(inicio);

            List<Point> posiveis = movimentation.possiblePaths(inicio);

            Point posivel = posiveis.get(random.nextInt(0, posiveis.size()));

            individual.pushPosition(posivel);
            gerados.add(individual);

            for(long iteracoes = 0;!posivel.equals(fim) && iteracoes <= maximoInteracoes; ++iteracoes){

                posiveis = movimentation.possiblePaths(posivel);
                posivel = posiveis.get(random.nextInt(0, posiveis.size()));

                individual.pushPosition(posivel);

            }


        }

        return gerados;
    }
}

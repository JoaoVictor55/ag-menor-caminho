package org.geneticAlgorithm.guias;

import org.geneticAlgorithm.scenario.Scenario;

import java.awt.*;
import java.util.Arrays;

public class GuideScenario {

    public static void main(String []args){

        //defiimos a altura máxma, largura máxima e a semente
        Scenario cenario = new Scenario(10, 10, 10l);

        //definindo o custo no cenário: definimos um custo mínimo e o máximo
        //os valores são definimos aleatoriamente
        cenario.setCostInScenario(10, 300);

        //colocando bloqueios de acordo com uma probabilidade:
        cenario.putBlocking(0.3f); //30% de chance

        //colocando bloqueios de acordo com uma probabilidade exceto nos pontos indicados
        cenario.putBlockingExceptIn(0.3f, Arrays.asList(new Point(0,0), new Point(9, 9)));

        //Obtendo os pontos do cenário:
        for(int a = 0; a < cenario.getMaxHeight(); ++a){
            for(int b = 0; b < cenario.getMaxLength();++b){
                System.out.printf("%f ",cenario.getCost(a, b));
            }
            System.out.print("\n");
        }


    }
}

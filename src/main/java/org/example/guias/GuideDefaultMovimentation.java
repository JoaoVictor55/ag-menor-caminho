package org.example.guias;

import org.example.movimentation.DefaultMovimentation;
import org.example.scenario.Scenario;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class GuideDefaultMovimentation {

    public static void main(String [] args){

        Scenario scenario = new Scenario(10, 10); //definindo o cenário

        //definindo os pontos iniciais e finais do cenário
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(9,9);

        DefaultMovimentation defaultMovimentation = new DefaultMovimentation(scenario, startPoint, endPoint);

        //checando os caminhos possíveis a partir de um ponto:
        defaultMovimentation.possiblePaths(startPoint).forEach(System.out::println);

        //checando se é válido ir de um ponto ao outro:
        System.out.println(defaultMovimentation.isPossible(startPoint, endPoint));

        //checando a validade de um caminho:
        List<Point> caminho = Arrays.asList(startPoint, new Point(1,1),
                new Point(2,2), new Point(3,3), new Point(4,4),
                new Point(5,5), new Point(6,6), new Point(7,7),
                new Point(8,8), endPoint);

        System.out.println(defaultMovimentation.isValid(caminho));
    }
}

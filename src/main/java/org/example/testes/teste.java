package org.example.testes;

import org.example.movimentation.DefaultMovimentation;
import org.example.movimentation.Movimentation;
import org.example.scenario.Scenario;

public class teste {

    public static void main(String [] args){

        Scenario scenario = new Scenario(10, 10 );

        Object o = scenario;

        Scenario s =(Scenario) o;

    }
}

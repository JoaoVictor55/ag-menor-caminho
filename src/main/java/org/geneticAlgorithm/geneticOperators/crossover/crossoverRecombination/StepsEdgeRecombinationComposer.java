package org.geneticAlgorithm.geneticOperators.crossover.crossoverRecombination;

import java.io.Serializable;
import java.util.*;
import java.awt.Point;

public class StepsEdgeRecombinationComposer implements Serializable {

    private final ArrayList<String> reasons = new ArrayList<>();
    private final ArrayList<Point> chosen = new ArrayList<>();
    private final ArrayList<Point> adjacentsTo = new ArrayList<>();
    private final ArrayList<List<Point>> adjacent = new ArrayList<>();


    public void addRecordSteps(String reason, Point chosen, Point adjacentTo, List<Point> adjacent){

        reasons.add(reason);
        this.chosen.add(chosen);
        this.adjacent.add(adjacent);
        adjacentsTo.add(adjacentTo);

    }

    public void reset(){

        reasons.clear();
        chosen.clear();
        adjacent.clear();
        adjacent.clear();
    }

    public StepsEdgeRecombination getSteps(){


       return new StepsEdgeRecombination(reasons, chosen, adjacentsTo, adjacent);
    }

    public int getSize(){

        return this.reasons.size();
    }


    public Map<String, Object> getStep(int i){

        Map<String, Object> report = new HashMap<>();

        report.put("razao", reasons.get(i));
        report.put("escolhido", chosen.get(i));
        report.put("adjacenciasDe", adjacentsTo.get(i));
        report.put("adjacencias", adjacent.get(i));


        return report;
    }

    public enum TypeChange {
        RANDOM("Aleatório"),
        ONLY_OPTION("Única opção"),
        NEXT_FINAL("Adjacente ao final"),
        FINAL("Final"),
        COMUM("Nó comum"),
        REMOVED("Nó foi removido");

        private  final String name;

        TypeChange(String name){
            this.name = name;
        }

        @Override
        public String toString(){
            return this.name;
        }
    }

}

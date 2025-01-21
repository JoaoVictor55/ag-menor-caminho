package org.example.geneticOperators.crossover.crossoverRecombinacao;

import java.io.Serializable;
import java.util.*;
import java.awt.Point;

public class ReportEdgeRecombinationComposer implements Serializable {

    private final ArrayList<String> reasons = new ArrayList<>();
    private final ArrayList<Point> chosen = new ArrayList<>();
    private final ArrayList<Point> adjacentsTo = new ArrayList<>();
    private final ArrayList<List<Point>> adjacent = new ArrayList<>();


    public void addReport(String reason, Point chosen, Point adjacentTo, List<Point> adjacent){

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

    public ReportEdgeRecombination getRecord(){


       return new ReportEdgeRecombination(reasons, chosen, adjacentsTo, adjacent);
    }

    public int getSize(){

        return this.reasons.size();
    }


    public Map<String, Object> getReport(int i){

        Map<String, Object> report = new HashMap<>();

        report.put("razao", reasons.get(i));
        report.put("escolhido", chosen.get(i));
        report.put("adjacenciasDe", adjacentsTo.get(i));
        report.put("adjacencias", adjacent.get(i));


        return report;
    }

}

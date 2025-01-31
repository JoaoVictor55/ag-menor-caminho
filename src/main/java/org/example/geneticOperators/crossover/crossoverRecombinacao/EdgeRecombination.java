package org.example.geneticOperators.crossover.crossoverRecombinacao;

import org.example.geneticOperators.crossover.Crossover;

public interface EdgeRecombination extends Crossover {


     ReportEdgeRecombination getReport();
     void setActiveReport(boolean activeReport);
     boolean isReportActive();

    enum TypeChange {
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

package org.example.geneticOperators.crossover.crossoverRecombinacao;

import lombok.Getter;
import org.example.individual.Individual;
import org.example.movimentation.Movimentation;



import java.awt.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.List;

public class CrossoverEdgeRecombination implements EdgeRecombination {

    @Getter
    private Long seed;

    private final Movimentation movimentation;

    private final Random random = new SecureRandom();

    private final ReportEdgeRecombination reportEdgeRecombination = new ReportEdgeRecombination();

    private Boolean activeReport = false;

    public CrossoverEdgeRecombination(Long seed, Movimentation movimentation){

        this.movimentation = movimentation;

        this.seed = seed;
        if(seed != null){

            random.setSeed(this.seed);
        }

    }

    public void setSeed(long seed){
        this.seed = seed;

        this.random.setSeed(seed);
    }

    //higherPriority: nós que estão diretamente ligado ao final
    private void initializeMatrices(Individual father, Individual mother, Map<Point,
            List<Point>> adjacencyMatrix,
                                    Set<Point> higherPriority, Set<Point> commonEdges){

        Set<Point> fatherEdges = new HashSet<>();

        //preenche a matriz de adjacência com as adjacências dos nós do father.
        for(int indiceNo = 0; indiceNo < father.getSize()-1; ++indiceNo){

            fatherEdges.add(father.getPosition(indiceNo));
            adjacencyMatrix.putIfAbsent(father.getPosition(indiceNo), new ArrayList<>());
            adjacencyMatrix.get(father.getPosition(indiceNo)).add(father.getPosition(indiceNo+1));

        }

        fatherEdges.add(father.getPosition(-1));
        higherPriority.add(father.getPosition(-2));

        //preenche a adjacência com os nós encontrados na mãe
        for(int indiceNo = 0; indiceNo < mother.getSize() - 1; ++indiceNo){

            adjacencyMatrix.putIfAbsent(mother.getPosition(indiceNo), new ArrayList<>());

            if(fatherEdges.contains(mother.getPosition(indiceNo))){
                commonEdges.add(mother.getPosition(indiceNo));
            }

            var possiblesPaths = movimentation.possiblePaths(mother.getPosition(indiceNo));

            for(Point p : possiblesPaths){

                if(fatherEdges.contains(p)){
                    commonEdges.add(p);
                    commonEdges.add(mother.getPosition(indiceNo));

                    if(p.equals(mother.getPosition(-1))){
                        higherPriority.add(mother.getPosition(indiceNo));
                    }


                    adjacencyMatrix.get(mother.getPosition(indiceNo)).add(p);

                    if(adjacencyMatrix.containsKey(p)){
                        adjacencyMatrix.get(p).add(mother.getPosition(indiceNo));

                    }

                }

            }

            adjacencyMatrix.get(mother.getPosition(indiceNo)).add(mother.getPosition(indiceNo+1));
        }


        if(fatherEdges.contains(mother.getPosition(-1))){
            commonEdges.add(mother.getPosition(-1));
        }

        movimentation.possiblePaths(mother.getPosition(-1)).forEach(c->{
            if(fatherEdges.contains(c)){
                commonEdges.add(c);
            }
        });


    }

    @Override
    public List<Individual> crossover(Individual father, Individual mother) {

        Map<Point, List<Point>> adjacencyMatrix = new HashMap<>();
        Set<Point> higherPriority = new HashSet<>();
        Set<Point> commonEdges = new HashSet<>();

        initializeMatrices(father, mother, adjacencyMatrix, higherPriority, commonEdges);

        Individual offSprings = new Individual(movimentation, father.getCostCalculator());
        offSprings.pushPosition(father.getPosition(0));
        Point added = offSprings.getPosition(0);

        //quando um nó é adicionado ao filho, elimina-se ele da lista de possíveis
        Set<Point> edgesAlreadyUsed = new HashSet<>();
        edgesAlreadyUsed.add(father.getPosition(0));

        boolean keepGoing = true;
        boolean found = false;

        while (keepGoing){

            //finaliza assim que o nó adicionado se conecta ao nó final
            if(adjacencyMatrix.get(added).contains(mother.getPosition(-1))){

                Point p = mother.getPosition(-1);
                offSprings.pushPosition(p);
                this.writeReport(TypeChange.FINAL, p, added,adjacencyMatrix.get(added), edgesAlreadyUsed);
                break;
            }

            Collections.shuffle(adjacencyMatrix.get(added));
            int deletadosQuantidade = 0;
            found = false;
            Point possibleEdge = null;

            for(Point adjacency : adjacencyMatrix.get(added)){

                if(edgesAlreadyUsed.contains(adjacency)){
                    ++deletadosQuantidade;
                    continue;
                }

                if(higherPriority.contains(adjacency)){

                    offSprings.pushPosition(adjacency);
                    this.writeReport(TypeChange.NEXT_FINAL, adjacency, added,adjacencyMatrix.get(added), edgesAlreadyUsed);
                    offSprings.pushPosition(mother.getPosition(-1));
                    this.writeReport(TypeChange.FINAL, mother.getPosition(-1), added,adjacencyMatrix.get(added), edgesAlreadyUsed);
                    keepGoing = false;
                    break;
                }

                if(possibleEdge == null){
                    possibleEdge = adjacency;
                }

                if(commonEdges.contains(adjacency)){
                    offSprings.pushPosition(adjacency);

                    if(deletadosQuantidade == adjacencyMatrix.get(added).size()-1){
                        this.writeReport(TypeChange.ONLY_OPTION, adjacency, added,adjacencyMatrix.get(added), edgesAlreadyUsed);
                    }
                    else
                        this.writeReport(TypeChange.COMUM, adjacency, added,adjacencyMatrix.get(added), edgesAlreadyUsed);

                    added = adjacency;
                    edgesAlreadyUsed.add(added);
                    found = true;

                    break;

                }

               if(random.nextBoolean()){
                    possibleEdge = adjacency;
                }

            }

            //caso ele não achou nenhum comum porém a lista de adjacência do nó não é vazia
            if(!found && keepGoing && possibleEdge != null){

                offSprings.pushPosition(possibleEdge);

                if(deletadosQuantidade == adjacencyMatrix.get(added).size()-1){
                    this.writeReport(TypeChange.ONLY_OPTION, possibleEdge, added,adjacencyMatrix.get(added), edgesAlreadyUsed);
                }
                else
                    this.writeReport(TypeChange.RANDOM, possibleEdge, added,adjacencyMatrix.get(added), edgesAlreadyUsed);
                edgesAlreadyUsed.add(possibleEdge);
                added = offSprings.getPosition(-1);

            } else if (!found && keepGoing) {

                offSprings.removePosition(-1);
                added = offSprings.getPosition(-1);
            }
        }

        return List.of(offSprings);
    }

    private void writeReport(TypeChange reason, Point chosen,
                             Point adjacentTo, List<Point> adjacency,
                             Set<Point> deleted){
        if(activeReport){

            List<Point> points = adjacency.stream().filter(p-> !deleted.contains(p) || (p.equals(chosen))).toList();

            reportEdgeRecombination.addReport(reason.toString(), chosen, adjacentTo,
                    points);
        }
    }

    @Override
    public boolean getActiveReport(){

        return this.activeReport;
    }

    @Override
    public ReportEdgeRecombination getReport() {

        return reportEdgeRecombination;
    }

    @Override
    public void setActiveReport() {
        this.activeReport = true;
    }
}

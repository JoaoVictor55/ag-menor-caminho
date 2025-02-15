package org.geneticAlgorithm.movimentation;
import java.awt.Point;
import java.util.List;

/**
 * Movimentation é a interface que define a lógica da movimentação em um cenário
 */
public interface Movimentation {

    /**
     * @return true se é possível ir do ponto "from" ao "to"
     */
    boolean isPossible(Point from, Point to);

    /**
     * @return lista de todos os caminhos possíveis de um ponto
     * */
    List<Point> possiblePaths(Point from);

    /**
     * @return true se o caminho é válido
     * */
    boolean isValid(List<Point> path);

}

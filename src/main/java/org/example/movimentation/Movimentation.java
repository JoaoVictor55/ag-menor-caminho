package org.example.movimentation;
import java.awt.Point;
import java.util.List;

public interface Movimentation {

    boolean isPossible(Point from, Point to);

    List<Point> possiblePaths(Point from);

    boolean isValid(List<Point> path);

}

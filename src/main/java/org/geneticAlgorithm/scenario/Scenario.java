package org.geneticAlgorithm.scenario;

import lombok.Getter;
import org.geneticAlgorithm.geneticOperators.StochasticOperator;

import java.awt.*;
import java.security.SecureRandom;
import java.util.*;

public class Scenario implements StochasticOperator {

    @Getter
    private final int maxLength;
    @Getter
    private final int maxHeight;

    @Getter
    private Long seed;

    private final Random random;


    private final float [][] cenario;

    public Scenario(int maxHeight, int maxLength){

        this(maxLength, maxHeight, null);
    }

    public Scenario(int maxHeight, int maxLength, Long seed){

        this.maxHeight = maxHeight;
        this.maxLength = maxLength;

        this.random = new SecureRandom();
        if(seed != null)
            this.random.setSeed(seed);

        this.cenario = new float[this.maxHeight][this.maxLength];

    }

    public void putBlocking(Point position){

        this.cenario[position.x][position.y] = Float.POSITIVE_INFINITY;
    }

    public void putBlocking(Collection<Point> positions){

        positions.forEach(this::putBlocking);
    }

    public void putBlockingExceptIn(float probability, Collection<Point> positions){

        HashSet<Point> dontPutHere = new HashSet<>(positions);

        for(int a = 0; a < this.maxHeight; ++a){

            int numberInLine = this.maxLength / 3;

            for(int b = 0; b < this.maxLength; ++b){

                if(numberInLine == 0) break;

                if(!dontPutHere.contains(new Point(a, b)) && random.nextFloat(1.0f) < probability){

                    --numberInLine;
                    this.cenario[a][b] = Float.POSITIVE_INFINITY;
                }
            }
        }
    }

    public void putBlocking(float probability){

        for(int a = 0; a < this.maxHeight; ++a){

            int numberInLine = this.maxLength / 3;

            for(int b = 0; b < this.maxLength; ++b){

                if(numberInLine == 0) break;

                if(random.nextFloat(1.0f) < probability){

                    --numberInLine;
                    this.cenario[a][b] = Float.POSITIVE_INFINITY;
                }
            }
        }
    }

    public void setCostInScenario(float minCost, float maxCost){

        for(int a = 0; a < this.maxHeight; ++a){
            for(int b = 0; b < this.maxLength; ++b){

                this.cenario[a][b] = this.random.nextFloat(minCost, maxCost);

            }
        }

    }


    public  boolean isOutsideBoundaries(Point position){

        return (position.x < 0 || position.y < 0) || (position.x >= this.maxHeight || position.y >= this.maxLength);
    }

    public boolean isOutsideBoundaries(int x, int y){
        return (x < 0 || y < 0) || (x >= this.maxHeight || y >= this.maxLength);
    }

    public boolean isUnblocked(Point position){

        return !isOutsideBoundaries(position) && (cenario[position.x][position.y] != Double.POSITIVE_INFINITY);
    }

    public float getCost(Point position){

        if (isOutsideBoundaries(position)){
            return Float.POSITIVE_INFINITY;
        }

        return this.cenario[position.x][position.y];
    }

    public float getCost(int x, int y){

        if(isOutsideBoundaries(x, y)){
            return Float.POSITIVE_INFINITY;
        }

        return this.cenario[x][y];
    }

    @Override
    public void setSeed(long seed){

        this.seed = seed;
        this.random.setSeed(seed);
    }


}

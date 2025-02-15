package org.geneticAlgorithm.simulation.costCalculatorFactory;

import org.geneticAlgorithm.movimentation.DefaultMovimentation;
import org.geneticAlgorithm.movimentation.Movimentation;
import org.geneticAlgorithm.scenario.Scenario;
import org.geneticAlgorithm.simulation.ObjectInstantiator;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DefaultMovimentationFactory implements ObjectInstantiator<DefaultMovimentation> {

    private DefaultMovimentation defaultMovimentation;

    private enum listOfParameters {

        SCENARIO,
        MOVIMENTATION,
        START_POINT,
        END_POINT
    }

    @Override
    public DefaultMovimentation build(Map<String, Object> parameters) throws InstantiationException {

        Point startPoint = (Point) parameters.get(listOfParameters.START_POINT.toString());

        if(startPoint == null){
            throw new InstantiationException("StartPoint argument is missing");
        }

        Point endPoint = (Point) parameters.get(listOfParameters.END_POINT.toString());

        if(endPoint == null){
            throw new InstantiationException("EndPoint argument is missing");
        }

        Scenario scenario = (Scenario) parameters.get(listOfParameters.SCENARIO.toString());

        if(scenario == null){
            throw new InstantiationException("Scenario argument is missing");
        }

        Movimentation movimentation = (Movimentation)parameters.get(listOfParameters.MOVIMENTATION.toString());
        if(movimentation == null){
            throw new InstantiationException("Movimentation argument is missing");
        }

        this.defaultMovimentation = new DefaultMovimentation(scenario, startPoint, endPoint);


        return null;
    }

    @Override
    public DefaultMovimentation getInstance() {
        return this.defaultMovimentation;
    }

    @Override
    public Map<String, Object> getParametersListOfConstructor() {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(listOfParameters.MOVIMENTATION.toString(), Movimentation.class);
        hashMap.put(listOfParameters.END_POINT.toString(), Point.class);
        hashMap.put(listOfParameters.START_POINT.toString(), Point.class);

        return hashMap;
    }
}

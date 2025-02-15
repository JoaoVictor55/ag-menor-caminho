package org.geneticAlgorithm.simulation.costCalculatorFactory;

import org.geneticAlgorithm.cost.DefaultCostCalculator;
import org.geneticAlgorithm.movimentation.Movimentation;
import org.geneticAlgorithm.scenario.Scenario;
import org.geneticAlgorithm.simulation.ObjectInstantiator;

import java.util.HashMap;
import java.util.Map;

public class DefaultCostCalculatorFactory implements ObjectInstantiator<DefaultCostCalculator> {

    private DefaultCostCalculator defaultCostCalculator;

    private enum listOfParameters {

        SCENARIO,
        MOVIMENTATION,
    }

    @Override
    public DefaultCostCalculator build(Map<String, Object> parameters) throws InstantiationException {

        Scenario scenario = (Scenario)parameters.getOrDefault(listOfParameters.SCENARIO.toString(),
                null);
        if(scenario == null){

            throw new InstantiationException("Scenario argument is missing");
        }

        Movimentation movimentation = (Movimentation)parameters.getOrDefault(listOfParameters.MOVIMENTATION.toString(),
                null);

        if(movimentation == null){
            throw new InstantiationException("Movimentation argument is missing");
        }

        this.defaultCostCalculator = new DefaultCostCalculator(scenario, movimentation);

        return this.defaultCostCalculator;
    }

    @Override
    public DefaultCostCalculator getInstance() {
        return this.defaultCostCalculator;
    }


    @Override
    public Map<String, Object> getParametersListOfConstructor() {

        Map<String, Object> parameterListOfConstructor = new HashMap<>();
        parameterListOfConstructor.put(listOfParameters.MOVIMENTATION.toString(), Movimentation.class);
        parameterListOfConstructor.put(listOfParameters.SCENARIO.toString(), Scenario.class);

        return parameterListOfConstructor;
    }
}

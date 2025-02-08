package org.example.simulation.costCalculatorFactory;

import org.example.cost.DefaultCostCalculator;
import org.example.movimentation.Movimentation;
import org.example.scenario.Scenario;
import org.example.simulation.ObjectInstantiator;

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
        return Map.of();
    }
}

package org.example.simulation.geneticOperatorFactory;

import org.example.geneticOperators.crossover.crossoverRecombinacao.CrossoverEdgeRecombination;
import org.example.movimentation.Movimentation;
import org.example.simulation.ObjectInstancer;

import java.util.HashMap;
import java.util.Map;

public class CrossoverEdgeRecombinationInstantiator implements ObjectInstancer<org.example.geneticOperators.crossover.crossoverRecombinacao.CrossoverEdgeRecombination> {

    private CrossoverEdgeRecombination crossoverEdgeRecombination;

    private enum listOfParameters {

        SEED,
        MOVIMENTATION,
        ACTIVE_REPORT,
    }

    @Override
    public CrossoverEdgeRecombination build(Map<String, Object> parameters) throws InstantiationException {


        Long seed = (Long)parameters.getOrDefault(listOfParameters.SEED.toString(), null);
        Movimentation movimentation = (Movimentation)parameters.getOrDefault(listOfParameters.MOVIMENTATION.toString(),
                null);

        if(movimentation == null){
            throw new InstantiationException("Movimentation argument is missing");
        }

        this.crossoverEdgeRecombination = new CrossoverEdgeRecombination(seed, movimentation);

        return crossoverEdgeRecombination;
    }

    @Override
    public CrossoverEdgeRecombination configure(Map<String, Object> parameters) {

        Long seed = (Long)parameters.getOrDefault(listOfParameters.SEED.toString(), null);

        if(seed != null){
            this.crossoverEdgeRecombination.setSeed(seed);
        }

        Boolean activeReport = (Boolean) parameters.getOrDefault(listOfParameters.ACTIVE_REPORT.toString(),
                null);

        if(activeReport != null){
            this.crossoverEdgeRecombination.setActiveReport(activeReport);
        }

        return this.crossoverEdgeRecombination;
    }

    @Override
    public CrossoverEdgeRecombination getInstance() {
        return this.crossoverEdgeRecombination;
    }

    @Override
    public Map<String, Object> getParametersList() {

        Map<String, Object> parameters = new HashMap<>();

        parameters.put(listOfParameters.SEED.toString(), Long.class);
        parameters.put(listOfParameters.ACTIVE_REPORT.toString(), Boolean.class);

        return parameters;
    }

    @Override
    public Map<String, Object> getParametersListOfConstructor() {

        Map<String, Object> parameters = new HashMap<>();

        parameters.put(listOfParameters.SEED.toString(), Long.class);
        parameters.put(listOfParameters.MOVIMENTATION.toString(), Movimentation.class);

        return parameters;
    }
}

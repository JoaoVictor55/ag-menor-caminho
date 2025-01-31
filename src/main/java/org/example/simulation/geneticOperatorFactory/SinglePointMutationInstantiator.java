package org.example.simulation.geneticOperatorFactory;

import org.example.geneticOperators.mutation.singlePointMutation.SinglePointMutation;
import org.example.movimentation.Movimentation;
import org.example.simulation.ObjectInstancer;

import java.util.HashMap;
import java.util.Map;

public class SinglePointMutationInstantiator implements ObjectInstancer<SinglePointMutation> {

    private SinglePointMutation singlePointMutation;

    private enum listOfParameters {

        SEED,
        MAX_ITERATION,
        MUTATION_PROBABILITY,
        MOVIMENTATION,
        ACTIVE_REPORT,
    }

    @Override
    public SinglePointMutation build(Map<String, Object> parameters) throws InstantiationException {

        Long seed = (long)parameters.getOrDefault(listOfParameters.SEED.toString(), null);
        Movimentation movimentation = (Movimentation) parameters.getOrDefault(listOfParameters.MOVIMENTATION.toString(),
                null);

        if(movimentation == null){
            throw new InstantiationException("Movimentation argument is missing");
        }

        Integer maxIteration = (Integer) parameters.getOrDefault(listOfParameters.MAX_ITERATION.toString(),
                null);

        Double mutationProbability = (Double)parameters.getOrDefault(listOfParameters.MUTATION_PROBABILITY.toString(), null);

        if(mutationProbability == null){
            throw new InstantiationException("MutationProbability argument is missing");
        }


        this.singlePointMutation = new SinglePointMutation(seed, movimentation, mutationProbability, maxIteration);

        return this.singlePointMutation;
    }

    @Override
    public SinglePointMutation configure(Map<String, Object> parameters) {

        Boolean activeMutationReport = (Boolean) parameters.getOrDefault(listOfParameters.ACTIVE_REPORT.toString(),
                null);

        if(activeMutationReport != null){
            this.singlePointMutation.activateReport(activeMutationReport);
        }

        Long seed = (Long)parameters.getOrDefault(listOfParameters.SEED.toString(),
                null);

        if(seed != null){
            this.singlePointMutation.setSeed(seed);
        }

        Double mutationProbability = (Double) parameters.getOrDefault(listOfParameters.MUTATION_PROBABILITY.toString(),
                null);

        if(mutationProbability != null){
            this.singlePointMutation.setMutationProbability(mutationProbability);
        }

        Integer maxIteration = (Integer) parameters.getOrDefault(listOfParameters.MAX_ITERATION.toString(),
                null);

        if(maxIteration != null){
            this.singlePointMutation.setMaxIterations(maxIteration);
        }

        return singlePointMutation;
    }

    @Override
    public SinglePointMutation getInstance() {
        return this.singlePointMutation;
    }

    @Override
    public Map<String, Object> getParametersList() {

        HashMap<String, Object> parameters = new HashMap<>();

        parameters.put(listOfParameters.SEED.toString(), Long.class);
        parameters.put(listOfParameters.MAX_ITERATION.toString(), Integer.class);
        parameters.put(listOfParameters.MUTATION_PROBABILITY.toString(), Double.class);
        parameters.put(listOfParameters.ACTIVE_REPORT.toString(), Boolean.class);

        return parameters;
    }

    @Override
    public Map<String, Object> getParametersListOfConstructor() {
        HashMap<String, Object> parameters = new HashMap<>();

        parameters.put(listOfParameters.SEED.toString(), Long.class);
        parameters.put(listOfParameters.MAX_ITERATION.toString(), Integer.class);
        parameters.put(listOfParameters.MUTATION_PROBABILITY.toString(), Double.class);
        parameters.put(listOfParameters.ACTIVE_REPORT.toString(), Boolean.class);
        parameters.put(listOfParameters.MOVIMENTATION.toString(), Movimentation.class);

        return parameters;
    }
}

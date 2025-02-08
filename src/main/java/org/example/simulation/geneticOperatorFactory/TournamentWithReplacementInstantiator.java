package org.example.simulation.geneticOperatorFactory;

import org.example.geneticOperators.selection.TournamentWithReplacement;
import org.example.simulation.ObjectConfigurator;
import org.example.simulation.ObjectInstantiator;

import java.util.HashMap;
import java.util.Map;

public class TournamentWithReplacementInstantiator implements ObjectInstantiator<TournamentWithReplacement>,
        ObjectConfigurator<TournamentWithReplacement> {

    private TournamentWithReplacement tournamentWithReplacement;
    private enum listOfParameters{
        
        SEED("seed"),
        TOURNAMENT_SIZE("tournamentSize");

        private final String name;

        listOfParameters(String name) {
            this.name = name;
        }

        @Override
        public String toString(){

            return this.name;
        }
    }

    @Override
    public TournamentWithReplacement build(Map<String, Object> parameters) throws InstantiationException {

        Integer tournamentSize = (Integer) parameters.getOrDefault(listOfParameters.TOURNAMENT_SIZE.toString(),
                null);

        if(tournamentSize == null){
            throw new InstantiationException("Argument tournamentSize is missing");
        }

        Long seed = (Long) parameters.getOrDefault(listOfParameters.SEED.toString(), null);

        if(seed == null)
            this.tournamentWithReplacement = new TournamentWithReplacement(tournamentSize);
        else
            this.tournamentWithReplacement = new TournamentWithReplacement(tournamentSize, seed);

        return this.tournamentWithReplacement;
    }

    @Override
    public TournamentWithReplacement configure(Map<String, Object> parameters) {

        Long seed = (Long)parameters.getOrDefault(listOfParameters.SEED.toString(), null);
        Integer tournamentSize = (Integer)parameters.getOrDefault(listOfParameters.TOURNAMENT_SIZE.toString(),
                null);

        if(seed != null){
            this.tournamentWithReplacement.setSeed(seed);
        }
        if(tournamentSize != null){
            this.tournamentWithReplacement.setTournamentSize(tournamentSize);
        }

        return tournamentWithReplacement;
    }

    @Override
    public TournamentWithReplacement getInstance() {
        return this.tournamentWithReplacement;
    }

    @Override
    public Map<String, Object> getParametersList() {

        HashMap<String, Object> m = new HashMap<>();

        m.put(listOfParameters.SEED.toString(), Integer.class);
        m.put(listOfParameters.TOURNAMENT_SIZE.toString(), Long.class);

        return m;
    }

    @Override
    public Map<String, Object> getParametersListOfConstructor() {
        HashMap<String, Object> m = new HashMap<>();

        m.put(listOfParameters.SEED.toString(), Integer.class);
        m.put(listOfParameters.TOURNAMENT_SIZE.toString(), Long.class);

        return m;
    }
}

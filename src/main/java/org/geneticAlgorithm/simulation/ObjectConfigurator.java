package org.geneticAlgorithm.simulation;

import java.util.Map;

public interface ObjectConfigurator <O>{

    O configure(Map<String, Object> parameters);
    Map<String, Object> getParametersList();
}

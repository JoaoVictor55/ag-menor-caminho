package org.example.simulation;

import java.util.Map;

public interface ObjectInstantiator<O>{

    O build(Map<String, Object> parameters) throws InstantiationException;
    O getInstance();
    Map<String, Object> getParametersListOfConstructor();

}

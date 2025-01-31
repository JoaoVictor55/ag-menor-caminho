package org.example.simulation;

import java.util.Map;

public interface ObjectInstancer<O>{

    O build(Map<String, Object> parameters) throws InstantiationException;
    O configure(Map<String, Object> parameters);
    O getInstance();
    Map<String, Object> getParametersList();
    Map<String, Object> getParametersListOfConstructor();

}

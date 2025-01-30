package org.example.simulation.factory;

import java.util.List;
import java.util.Map;

public interface ObjectInstancer<T>{

    T build(Map<String, Object> parameters) throws InstantiationException;
    T configure(Map<String, Object> parameters);
    T getInstance();
    Map<String, Object> getParametersList();
    Map<String, Object> getParametersListOfConstructor();
}

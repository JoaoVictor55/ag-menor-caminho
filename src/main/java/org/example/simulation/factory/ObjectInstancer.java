package org.example.simulation.factory;

import java.util.List;
import java.util.Map;

public interface ObjectInstancer<T>{

    T build(Map<String, Object> parameters);
    T configure(Map<String, Object> parameters);
    T getInstance();
    List<String> getParametersList();
}

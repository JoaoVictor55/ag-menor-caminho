package org.geneticAlgorithm.geneticOperators;

public interface OperatorWithSteps<R>{

    void recordSteps(boolean activate);
    boolean isStepsRecordActive();
    R getSteps();
}

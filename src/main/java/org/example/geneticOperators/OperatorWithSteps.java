package org.example.geneticOperators;

public interface OperatorWithSteps<R>{

    void recordSteps(boolean activate);
    boolean isStepsRecordActive();
    R getSteps();
}

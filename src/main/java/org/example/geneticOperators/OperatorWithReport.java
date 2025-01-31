package org.example.geneticOperators;

public interface OperatorWithReport <R>{

    void setActiveReport(boolean activate);
    boolean isReportActive();
    R getReport();
}

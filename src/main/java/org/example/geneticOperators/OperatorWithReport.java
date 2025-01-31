package org.example.geneticOperators;

public interface OperatorWithReport <R>{

    void setActivateReport(boolean activate);
    boolean isReportActive();
    R getReport();
}

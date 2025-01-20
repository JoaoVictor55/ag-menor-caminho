package org.example.geneticOperators.mutation.singlePointMutation;

import org.example.geneticOperators.mutation.Mutation;

public interface MutationWithReport extends Mutation {


    void activateReport(boolean activate);
    boolean isReportActive();

    ReportMutation getResult();

    enum Type {

        REPLACED("substituído"),
        NO_MUTATION("a mutação não ocorreu"),
        REMOVED("removido");

        Type(String name){

        }
    }

}

package org.example.geneticOperators.mutation;

import org.example.geneticOperators.OperatorWithReport;

public interface MutationWithReport<R> extends OperatorWithReport<R>, Mutation {

    enum Type {

        REPLACED("substituído"),
        NO_MUTATION("a mutação não ocorreu"),
        REMOVED("removido");

        Type(String name){

        }
    }
}

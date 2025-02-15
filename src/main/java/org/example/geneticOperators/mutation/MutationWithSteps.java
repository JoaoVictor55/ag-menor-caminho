package org.example.geneticOperators.mutation;

import org.example.geneticOperators.OperatorWithSteps;

public interface MutationWithSteps<R> extends OperatorWithSteps<R>, Mutation {

    enum Type {

        REPLACED("substituído"),
        NO_MUTATION("a mutação não ocorreu"),
        REMOVED("removido");

        Type(String name){

        }
    }
}

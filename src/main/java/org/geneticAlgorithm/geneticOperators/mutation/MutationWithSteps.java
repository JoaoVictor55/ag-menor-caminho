package org.geneticAlgorithm.geneticOperators.mutation;

import org.geneticAlgorithm.geneticOperators.OperatorWithSteps;

public interface MutationWithSteps<R> extends OperatorWithSteps<R>, Mutation {

    enum Type {

        REPLACED("substituído"),
        NO_MUTATION("a mutação não ocorreu"),
        REMOVED("removido");

        Type(String name){

        }
    }
}

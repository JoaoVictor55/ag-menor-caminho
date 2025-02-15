package org.geneticAlgorithm.geneticOperators.mutation.singlePointMutation;

import org.geneticAlgorithm.geneticOperators.mutation.MutationWithSteps;

import java.awt.*;

public record StepsMutation(
        int whereHappened,
        Point originalPoint,
        Point newPoint,
        MutationWithSteps.Type reason
) {
}

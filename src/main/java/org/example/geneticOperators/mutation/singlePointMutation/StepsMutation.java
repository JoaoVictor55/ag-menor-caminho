package org.example.geneticOperators.mutation.singlePointMutation;

import org.example.geneticOperators.mutation.MutationWithSteps;

import java.awt.*;

public record StepsMutation(
        int whereHappened,
        Point originalPoint,
        Point newPoint,
        MutationWithSteps.Type reason
) {
}

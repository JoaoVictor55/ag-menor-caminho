package org.example.geneticOperators.mutation.singlePointMutation;

import org.example.geneticOperators.mutation.MutationWithReport;

import java.awt.*;

public record ReportMutation(
        int whereHappened,
        Point originalPoint,
        Point newPoint,
        MutationWithReport.Type reason
) {
}

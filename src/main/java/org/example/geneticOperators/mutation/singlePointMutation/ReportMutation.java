package org.example.geneticOperators.mutation.singlePointMutation;

import java.awt.*;

public record ReportMutation(
        int whereHappened,
        Point originalPoint,
        Point newPoint,
        MutationWithReport.Type reason
) {
}

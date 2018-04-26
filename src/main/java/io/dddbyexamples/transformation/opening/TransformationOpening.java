package io.dddbyexamples.transformation.opening;

import io.dddbyexamples.transformation.TransformationEvents;
import io.dddbyexamples.transformation.TransformationTypes;
import io.dddbyexamples.transformation.opening.validation.DecisionDateValidation;
import io.dddbyexamples.transformation.opening.validation.DecisionValidator;
import io.dddbyexamples.transformation.opening.validation.InstitutionValidator;
import lombok.AllArgsConstructor;

import java.util.Collections;

@AllArgsConstructor
public class TransformationOpening {

    private final DecisionValidator decisionValidator;
    private final DecisionDateValidation decisionDateValidation;
    private final InstitutionValidator institutionValidator;

    private final TransformationEvents events;

    public OpeningResult openInclusion(OpenInclusion command) {
        //if (validator nok)
        // return OpeningResult.validationIssues(validationResult);

        TransformationOpened event = new TransformationOpened(
                TransformationTypes.INCLUSION,
                command.getDocument(),
                Collections.singletonList(command.getTargetInstitution()),
                command.getTransformedInstitutions()
        );

        events.emit(event);
        return OpeningResult.openedCorrectly();
    }
}

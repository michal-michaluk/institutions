package io.dddbyexamples.transformation.opening;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.dddbyexamples.transformation.opening.validation.InstitutionValidator;
import io.dddbyexamples.transformation.opening.validation.ValidationResult;
import org.assertj.core.util.Lists;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by apawlak on 25.04.18.
 */
public class InstitutionSteps {

    private Map<String, Institution> institutions = new HashMap<>();
    private InstitutionValidator institutionValidator = new InstitutionValidator();
    private ValidationResult validationResult;

    @Given("^Instytucja \"([^\"]*)\" ma rodzaj \"([^\"]*)\"$")
    public void instytucjaAMaRodzaj(String institution, InstitutionKind institutionKind) throws Throwable {
        institutions.put(institution, new Institution(InstutuionStatus.OPEN, institutionKind, null));
    }

    @Given("^Instytucja \"([^\"]*)\" ma status \"([^\"]*)\"$")
    public void instytucjaMaStatus(String institution, InstutuionStatus status) throws Throwable {
        institutions.put(institution, new Institution(status, InstitutionKind.PUBLIC, null));
    }

    @When("^Sprawdzamy status instytucji \"([^\"]*)\"$")
    public void sprawdzamyStatusInstytucji(String institution) throws Throwable {
        validationResult = institutionValidator.validateStatus(
                institutions.get(institution).getStatus()
        );
    }

    @When("^Sprawdzamy rodzaj instytucji \"([^\"]*)\"$")
    public void sprawdzamyRodzajInstytucji(String institution) throws Throwable {
        validationResult = institutionValidator.isKindCantBeTransformed(
                institutions.get(institution).getInstitutionKind()
        );
    }

    @When("^Porównujemy rodzaje instytucji$")
    public void porównujemyRodzajeInstytucji() throws Throwable {
        validationResult = institutionValidator.validateKind(
                institutions.values().stream()
                        .map(Institution::getInstitutionKind)
                        .collect(Collectors.toList())
        );
    }

    @Then("^rezultat walidacji to \"([^\"]*)\" a informacja to \"([^\"]*)\"$")
    public void rezultatWalidacjiToAMessageTo(boolean result, String message) throws Throwable {
        Assert.assertEquals(result, validationResult.isValid());
        Assert.assertEquals(message, validationResult.getMessage());
    }
}

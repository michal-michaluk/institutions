package io.dddbyexamples.transformation.opening;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.dddbyexamples.transformation.opening.Institution;
import io.dddbyexamples.transformation.opening.InstutuionStatus;
import io.dddbyexamples.transformation.opening.validation.InstitutionValidator;
import io.dddbyexamples.transformation.opening.validation.ValidationResult;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * Created by apawlak on 25.04.18.
 */
public class InstitutionSteps {
    private Institution institutionA;
    private Institution institutionB;
    private Institution institutionC;
    private InstitutionValidator institutionValidator = new InstitutionValidator();

    @Before
    public void setUp() {
    }

    @Given("^Instytucja A ma status \"([^\"]*)\"$")
    public void instytucjaAMaStatus(InstutuionStatus status) throws Throwable {
        institutionA = new Institution(status, InstitutionKind.PUBLIC, null);
    }

    @When("^Sprawdzamy status instytucji$")
    public void sprawdzamyStatusInstytucji() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
    }

    @Then("^Rezultat walidacji statusu to \"([^\"]*)\"  i informacja \"([^\"]*)\"$")
    public void rezultatWalidacjiStatusuToIInformacja(boolean result, String messagee) throws Throwable {
        ValidationResult validationResult = institutionValidator.validateStatus(institutionA.getStatus());
        Assert.assertEquals(result, validationResult.isValid());
        Assert.assertEquals(messagee, validationResult.getMessage());
    }


    @Given("^Instytucja A jest \"([^\"]*)\"$")
    public void instytucjaAJest(InstitutionKind institutionKind) throws Throwable {
        institutionA = new Institution(InstutuionStatus.OPENING, institutionKind, null);
    }

    @Given("^Instytucja B jest \"([^\"]*)\"$")
    public void instytucjaBJest(InstitutionKind institutionKind) throws Throwable {
        institutionB = new Institution(InstutuionStatus.OPENING, institutionKind, null);
    }

    @When("^Porównujemy rodzaje instytucji$")
    public void porównujemyRodzajeInstytucji() throws Throwable {
    }

    @Then("^rezultat walidacji rodzaju to \"([^\"]*)\" i informacja \"([^\"]*)\"$")
    public void rezultatWalidacjiRodzajuToIInformacja(boolean result, String messagee) throws Throwable {
        ValidationResult validationResult = institutionValidator.validateKind(Lists.newArrayList(institutionA.getInstitutionKind(),
                institutionB.getInstitutionKind()));
        Assert.assertEquals(result, validationResult.isValid());
        Assert.assertEquals(messagee, validationResult.getMessage());
    }


    @Given("^Instytucja A jest niepublicyna$")
    public void instytucjaAJestNiepublicyna() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        institutionA = new Institution(InstutuionStatus.OPENING, InstitutionKind.NOT_PUBLIC, null);
    }

    @Given("^Instytucja B jest niepublicyna$")
    public void instytucjaBJestNiepublicyna() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        institutionB = new Institution(InstutuionStatus.OPENING, InstitutionKind.NOT_PUBLIC, null);
    }

    @Given("^Instytucja C jest publicyna$")
    public void instytucjaCJestPublicyna() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        institutionC = new Institution(InstutuionStatus.OPENING, InstitutionKind.PUBLIC, null);
        ;
    }

    @Then("^rezultat walidacji to false i informacja niemożna połączyć instytucji publicznej i niepublicznej$")
    public void rezultatWalidacjiToFalseIInformacjaNiemożnaPołączyćInstytucjiPublicznejINiepublicznej() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ValidationResult validationResult = institutionValidator.validateKind(Lists.newArrayList(institutionA.getInstitutionKind(),
                institutionB.getInstitutionKind(), institutionC.getInstitutionKind()));
        Assert.assertEquals(Boolean.FALSE, validationResult.isValid());
        Assert.assertEquals("niemożna połączyć instytucji publicznej i niepublicznej", validationResult.getMessage());
    }

    @Given("^Instytucja A ma rodzaj \"([^\"]*)\"$")
    public void instytucjaAMaRodzaj(InstitutionKind institutionKind) throws Throwable {
        institutionA = new Institution(InstutuionStatus.OPENING, institutionKind, null);
    }

    @When("^Sprawdzamy rodzaj instytucji$")
    public void sprawdzamyRodzajInstytucji() throws Throwable {
    }

    @Then("^rezultat walidacji to \"([^\"]*)\" a message to \"([^\"]*)\"$")
    public void rezultatWalidacjiToAMessageTo(boolean result, String message) throws Throwable {
        ValidationResult validationResult = institutionValidator.isKindCantBeTransformed(institutionA.getInstitutionKind());
        Assert.assertEquals(result, validationResult.isValid());
        Assert.assertEquals(message, validationResult.getMessage());
    }
}

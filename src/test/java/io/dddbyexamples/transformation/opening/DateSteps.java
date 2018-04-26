package io.dddbyexamples.transformation.opening;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.dddbyexamples.transformation.opening.validation.DecisionDateValidation;
import io.dddbyexamples.transformation.opening.validation.ValidationResult;
import org.assertj.core.util.Lists;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by apawlak on 25.04.18.
 */
public class DateSteps {
    private LocalDate currentDate;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Institution institutionA;
    private Institution institutionB;
    private DecisionDateValidation decisionDateValidation = new DecisionDateValidation();
    private LocalDate checkOnDate;


    @Given("^dzisiejsza data to \"([^\"]*)\"$")
    public void dzisiejszaDataTo(String date) throws Throwable {
        currentDate = LocalDate.parse(date, formatter);
    }

    @Given("^instytucji A założona dnia \"([^\"]*)\"$")
    public void instytucjiAZałożonaDnia(String startDate) throws Throwable {
        institutionA = new Institution(Institution.Status.OPEN, Institution.Kind.NOT_PUBLIC, LocalDate.parse(startDate, formatter));
    }

    @Given("^instytucji B założona dnia \"([^\"]*)\"$")
    public void instytucjiBZałożonaDnia(String startDate) throws Throwable {
        institutionB = new Institution(Institution.Status.OPEN, Institution.Kind.NOT_PUBLIC, LocalDate.parse(startDate, formatter));
    }

    @When("^sprawdzamy poprawność dat dla przekształcenia z datą \"([^\"]*)\"$")
    public void sprawdzamyPoprawnośćDatDlaPrzekształceniaZDatą(String checkWithDate) throws Throwable {
        checkOnDate = LocalDate.parse(checkWithDate, formatter);
    }

    @Then("^wszystkie daty są poprawne$")
    public void wszystkieDatySąPoprawne() throws Throwable {
        ValidationResult validationResult = decisionDateValidation.checkInstitutionOnDate(Lists.newArrayList(institutionA, institutionB), checkOnDate);
        Assert.assertTrue(validationResult.isValid());
    }

    @When("^chcemy zamknąć przekształcenie w dniu \"([^\"]*)\"$")
    public void chcemyZamknąćPrzekształcenieWDniu(String checkWithDate) throws Throwable {
        checkOnDate = LocalDate.parse(checkWithDate, formatter);
    }

    @Then("^operacja jest niemożliwa$")
    public void operacjaJestNiemożliwa() throws Throwable {
        ValidationResult validationResult = decisionDateValidation.checkInstitutionOnDate(Lists.newArrayList(institutionA, institutionB), checkOnDate);
        Assert.assertFalse(validationResult.isValid());
    }

    @Then("^operacja została wykonana$")
    public void operacjaZostałaWykonana() throws Throwable {
        ValidationResult validationResult = decisionDateValidation.checkInstitutionOnDate(Lists.newArrayList(institutionA, institutionB), checkOnDate);
        Assert.assertTrue(validationResult.isValid());
    }
}


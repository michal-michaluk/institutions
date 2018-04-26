package io.dddbyexamples.transformation.opening;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.dddbyexamples.transformation.TransformationEvents;
import io.dddbyexamples.transformation.closing.TransformationReport;
import io.dddbyexamples.transformation.ongoing.AssetsDistributed;
import io.dddbyexamples.transformation.ongoing.KnownAssetsChanged;
import io.dddbyexamples.transformation.opening.validation.DecisionDateValidation;
import io.dddbyexamples.transformation.opening.validation.DecisionValidator;
import io.dddbyexamples.transformation.opening.validation.InstitutionValidator;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransformationOpeningSteps {

    private Clock clock = Clock.systemUTC();

    private DecisionValidator decisionValidator = Mockito.mock(DecisionValidator.class);
    private DecisionDateValidation decisionDateValidation = Mockito.mock(DecisionDateValidation.class);
    private InstitutionValidator institutionValidator = Mockito.mock(InstitutionValidator.class);
    private TransformationEventsFake events = new TransformationEventsFake();

    private TransformationOpening subject;
    private OpeningResult result;

    @Before
    public void setUp() throws Exception {
        subject = new TransformationOpening(
                decisionValidator,
                decisionDateValidation,
                institutionValidator,
                events
        );
    }

    @Given("^instytucji \"([^\"]*)\"$")
    public void instytucji(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^włączamy instytucję \"([^\"]*)\" do instytucji \"([^\"]*)\"$")
    public void włączamyInstytucjęDoInstytucji(String transformedInstitution,
                                               String targetInstitution) throws Throwable {
        OpenInclusion command = new OpenInclusion(
                createExampleDecisionDocument(
                        LocalDate.now(clock).minusWeeks(1),
                        LocalDate.now(clock).plusMonths(1)),
                targetInstitution,
                Collections.singletonList(transformedInstitution)
        );

        result = subject.openInclusion(command);
    }

    private DecisionDocument createExampleDecisionDocument(LocalDate decisionDate, LocalDate executionDate) {
        return new DecisionDocument(decisionDate, executionDate);
    }

    @Given("^walidacja decyzji \\(w tym dat i instytucji\\) przeszła poprawnie$")
    public void walidacjaDecyzjiWTymDatIInstytucjiPrzeszłaPoprawnie() throws Throwable {
        Mockito.when(decisionValidator.validate(null))
                .thenReturn("");
        throw new PendingException();
    }

    @Given("^walidacja dat przeszła poprawnie$")
    public void walidacjaDatPrzeszłaPoprawnie() throws Throwable {
        Mockito.when(decisionDateValidation.validate(null))
                .thenReturn("");
        throw new PendingException();
    }

    @Given("^walidacja instytucji przeszła poprawnie$")
    public void walidacjaInstytucjiPrzeszłaPoprawnie() throws Throwable {
        Mockito.when(institutionValidator.validate(null))
                .thenReturn("");
        throw new PendingException();
    }

    @Then("^w tym przekształceniu instytucja \"([^\"]*)\" jest instucucją docelową$")
    public void wTymPrzekształceniuInstytucjaJestInstucucjąDocelową(String targetInstitution) throws Throwable {
        TransformationOpened event = events.expectSingleOpening();
        Assertions.assertThat(event.getTargetInstitutions())
                .contains(targetInstitution);
    }

    @Then("^weryfikacja zasad decyzji \\(w tym dat i instytucji\\) dat jest ok$")
    public void weryfikacjaZasadDecyzjiWTymDatIInstytucjiDatJestOk() throws Throwable {
        Assertions.assertThat(result).isNotNull()
        //    .extracting(OpeningResult::openedCorrectly)
        ;
        throw new PendingException();
    }

    @Then("^instytucja B ma status \"([^\"]*)\" na wykazie Instutucji Szkolnictwa Wyższego i Nauki$")
    public void instytucjaBMaStatusNaWykazieInstutucjiSzkolnictwaWyższegoINauki(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^zadanie dla pracowników z instytucjia \"([^\"]*)\" zostało zdefiniowane w harmonogramach$")
    public void zadanieDlaPracownikówZInstytucjiaZostałoZdefiniowaneWHarmonogramach(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^przekształcenie pojawiło się na liście przekształceń$")
    public void przekształceniePojawiłoSięNaLiściePrzekształceń() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^postęp przekształcenia zawiera informację \"([^\"]*)\"$")
    public void postępPrzekształceniaZawieraInformację(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    private class TransformationEventsFake implements TransformationEvents {
        private List<TransformationOpened> openedEvents = new ArrayList<>();
        private List<KnownAssetsChanged> knownEvents = new ArrayList<>();
        private List<AssetsDistributed> distributedEvents = new ArrayList<>();
        private List<TransformationReport> reportEvents = new ArrayList<>();

        @Override
        public void emit(TransformationOpened event) {
            openedEvents.add(event);
        }

        @Override
        public void emit(KnownAssetsChanged event) {
            knownEvents.add(event);
        }

        @Override
        public void emit(AssetsDistributed event) {
            distributedEvents.add(event);
        }

        @Override
        public void emit(TransformationReport event) {
            reportEvents.add(event);
        }

        public TransformationOpened expectSingleOpening() {
            Assertions.assertThat(openedEvents).hasSize(1);
            return openedEvents.get(0);
        }
    }
}

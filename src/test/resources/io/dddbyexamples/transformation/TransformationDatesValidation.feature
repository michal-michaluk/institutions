Feature: weryfikacja poprawności dat w procesie przekształceń

  Scenario: weryfikacja poprawności dat
    Given dzisiejsza data to "11.02.2015"
    Given instytucji "A" założona dnia 20.05.2010
    Given instytucji "B" założona dnia 11.02.2013
    When sprawdzamy poprawność dat dla przekształcenia z datą "01.02.2015"
    Then wszystkie daty są poprawne


  Scenario: przekształcenie z datą przyszłą
    Given dzisiejsza data to "11.02.2015"
    Given instytucji "A" założona dnia "20.05.2010"
    Given instytucji "B" założona dnia "11.02.2013"
    When sprawdzamy poprawność dat dla przekształcenia z datą "11.04.2015"
    Then wszystkie daty są poprawne
    When chcemy zamknąć przekształcenie w dniu "11.02.2015"
    Then operacja jest niemożliwa
    When chcemy zamknąć przekształcenie w dniu "11.04.2015"
    Then operacja została wykonana


  Scenario: przekształcenie z datą z przed założenia instytucji
    Given dzisiejsza data to "11.02.2015"
    Given instytucji "A" założona dnia "11.02.2015"
    Given instytucji "B" założona dnia "11.02.2013"
    When sprawdzamy poprawność dat dla przekształcenia z datą "01.01.2015"
    Then daty NIE są poprawne, komunikat: data założenia instytucji "A 11.02.2015" jest puźniejsza data przekształcenia "01.01.2015"

    # TODO zbierz kolejne scenariusze i doprecyzuj
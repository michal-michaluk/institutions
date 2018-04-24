Feature: zamknięcie procesu przekształcenia

  zamkniecie przekształcenia
  - weryfikacja czy wszystkie kierunki są rozdzielone lub zamknięte
  - weryfikacja czy wszystkie byty zależne są rozdzielone lub zamknięte
  ** przy założeniu, że zmiany dokonują się dopiero na moment zamknięcia przekształcenia:
  - delegacja do systemów zależnych instrukcji jak rozdysponować ich byty

  lista przekształceń [read model]

  postęp przekształcenia [read model]
  - weryfikacja postępu przy rozdzielaniu kierunków
  - weryfikacja postępu przy rozdzielaniu ...


  historia kierunku [read model]
  - ciągłość jest zachowana
  - widoczny jest wpis z przekształceniem

  weryfikacja innych bytów...

  Background:
    Given instytucji "A"
    Given instytucji "B"

    When włączamy instytucję "B" do instytucji "A"
    Then w tym przekształceniu instytucja "A" jest instucucją docelową
    Then weryfikacja zasad dla dat jest ok

    Then instystucja B ma status "Wtrakcie przekształcenia" na wykazie Instutucji Szkolnictwa Wyższego i Nauki
    And zadanie dla pracowników z instytucjia "A" zostało zdewiniowane w harmonogramach
    And zadanie dla pracowników z instytucjia "B" zostało zdewiniowane w harmonogramach
    And przekształcenie pojawiło się na liście przekształceń
    And postęp przekształcenia zawiera informację ""
    # pracownik z instytucji B już nie pracuje


  Scenario: poprawne dokonanie przekształcenia

    Given kuerunki 201, 202, 203 przeniesiono do instytucji "A"
    Given kuerunki 204 zostaną zlikwidowane
    Given całkowity postęp przekształcenia wynosi 100%

    Then weryfikacja zasad dla przeniesienia kierunków jest poprawna
    When zakończamy przekształcenie
    Then na historia kierunków 201, 202, 203 istnieje nowy wpisa o przeniesieniu do instytucji "A"
    Then w histori kierunków 204 status zmienił się na zlikwidowany
    Then na wykazie Instutucji Szkolnicta Wyższego i Nauki insytucja "B" ma status przekształcona
    Then w szczegułach wykazu Instutucji Szkolnicta Wyższego i Nauki insytucja "B" została włączona do "A"
    Then przekształcenie znika z listy przekształceń


  # TODO kolejne scenariusze dla zamknięcia przekształcenia

  Scenario: kierunek został zamknięty przed zamknięciem przekształcenia

  Scenario: kierunek został zamknięty z datą po dokonaniu przekształcenia

  Scenario: zakończenie przeksztalcenia jest niemożliwe kiedy zasady poprawności nie są spoełnione



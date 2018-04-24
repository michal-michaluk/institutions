Feature: rozpoczęcie procesu przekształceń

  rozpoczęcie włączenie [pan z ministerstwa]:
  - wybór instytucji podlegających przekształceniu
  - wybrnanie typu przekształcenia (połączenie, włączenie i wyodrębnienie)
  - (opcjonalnie) utworzenie nowej instytyucji jako docelowej
  - wskazanie (docelowej) instytucji, która pozostanie po przekształceniu
  - przekazanie informacji do zdefiniowania zadań [harmonogramy] (pracownicy z dowolnej instytucji mogą dysponować tymi bytami)

  # TODO sformułowane dla połączenie, wyodrębnienie


  Scenario: włączeniea instytucji
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

  # TODO doprecyzuj scenariusze:

  Scenario: połączenie instytucji

  Scenario: wyodrębnienie instytucji

  Scenario: weryfikacja dat nie przebiegła pomyślnie

  Scenario: pracownik z instytucji B już nie pracuje (zadanie w harmonogramach pójdzie w dev null)

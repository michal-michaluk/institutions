Feature: rozpoczęcie procesu przekształceń

  rozpoczęcie włączenie [pan z ministerstwa]:
  - wybrnanie typu przekształcenia (połączenie, włączenie i wyodrębnienie)
  - wybór instytucji podlegających przekształceniu
  - (opcjonalnie) utworzenie nowej instytyucji jako docelowej
  - wskazanie (docelowej) instytucji, która pozostanie po przekształceniu
  - zweryfikowanie dat
  - zweryfikowanie typów instytucji
  - zweryfikowanie decyzji
  - przekazanie informacji do zdefiniowania zadań [harmonogramy] (pracownicy z dowolnej instytucji mogą dysponować tymi bytami)

  # TODO sformułowane dla połączenie, wyodrębnienie


  Scenario: włączeniea instytucji
    Given instytucji "A"
    Given instytucji "B"

    Given walidacja decyzji (w tym dat i instytucji) przeszła poprawnie
    When włączamy instytucję "B" do instytucji "A"
    Then w tym przekształceniu instytucja "A" jest instucucją docelową
    Then weryfikacja zasad dla dat jest ok

    Then instytucja B ma status "W trakcie przekształcenia" na wykazie Instutucji Szkolnictwa Wyższego i Nauki
    And zadanie dla pracowników z instytucjia "A" zostało zdefiniowane w harmonogramach
    And zadanie dla pracowników z instytucjia "B" zostało zdefiniowane w harmonogramach
    And przekształcenie pojawiło się na liście przekształceń
    And postęp przekształcenia zawiera informację ""

  # TODO doprecyzuj scenariusze:

  Scenario: połączenie instytucji

  Scenario: wyodrębnienie instytucji

  Scenario: weryfikacja dat nie przebiegła pomyślnie

  Scenario: pracownik z instytucji B już nie pracuje (zadanie w harmonogramach pójdzie w dev null)

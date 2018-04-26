Feature: zarządzanie kierunkami

  zarządzanie podległymi bytami:
  [pan z ministerstwa]
  - pobranie wykazu kierunków
  - wskazanie kierunków, które będą w docelowej instytucji
  [panie z dziekanatu]
  - pobranie zależnych bytów (wykaz pracowników, wykaz dorobku naukowego ...)
  - wskazanie zależnych bytów, które należą do docelowej instytucji
  - łączenie dziekanatów: określenie

  - weryfikacja czy wszystkie kierunki są rozdzielone lub zamknięte
  - weryfikacja czy wszystkie byty zależne są rozdzielone lub zamknięte

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

  Scenario: kierunki zostały podzielone poprawnie
    Then wszystkie kierunki z "A" pozostają po przekształceniu
    When przypisujemy kierunki z instytucji "B" do docelowej instytucji "A":
      | idKierunku | nazwa               |
      | 201        | Matematyka poz. 1.  |
      | 202        | Informatyka poz. 1. |
      | 203        | Informatyka poz. 2. |
    # na liście są tylko aktywne kierunki
    Then po przekształceniu dla kierunków: 201, 202, 203 zmieni się instytucja z "B" na "A"
    Then postęp przekształcenia wynosi 75% dla kierunków
    When likwidujemy kierunki z instytucji "B", przenosząc studentów na kierunki z instytucji "A":
      | idKierunku | nazwa              | docelowyKierynek | nazwaDocelowego    |
      | 204        | Matematyka poz. 2. | 101              | Matematyka poz. 2. |
    # na liście są tylko aktywne kierunki
    Then po przekształceniu kierunki: 204 będą zlikwidowane
    Then postęp przekształcenia wynosi 100% dla kierunków
    Then w przekształceniu dla kierunków 4 zmieni się stan na "zlikwiodowany"
    And żadne zmiany nie zostały jeszcze naniesione na moduł kierunków

    Given całkowity postęp przekształcenia wynosi 100%
    Then weryfikacja zasad dla przeniesienia kierunków jest poprawna


  Scenario: duplikaty na kierunkach po przekształceniu
    Given instytucja A ma kierunki:
      | idKierunku | nazwa              |
      | 101        | Matematyka poz. 1. |

    Then wszystkie kierunki z "A" pozostają po przekształceniu
    When przypisujemy kierunki z instytucji "B" do docelowej instytucji "A":
      | idKierunku | nazwa              |
      | 201        | Matematyka poz. 1. |
    Then weryfikacja zasad dla przeniesienia kierunków znalazła duplikacje


  Scenario: rozdział kierunków przy weodrębnieniu
    # TODO więcej scenariusze

Feature: proces przekształceń

  rozpoczęcie przekształcenia [pan z ministerstwa]:
  - wybór instytucji podlegających przekształceniu
  - wybrnanie typu przekształcenia (połączenie, włączenie i wyodrębnienie)
  - (opcjonalnie) utworzenie nowej instytyucji jako docelowej
  - wskazanie (docelowej) instytucji, która pozostanie po przekształceniu
  - przekazanie informacji do zdefiniowania zadań [harmonogramy] (pracownicy z dowolnej instytucji mogą dysponować tymi bytami)

  zarządzanie podległymi bytami:
  [pan z ministerstwa]
  - pobranie wykazu kierunków
  - wskazanie kierunków, które będą w docelowej instytucji
  [panie z dziekanatu]
  - pobranie zależnych bytów (wykaz pracowników, wykaz dorobku naukowego ...)
  - wskazanie zależnych bytów, które należą do docelowej instytucji
  - łączenie dziekanatów: określenie

  zamkniecie przekształcenia
  - weryfikacja czy wszystkie kierunki są rozdzielone lub zamknięte
  - weryfikacja czy wszystkie byty zależne są rozdzielone lub zamknięte
  ** przy założeniu, że zmiany dokonują się dopiero na moment zamknięcia przekształcenia:
  - delegacja do systemów zależnych instrukcji jak rozdysponować ich byty

  lista przekształceń [read model]

  postęp przekształcenia [read model]:
  - weryfikacja postępu przy rozdzielaniu kierunków
  - weryfikacja postępu przy rozdzielaniu ...


  historia kierunku [read model]:
  - ciągłość jest zachowana
  - widoczny jest wpis z przekształceniem

  weryfikacja innych bytów...

  Background:
    Given instytucji "A"
    Given instytucji "B"

  Scenario: kompletny proces włączeniea instytucji

    When włączamy instytucję "B" do instytucji "A"
    Then w tym przekształceniu instytucja "A" jest instucucją docelową
    Then weryfikacja zasad dla dat jest ok

    Then instystucja B ma status "Wtrakcie przekształcenia" na wykazie Instutucji Szkolnictwa Wyższego i Nauki
    And zadanie dla pracowników z instytucjia "A" zostało zdewiniowane w harmonogramach
    And zadanie dla pracowników z instytucjia "B" zostało zdewiniowane w harmonogramach
    And przekształcenie pojawiło się na liście przekształceń
    And postęp przekształcenia zawiera informację ""
    # pracownik z instytucji B już nie pracuje

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
    Then postęp przekształcenia wynosi 100% dla kierunków
    Then po przekształceniu kierunki: 204 będą zlikwidowane
    And żadne zmiany nie zostały jeszcze naniesione na moduł kierunków

    Given całkowity postęp przekształcenia wynosi 100%
    Then weryfikacja zasad dla przeniesienia kierunków jest poprawna
    When zakończamy przekształcenie
    Then na historia kierunków 201, 202, 203 istnieje nowy wpisa o przeniesieniu do instytucji "A"
    Then w histori kierunków 204 status zmienił się na zlikwidowany
    Then na wykazie Instutucji Szkolnicta Wyższego i Nauki insytucja "B" ma status przekształcona
    Then w szczegułach wykazu Instutucji Szkolnicta Wyższego i Nauki insytucja "B" została włączona do "A"
    Then przekształcenie znika z listy przekształceń

  # TODO doprecyzuj scenariusze:

  Scenario: połączenie

  Scenario: wyodrębnienie

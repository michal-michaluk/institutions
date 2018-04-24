Feature: system weryfikuje poprawność przenoszonych kierunkow

  weryfikacja kirunków:
  - nie możemy przenieść kierunku jeśli docelowa instytucja już taki prowadzi
  - daty uruchomienia kierunku są po dokonaniu przekształcenia

    # weryfikacja poprawności kierunków
  Scenario: weryfikacja duplikacji kierunków ok
    Given kierunek 101 "Matematyka" profil "ogólne" poziom "1" w instytucji "A"
    Given kierunek 201 "Matematyka" profil "ogólne" poziom "2" w instytucji "B"
    When w docelowej instytucji "A" pozostają kierunki 101, 201
    Then weryfikacja jest ok

  Scenario: weryfikacja duplikacji kierunków NOK
    Given kierunek 101 "Matematyka" profil "ogólne" poziom "1" w instytucji "A"
    Given kierunek 201 "Matematyka" profil "ogólne" poziom "1" w instytucji "B"
    When w docelowej instytucji "A" pozostają kierunki 101, 201
    Then weryfikacja zwraca komunikat: "duplikacja kierunków Matematyka poziom 1: 101, 201"

    # TODO weryfikacja poprawności dat
Feature: Weryfikacja statusów i typów instytucji podlegających przekształceniu


  Scenario Outline: Sprawdzamy czy status instytucji pozwala na przekształcenie
    Given Instytucja "A" ma status "<status>"
    When Sprawdzamy status instytucji "A"
    Then rezultat walidacji to "<result>" a informacja to "<message>"
    Examples:
      | status            | result | message                     |
      | OPEN              | true   |                             |
      | LIQUIDATED        | false  | Instytucja nie jest aktywna |
      | IN_LIQUIDATION    | false  | Instytucja nie jest aktywna |
      | IN_TRANSFORMATION | false  | Instytucja nie jest aktywna |
      | TRANSFORMED       | false  | Instytucja nie jest aktywna |


  Scenario Outline: Sprawdzamy czy rodzaj instytucji pozwala na przekształcenie
    Given Instytucja "A" ma rodzaj "<rodzaj>"
    When Sprawdzamy rodzaj instytucji "A"
    Then rezultat walidacji to "<result>" a informacja to "<message>"
    Examples:
      | rodzaj         | result | message                                          |
      | PUBLIC         | true   |                                                  |
      | NOT_PUBLIC     | true   |                                                  |
      | ECCLESIASTICAL | false  | Rodzaj instytucji nie pozwala na przekształcenie |
      | SCIENTIST      | false  | Rodzaj instytucji nie pozwala na przekształcenie |


  Scenario Outline: Instytucje biorące udział w przekształceniu musza być tego samego rodzaju
    Given Instytucja "A" ma rodzaj "<rodzajA>"
    Given Instytucja "B" ma rodzaj "<rodzajB>"
    When Porównujemy rodzaje instytucji
    Then rezultat walidacji to "<result>" a informacja to "<message>"
    Examples:
      | rodzajA    | rodzajB    | result | message                                                 |
      | PUBLIC     | PUBLIC     | true   |                                                         |
      | NOT_PUBLIC | PUBLIC     | false  | niemożna połączyć instytucji publicznej i niepublicznej |
      | NOT_PUBLIC | NOT_PUBLIC | true   |                                                         |
      | PUBLIC     | NOT_PUBLIC | false  | niemożna połączyć instytucji publicznej i niepublicznej |


  Scenario:Większa ilość instytucji biorąca udział w przekształceniu
    Given Instytucja "A" ma rodzaj "NOT_PUBLIC"
    Given Instytucja "B" ma rodzaj "NOT_PUBLIC"
    Given Instytucja "C" ma rodzaj "PUBLIC"
    When Porównujemy rodzaje instytucji
    Then rezultat walidacji to "false" a informacja to "niemożna połączyć instytucji publicznej i niepublicznej"

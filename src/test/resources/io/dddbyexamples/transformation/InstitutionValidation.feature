Feature:

  Scenario Outline: Sprawdzamy czy status instytucji pozwala na przekształcenie
    Given Instytucja A ma status "<status>"
    When Sprawdzamy status instytucji
    Then Rezultat walidacji statusu to "<result>"  i informacja "<message>"
    Examples:
      | status            | result | message                     |
      | OPENING           | true   |                             |
      | LIQUIDATED        | false  | Instytucja nie jest aktywna |
      | IN_LIQUIDATED     | false  | Instytucja nie jest aktywna |
      | IN_TRANSFORMATION | false  | Instytucja nie jest aktywna |
      | TRANSFORMED       | false  | Instytucja nie jest aktywna |


  Scenario Outline: Instytucje biorące udział w przekształceniu musza być tego samego rodzaju
    Given Instytucja A jest "<rodzajA>"
    Given Instytucja B jest "<rodzajB>"
    When Porównujemy rodzaje instytucji
    Then rezultat walidacji rodzaju to "<result>" i informacja "<message>"
    Examples:
      | rodzajA    | rodzajB    | result | message                                                 |
      | PUBLIC     | PUBLIC     | true   |                                                         |
      | NOT_PUBLIC | PUBLIC     | false  | niemożna połączyć instytucji publicznej i niepublicznej |
      | NOT_PUBLIC | NOT_PUBLIC | true   |                                                         |
      | PUBLIC     | NOT_PUBLIC | false  | niemożna połączyć instytucji publicznej i niepublicznej |


  Scenario:Większa ilość instytucji biorąca udział w przekształceniu
    Given Instytucja A jest niepublicyna
    Given Instytucja B jest niepublicyna
    Given Instytucja C jest publicyna
    When Porównujemy rodzaje instytucji
    Then rezultat walidacji to false i informacja niemożna połączyć instytucji publicznej i niepublicznej


  Scenario Outline: Sprawdzamy czy rodzaj instytucji pozwala na przekształcenie
    Given Instytucja A ma rodzaj "<rodzaj>"
    When Sprawdzamy rodzaj instytucji
    Then rezultat walidacji to "<result>" a message to "<message>"
    Examples:
      | rodzaj   | result | message                                          |
      | PUBLIC    | true   |                                                  |
      | NOT_PUBLIC | true   |                                                  |
      | ECCLESIASTICAL    | false  | Rodzaj instytucji nie pozwala na przekształcenie |
      | SCIENTIST      | false  | Rodzaj instytucji nie pozwala na przekształcenie |


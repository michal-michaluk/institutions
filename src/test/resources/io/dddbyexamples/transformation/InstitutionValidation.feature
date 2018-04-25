Feature:

  Scenario Outline: Sprawdzamy czy status instytucji pozwala na przekształcenie
    Given Instytucja A ma status <status>
    When Sprawdzamy status instytucji
    Then Rezultat walidacji to <result>  i informacja <message>
    Examples:
      | status            | result | message                     |
      | działająca        | true   |                             |
      | zlikwidowana      | false  | Instytucja nie jest aktywna |
      | w likwidacji      | false  | Instytucja nie jest aktywna |
      | w przekształceniu | false  | Instytucja nie jest aktywna |
      | przekształcona    | false  | Instytucja nie jest aktywna |


  Scenario Outline: Instytucje biorące udział w przekształceniu musza być tego samego rodzaju
    Given Instytucja "A" jest <rodzajA>
    Given Instytucja "B" jest <rodzajB>
    When Porównujemy rodzaje instytucji
    Then rezultat walidacji to <result> i informacja <message>
    Examples:
      | rodzajA      | rodzajB      | result | message                                                 |
      | publiczna    | publiczna    | true   |                                                         |
      | niepubliczna | publiczna    | false  | niemożna połączyć instytucji publicznej i niepublicznej |
      | niepubliczna | niepubliczna | true   |                                                         |
      | publiczna    | niepubliczna | false  | niemożna połączyć instytucji publicznej i niepublicznej |


  Scenario:Większa ilość instytucji biorąca udział w przekształceniu
    Given Instytucja "A" jest niepublicyna
    Given Instytucja "B" jest niepublicyna
    Given Instytucja "C" jest publicyna
    When Porównujemy rodzaje instytucji
    Then rezultat walidacji to false i informacja niemożna połączyć instytucji publicznej i niepublicznej


  Scenario Outline: Sprawdzamy czy rodzaj instytucji pozwala na przekształcenie
    Given Instytucja A ma rodzaj <instytucja>
    When Sprawdzamy rodzaj instytucji
    Then rezultat walidacji to <result> a message to <message>
    Examples:
      | instytucja   | result | message                                          |
      | publiczna    | true   |                                                  |
      | niepubliczna | true   |                                                  |
      | kościelna    | false  | Rodzaj instytucji nie pozwala na przekształcenie |
      | naukowa      | false  | Rodzaj instytucji nie pozwala na przekształcenie |


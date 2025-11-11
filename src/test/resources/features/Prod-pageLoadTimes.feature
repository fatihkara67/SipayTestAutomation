@widget @spy @last-prod @last
Feature: Time logs Scenarios

#  Scenario: Login page
#    Given The user go to 'prod-fletum' and measure load time
#
#  Scenario: Entry page
#    Given The user opens 'prod-fletum' environment
#    And   The User inputs a valid username "sahaUser"
#    And   The User inputs a valid password "sahaPassword"
#    Then The user click submit button and mesaure time

  Scenario: Deal Overview Page Load
    Given The user go to 'prod-fletum' and measure load time
    And   The User inputs a valid username "sahaUser"
    And   The User inputs a valid password "sahaPassword"
    Then The user click submit button and mesaure time
    Then The user click 'Digital Asset' in quick access and measure time
    Then The user click edit item button and measure time
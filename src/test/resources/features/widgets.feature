@widget
Feature: Widget Scenarios
  Background:
#    Given The user get data

  Scenario: Scenario 1
    Given The user send widget36 request
    Given The user send widget37 request
    Then The user verify scenario1

  Scenario: Scenario 2
    Given The user send widget36 request
    Given The user send widget38 request
    Then The user verify scenario2

  Scenario: Scenario 3
    Given The user send widget36 request
    Given The user send widget39 request
    Then The user verify scenario3

  Scenario: Scenario 4
    Given The user send widget38 request
    Given The user send widget39 request
    Then The user verify scenario4

#  Scenario: Scenario 5
#    Given The user send widget3 request
#    Given The user send widget40 request
#    Then The user verify scenario5
#
#  Scenario: Scenario 6
#    Given The user send widget4 request
#    Given The user send widget41 request
#    Then The user verify scenario6


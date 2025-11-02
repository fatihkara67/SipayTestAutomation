@preprod
Feature: Search Page Scenarios

  Background:
    Given The user go to 'test-app' environment
    Given The user login
      | username | TestSipay  |
      | password | Sipay2025. |

  Scenario: Deal Status Filter and reset button
    When The user select 'Prospect' in deal status filter
    Then The user verify deal status filter with 'Prospect'
    When The user click deal status reset button
    And The user verify Reset button func for deal status filter
    When The user select 'Pitched' in deal status filter
    Then The user verify deal status filter with 'Pitched'

  Scenario: Create Date Sorting
    When The user enter 'Test' to search input
    When The user click create date sort button
    Then The user verify create date ascending sort
    When The user click create date sort button
    Then The user verify create date descending sort

  Scenario: Create date filter and reset
    When The user select date filter
    Then The user verify create date filter
    When The user click create date reset button
    Then The user verify Reset button func for create date filter

  Scenario: Save New Record And Go to related record
    When The user click create new record button
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user click 'Kaydet' button
    Then The user verify warning 'Durum ProspectDraft olarak güncellendi'
    When The user navigate to 'https://sipayapp.efectura.com/'
    Then The user verify record is created
    When The user click related record button at row 0
    When The user go to other tab
    Then The user verify 'Prospect Formu' form is open

  Scenario: Pagination Control
    When The user enter 'utku' to search input
    Then The user verify previous page button passive
    Then The user verify next button active
    When The user click next page button
    Then The user verify previous page button active
    When The user go to last page
    Then The user verify next button passive

  Scenario: Duplicate Form Control
    When The user click create new record button
    When The user fill inputs prospect form sales and field
    When The user fill select prospect form sales and field
    When The user click 'Kaydet' button
    When The user wait 5 second
    When The user enter 'Test notu' into explanation in 'prospect'
    When The user click 'Kaydet' button
    When The user navigate to 'https://sipayapp.efectura.com/'
    Then The user verify duplicate

  Scenario: Add a search chip and refresh results
    When The user select 'ID' for search area
    Then The user verify 'ID' added

  Scenario: Toggle "Arama Alanları" panel and verify counter
    When The user click "Arama Alanları" button
    Then The user verify search area count "Arama Alanları"

  Scenario: Language switch to EN keeps state
    When The user enter 'Test' to search input
    When The user select 'Prospect' in deal status filter
    When The user switch language to 'EN'
    Then The user verify page language switched to 'EN'
    When The user switch language to 'TR'
    Then The user verify page language switched to 'TR'




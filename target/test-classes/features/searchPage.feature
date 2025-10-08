@spy @last
Feature: Search Page Scenarios

  Background:
    Given The user go to 'test-app' environment
    Given The user login
      | username | TestSipay  |
      | password | Sipay2025. |

#  Scenario: Search Input
#    When The user enter 'Test' to search input
#    Then The user verify record titles with 'Test'

  Scenario: Deal Status Filter and reset button
    When The user select 'Prospect' in deal status filter
    Then The user verify deal status filter with 'Prospect'
    When The user click deal status reset button
    And The user verify Reset button func for deal status filter
    When The user select 'Pitched' in deal status filter
    Then The user verify deal status filter with 'Pitched'

#  Scenario: Deal Status Filter 2
#    When The user select 'Pitched' in deal status filter
#    Then The user verify deal status filter with 'Pitched'
#
#  Scenario: Deal Durumu Reset Filter
#    When The user select 'Prospect' in deal status filter
#    When The user click deal status reset button
#    And The user verify Reset button func for deal status filter

  Scenario: Create Date Sorting
    When The user enter 'Test' to search input
    When The user click create date sort button
    Then The user verify create date ascending sort
    When The user click create date sort button
    Then The user verify create date descending sort

  Scenario: Create date filter and reset
    When The user select date filter 7 9 2025
    Then The user verify create date filter 7 9 2025
    When The user click create date reset button
    Then The user verify Reset button func for create date filter

#  Scenario: Create date filter reset button
#    When The user enter 'Sena' to search input
#    When The user select date filter 14 8 2025
#    When The user click create date reset button
#    Then The user verify Reset button func for create date filter

  Scenario: Save New Record And Go to related record
    When The user click create new record button
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user click 'Kaydet' button
    Then The user verify warning 'Durum ProspectDraft olarak güncellendi'
    When The user navigate to 'https://sipayapp.efectura.com/'
#    When The user fill and save the form
    Then The user verify record is created
    When The user click related record button at row 1
    When The user go to other tab
    Then The user verify 'Lead Formu' form is open

#  Scenario: Go To Related Record button
#    When The user select 'Prospect' in deal status filter
#    When The user click related record button at row 1
#    When The user go to other tab
#    Then The user verify 'Lead Formu' form is open

#  Scenario: Empty Required Area Control On Submit Form
#    When The user click create new record button
#    When The user click 'Tamamla' button
#    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'

  Scenario: Pagination Control
    When The user enter 'test' to search input
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



  Scenario: Remove a search chip and refresh results
    Given The user sees search chips 'Üye İsmi, İsim, Soyadı, Üye İşyeri Tabela Adı, Cep Telefonu'
    When The user remove chip 'İsim'
    Then The user verify chip 'İsim' is not visible
    And The user verify results are updated accordingly

  Scenario: Add a search chip and refresh results
    When The user select 'ID' for search area
    Then The user verify 'ID' added

  Scenario: Toggle "Arama Alanları" panel and verify counter
    When The user click "Arama Alanları" button
    Then The user verify search area count "Arama Alanları"
#    When The user close "Arama Alanları" panel
#    Then The user verify the panel is hidden

#  Scenario: Pagination - Single page state
#    Given The user applies a filter returning a single page
#    Then The user verify previous page button passive
#    And The user verify next button passive

  Scenario: Language switch to EN keeps state
    When The user enter 'Test' to search input
    When The user select 'Prospect' in deal status filter
    When The user switch language to 'EN'
    Then The user verify page language switched to 'EN'
#    And The user verify search input and applied filters persist




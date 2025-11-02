@preprod
Feature: Assigned Records Page

  Background:
    Given The user go to 'test-app' environment
    Given The user login
      | username | TestSipay  |
      | password | Sipay2025. |


  Scenario: Assign Not To Me Listing Case
    When The user click create new record button
    When The user select 'Emre Uzun' as salesRep
    When The user fill select prospect form sales and field
    When The user select2 'Ulaşılamadı' in 'prospectDurum'
    When The user fill inputs prospect form sales and field
    Given The user logout
    Given The user login
      | username | merveakan  |
      | password | Sipay2025. |
    When The user click assigned records tab
    Then The user verify table no result

  Scenario: Assign To Me Listing Case
    When The user click create new record button
    When The user select 'Merve Akan' as salesRep
    When The user fill select prospect form sales and field
    When The user select2 'Ulaşılamadı' in 'prospectDurum'
    When The user fill inputs prospect form sales and field
    Given The user logout
    Given The user login
      | username | merveakan  |
      | password | Sipay2025. |
    When The user click assigned records tab
    Then The user verify table with record

  Scenario: Records Not I Created Listing Case
    When The user click create new record button
    When The user select 'Merve Akan' as salesRep
    When The user fill select prospect form sales and field
    When The user select2 'Ulaşılamadı' in 'prospectDurum'
    When The user fill inputs prospect form sales and field
    Given The user logout
    Given The user login
      | username | merveakan  |
      | password | Sipay2025. |
    When The user click assigned records tab
    When The user click assignedOrCreatedCheckbox
    Then The user verify table no result for created case

  Scenario: Records I Created Listing Case
    When The user click create new record button
#    When The user select 'Test Sipay' as salesRep
    When The user fill select prospect form sales and field
    When The user select2 'Ulaşılamadı' in 'prospectDurum'
    When The user fill inputs prospect form sales and field
    When The user click 'Kaydet' button
    Then The user verify warning 'Durum ProspectDraft olarak güncellendi'
    When The user navigate to 'https://sipayapp.efectura.com/'
    When The user click assigned records tab
    When The user click assignedOrCreatedCheckbox
    Then The user verify table with record

  Scenario: Assigned Records Deal Status Filter 1
    When The user click create new record button
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user click 'Kaydet' button
    Then The user verify warning 'Durum ProspectDraft olarak güncellendi'
    When The user navigate to 'https://sipayapp.efectura.com/'
    When The user click assigned records tab
    When The user select 'ProspectDraft' in deal status filter
    Then The user verify deal status filter with 'ProspectDraft'

  Scenario: Assigned Records Create date filter
    When The user click assigned records tab
    When The user select date filter
    Then The user verify create date filter

  Scenario: Assigned Records Create date and Deal Status filter
    When The user click assigned records tab
    When The user select 'ProspectDraft' in deal status filter
    When The user select date filter
    Then The user verify create date filter
    Then The user verify deal status filter with 'ProspectDraft'
    When The user click create date reset button
    Then The user verify Reset button func for create date filter

#  Scenario: Assigned Records Create date filter reset button
#    When The user click assigned records tab
#    When The user enter 'Sena' to search input
#    When The user select date filter
#    When The user click create date reset button
#    Then The user verify Reset button func for create date filter

  Scenario: Assigned Records Go To Related Record button
    When The user click assigned records tab
    When The user select 'ProspectDraft' in deal status filter
    When The user click related record button at row 0
    When The user go to other tab
    Then The user verify 'Prospect Formu' form is open

  Scenario: Assigned Records Pagination Control
    When The user click assigned records tab
    When The user enter 'fatih' to search input
    Then The user verify previous page button passive
    Then The user verify next button active
    When The user click next page button
    Then The user verify previous page button active
    When The user go to last page
    Then The user verify next button passive
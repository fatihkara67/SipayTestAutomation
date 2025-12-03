@spy @last-prod @last @deneme
Feature: Prod Field And Sales Cases

  Background:
    Given The user go to 'prod-app' environment
    Given The user login
      | username | saha_satis |
      | password | Sipay2025. |


  Scenario: Prod Sales And Field End to End
    When The user go to 'Ziyaret' on navbar
    When The user click 'Tamamla' button
    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
    When The user enter 'KARA' into 'LastName'
    When The user click 'Temizle' button
    Then The user verify form inputs clear
    When The user click 'hasPosUsage'
    Then The user verify 'otherPosInfo' is displayed
    When The user fill select prospect form sales and field
    When The user select2 'Olumlu' in 'communicationTracking'
    When THe user fill date 'newAppointment' in form
#    When The user select date 14 10 2025 in 'newAppointment'
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    When The user wait 1 second
    Then The user verify warning 'Durum Prospect olarak güncellendi'
    When The user keep prospect data
    Then The user verify 'Fırsat Formu' form is open
    When The user open 'Ziyaret' info in form
    Then The user verify old values
    Then The user verify prospect values
    When The user open 'Ziyaret' info in form
    When The user click 'Tamamla' button
    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
    When The user wait 3 second
    When The user select2 'Kiralama' in 'deviceOwnership'
    When The user fill select prospect form sales and field
    When The user select2 '350' in 'deviceMonthlyFee'
    When The user select2 'Hugin' in 'deviceBrand'
    When The user fill inputs prospect form sales and field
    When The user click 'Cihaz Ekle' button
    When The user wait 3 second
    When The user enter 'Geçici Adres' into 'installationAddress'
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum Lead olarak güncellendi'
    When The user keep lead data
    Then The user verify 'Satış Formu' form is open
    When The user open 'Fırsat' info in form
    Then The user verify old values
    When The user open 'Fırsat' info in form
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum Pitched olarak güncellendi'
    When The user keep pitched data
    Then The user verify 'Saha Satış Kontrat Formu' form is open
    When The user open 'Satış Bilgileri' info in form
    Then The user verify old values
    When The user take form id
    Given The user opens 'prod-fletum' environment
    And   The User inputs a valid username "sahaUser"
    And   The User inputs a valid password "sahaPassword"
    And   The User clicks the Submit button
    Then  The User waits until the Analysis element is visible with a timeout of 120 seconds
    When The user navigate the deal item
    And The user clicks "Deal - Kişi İlişkisi" tab
    And The user select "Evet" in "IsAssociated" select filter
    Then The user verify "İlişkili" select filter with value "Evet" in "association-table"
    And The user clicks "Deal - Üye İşyeri İlişkisi" tab
    And The user select "Evet" in "IsAssociated" select filter
    Then The user verify "İlişkili" select filter with value "Evet" in "association-table"
    Given The user assert all


  Scenario: Prod-SalesAndField-Search-LastUpdatedList and Form Navigation
    When The user go to 'Ziyaret' on navbar
    When The user fill select prospect form sales and field
    When The user select2 'Olumlu' in 'communicationTracking'
    When THe user fill date 'newAppointment' in form
#    When The user select date 14 10 2025 in 'newAppointment'
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    When The user wait 1 second
    Then The user verify warning 'Durum Prospect olarak güncellendi'
    When The user navigate to 'https://crmapp.spwgpf.com/'
    Then The user verify last updated list
    When The user search created form
    When The user click related record button at row 0
    When The user go to other tab
    Then The user verify 'Fırsat Formu' form is open



  Scenario: Prod-SalesAndField-Search-DealStatus Filter and Reset
    When The user select 'Ziyaret' in deal status filter
    Then The user verify deal status filter with 'Ziyaret'
    When The user click deal status reset button
    And The user verify Reset button func for deal status filter
#    When The user select 'Fırsat' in deal status filter
#    Then The user verify deal status filter with 'Fırsat'


  Scenario: Prod-SalesAndField-Search-CreateDateSorting,Filter and Reset
    When The user enter 'a' to search input
    When The user click create date sort button
    Then The user verify create date ascending sort
    When The user click create date sort button
    Then The user verify create date descending sort
    When The user select date filter
    Then The user verify create date filter
    When The user click create date reset button
    Then The user verify Reset button func for create date filter


  Scenario: Prod-SalesAndField-AssignedRecords-DealStatus Filter and Reset
    When The user click assigned records tab
    When The user select 'Ziyaret' in deal status filter
    Then The user verify deal status filter with 'Ziyaret'
    When The user click deal status reset button
    And The user verify Reset button func for deal status filter


  Scenario: Prod-SalesAndField-AssignedRecords-CreateDateSorting,Filter and Reset
    When The user click assigned records tab
    When The user enter 'a' to search input
    When The user click create date sort button
    Then The user verify create date ascending sort
    When The user click create date sort button
    Then The user verify create date descending sort
    When The user select date filter
    Then The user verify create date filter
    When The user click create date reset button
    Then The user verify Reset button func for create date filter


#  Scenario: Prod-SalesAndField-AssignedRecords-Pagination Control
#    When The user click assigned records tab
#    When The user select 'Ziyaret' in deal status filter
#    Then The user verify previous page button passive
#    Then The user verify next button active
#    When The user click next page button
#    Then The user verify previous page button active
#    When The user go to last page
#    Then The user verify next button passive

  Scenario: Prod-SalesAndField-AssignedRecords-Form Navigation
    When The user click assigned records tab
    When The user select 'Ziyaret' in deal status filter
    When The user get member name
    When The user click related record button at row 0
    When The user go to other tab
    Then The user verify 'Fırsat Formu' form is open
    When The user open 'Ziyaret' info in form
    Then The user verify member name

  Scenario: Prod-SalesAndField-Randevu
    When The user go to 'Ziyaret' on navbar
    When The user fill select prospect form sales and field
    When The user select2 'Olumlu' in 'communicationTracking'
    When THe user fill date 'newAppointment' in form
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    When The user wait 1 second
    Then The user verify warning 'Durum Prospect olarak güncellendi'
    When The user navigate to 'https://crmapp.spwgpf.com/'
    When The user click randevular tab
    Then The user verify randevu list contains the form

  Scenario: Prod-SalesAndField-Search-Other Records
    When The user select 'Ziyaret' in deal status filter
    When The user click other records button
    When The user get member name
    When The user click create new record button in table
    When The user go to other tab
    Then The user verify 'Ziyaret Formu' form is open
    Then The user verify member name

  Scenario: Prod-SalesAndField-Search-Sahada Satış Records
    When The user select 'Ziyaret' in deal status filter
    When The user get member name
    When The user click create new record button in table
    When The user go to other tab
    Then The user verify 'Ziyaret Formu' form is open
    Then The user verify member name


  Scenario: Prod-SalesAndField-AssignedRecords-Assign Not To Me Listing Case
    When The user click create new record button
    When The user select 'Saha SAtış 2 22' as salesRep
#    When The user fill and save the form
    When The user fill select prospect form sales and field
    When The user select2 'Olumlu' in 'communicationTracking'
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    When The user navigate to 'https://crmapp.spwgpf.com/'
    When The user click assigned records tab
    Then The user verify table no result

  Scenario: Prod-SalesAndField-AssignedRecords-Assign To Me Listing Case
    When The user click create new record button
    When The user select 'Saha SAtış 2 22' as salesRep
    When The user fill select prospect form sales and field
    When The user select2 'Olumlu' in 'communicationTracking'
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    Given The user logout
    Given The user login
      | username | SAHASATIS22 |
      | password | Sipay2025.  |
    When The user click assigned records tab
    Then The user verify table with record


  Scenario: Prod-SalesAndField-AssignedRecords-Records Not I Created Listing Case
    When The user click create new record button
    When The user select 'Saha SAtış 2 22' as salesRep
    When The user fill select prospect form sales and field
    When The user select2 'Olumlu' in 'communicationTracking'
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    Given The user logout
    Given The user login
      | username | SAHASATIS22 |
      | password | Sipay2025.  |
    When The user click assigned records tab
    When The user click assignedOrCreatedCheckbox
    Then The user verify table no result for created case


  Scenario: Prod-SalesAndField-AssignedRecords-Records I Created Listing Case
    When The user click create new record button
    When The user fill select prospect form sales and field
    When The user select2 'Olumlu' in 'communicationTracking'
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    When The user navigate to 'https://crmapp.spwgpf.com/'
    When The user click assigned records tab
    When The user click assignedOrCreatedCheckbox
    Then The user verify table with record









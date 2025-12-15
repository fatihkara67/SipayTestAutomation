@preprod
Feature: Prod Field And Sales Cases

  Background:
    Given The user go to 'test-app' environment
    Given The user login
      | username | sahasatis2 |
      | password | Sipay2025. |


    Scenario: End to End
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
    When The user open 'Satış' info in form
    Then The user verify old values
    When The user open 'Satış' info in form
    Then The user verify old values
    Given The user assert all


  Scenario: Preprod-SalesAndField-AssignedRecords-Assign Not To Me Listing Case
    When The user click create new record button
    When The user select 'SAHASATIS 22' as salesRep
#    When The user fill and save the form
    When The user fill select prospect form sales and field
    When The user select2 'Olumlu' in 'communicationTracking'
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    When The user navigate to 'https://sipayapp.efectura.com/'
    When The user click assigned records tab
    Then The user verify table no result

  Scenario: Preprod-SalesAndField-AssignedRecords-Assign To Me Listing Case
    When The user click create new record button
    When The user select 'SAHASATIS 22' as salesRep
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


  Scenario: Preprod-SalesAndField-AssignedRecords-Records Not I Created Listing Case
    When The user click create new record button
    When The user select 'SAHASATIS 22' as salesRep
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


  Scenario: Preprod-SalesAndField-AssignedRecords-Records I Created Listing Case
    When The user click create new record button
    When The user fill select prospect form sales and field
    When The user select2 'Olumlu' in 'communicationTracking'
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    When The user navigate to 'https://sipayapp.efectura.com/'
    When The user click assigned records tab
    When The user click assignedOrCreatedCheckbox
    Then The user verify table with record





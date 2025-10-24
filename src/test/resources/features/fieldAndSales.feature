@spy @last-prod @last @deneme
Feature: Field And Sales Cases

  Background:


#  Scenario: End to End
#    Given The user go to 'test-app' environment
#    Given The user login
#      | username | SAHASATIS2 |
#      | password | Sipay2025. |
#    When The user go to 'Ziyaret' on navbar
##    When The user click 'Tamamla' button
##    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
##    When The user enter 'KARA' into 'LastName'
##    When The user click 'Temizle' button
##    Then The user verify form inputs clear
#    When The user click 'hasPosUsage'
#    Then The user verify 'otherPosInfo' is displayed
#    When The user fill select prospect form sales and field
#    When The user select2 'Olumlu' in 'communicationTracking'
#    When The user select date 14 10 2025 in 'newAppointment'
#    When The user fill inputs prospect form sales and field
#    When The user click 'Tamamla' button
#    When The user wait 1 second
#    Then The user verify warning 'Durum Prospect olarak güncellendi'
#    When The user keep prospect data
#    Then The user verify 'Fırsat Formu' form is open
##    When The user open 'Ziyaret' info in form
##    Then The user verify old values
##    Then The user verify prospect values
##    When The user open 'Ziyaret' info in form
##    When The user click 'Tamamla' button
##    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
#    When The user wait 3 second
#    When The user select2 'Kiralama' in 'deviceOwnership'
#    When The user fill select prospect form sales and field
#    When The user select2 '350' in 'deviceMonthlyFee'
#    When The user select2 'Hugin' in 'deviceBrand'
#    When The user fill inputs prospect form sales and field
#    When The user click 'Cihaz Ekle' button
#    When The user wait 3 second
#    When The user click 'Tamamla' button
#    Then The user verify warning 'Durum Lead olarak güncellendi'
#    When The user keep lead data
#    Then The user verify 'Satış Formu' form is open
#    When The user open 'Fırsat' info in form
#    Then The user verify old values
#    When The user open 'Fırsat' info in form
#    When The user fill select prospect form sales and field
#    When The user fill inputs prospect form sales and field
#    When The user click 'Tamamla' button
#    Then The user verify warning 'Durum Pitched olarak güncellendi'
#    When The user keep pitched data
#    Then The user verify 'Contracting Kayıt Formu' form is open
#    When The user open 'Satış' info in form
#    Then The user verify old values
#    When The user open 'Satış' info in form
#    Then The user verify old values
#    Given The user assert all



  Scenario: Prod End to End
    Given The user go to 'prod-app' environment
    Given The user login
      | username | saha_satis |
      | password | Sipay2025. |
    When The user go to 'Ziyaret' on navbar
#    When The user click 'Tamamla' button
#    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
#    When The user enter 'KARA' into 'LastName'
#    When The user click 'Temizle' button
#    Then The user verify form inputs clear
    When The user click 'hasPosUsage'
    Then The user verify 'otherPosInfo' is displayed
    When The user fill select prospect form sales and field
    When The user select2 'Olumlu' in 'communicationTracking'
    When The user select date 14 10 2025 in 'newAppointment'
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    When The user wait 1 second
    Then The user verify warning 'Durum Prospect olarak güncellendi'
    When The user keep prospect data
    Then The user verify 'Fırsat Formu' form is open
#    When The user open 'Ziyaret' info in form
#    Then The user verify old values
#    Then The user verify prospect values
#    When The user open 'Ziyaret' info in form
#    When The user click 'Tamamla' button
#    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
    When The user wait 3 second
    When The user select2 'Kiralama' in 'deviceOwnership'
    When The user fill select prospect form sales and field
    When The user select2 '350' in 'deviceMonthlyFee'
    When The user select2 'Hugin' in 'deviceBrand'
    When The user fill inputs prospect form sales and field
    When The user click 'Cihaz Ekle' button
    When The user wait 3 second
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
    Then The user verify 'Contracting Kayıt Formu' form is open
    When The user open 'Satış' info in form
    Then The user verify old values
    When The user open 'Satış' info in form
    Then The user verify old values
    Given The user assert all

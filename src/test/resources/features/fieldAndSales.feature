@last
Feature: Field And Sales Cases

  Background:
    Given The user go to 'test-app' environment
    Given The user login
      | username | SAHASATIS2 |
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
#    When The user select 'Fiziki POS' in 'product'
    When The user fill select prospect form sales and field
    When The user select2 'Olumlu' in 'communicationTracking'
    When The user select date 14 10 2025 in 'newAppointment'
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    When The user wait 1 second
    Then The user verify warning 'Durum Prospect olarak güncellendi'
    When The user keep prospect data
    Then The user verify 'Fırsat Formu' form is open
    When The user open 'Ziyaret' info in form
#    Then The user verify old values
    Then The user verify prospect values
    When The user open 'Ziyaret' info in form
    When The user click 'Tamamla' button
    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
    Given The user assert all

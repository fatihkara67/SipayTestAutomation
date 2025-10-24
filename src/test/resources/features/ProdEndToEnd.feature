@last-prod @spy
Feature: Prod End To End Form Cases

  Background:
    Given The user go to 'prod-app' environment

  Scenario: Sales End To End
    Given The user login
      | username | TestSipay  |
      | password | Sipay2025. |
    When The user go to 'Prospect' on navbar
    When The user click 'Tamamla' button
    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
    When The user fill select prospect form sales and field
    When The user click 'Tamamla' button
    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
    When The user click 'Temizle' button
    Then The user verify form inputs clear
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user click 'Kaydet' button
    Then The user verify warning 'Durum ProspectDraft olarak güncellendi'
    Then The user verify old values

    When The user select2 'Ulaşılamadı' in 'prospectDurum'
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum Prospect olarak güncellendi'
    Then The user verify 'Lead Formu' form is open
    When The user open 'Prospect' info in form
    Then The user verify old values
    When The user open 'Prospect' info in form
    When The user click 'Tamamla' button
    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
    When The user wait 3 second
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user select2 'Manuel POS' in 'product'
    Then The user verify other attributes display
    When The user select2 'Linkle Ödeme' in 'product'
    Then The user verify other attributes display
    When The user select2 'Sanal POS' in 'product'
    Then The user verify other attributes display
    When The user select2 'Fiziki POS' in 'product'
    Then The user verify other attributes display
    When The user click 'Güncelle' button
    Then The user verify warning 'Durum LeadDraft olarak güncellendi'
    Then The user verify old values
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum Lead olarak güncellendi'
    Then The user verify 'Pitched Kayıt Formu' form is open
    When The user open 'Lead' info in form
    Then The user verify old values
    When The user open 'Lead' info in form

    When The user click 'Tamamla' button
    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When THe user fill date 'offerDate' in form
    When THe user fill date 'expectedActivationMonth' in form
    When The user click 'Güncelle' button
    Then The user verify warning 'Durum PitchedDraft olarak güncellendi'
    Then The user verify old values
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum Pitched olarak güncellendi'
    Then The user verify 'Contracting Kayıt Formu' form is open
    When The user open 'Pitched' info in form
    Then The user verify old values
    When The user open 'Pitched' info in form

    When The user click 'Tamamla' button
    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user select2 'Adi Ortaklık' in 'companyType'
#    Then The user verify required documents
    When The user upload documents
    When The user click 'Güncelle' button
    Then The user verify warning 'Durum ContractingDraft olarak güncellendi'
    When The user navigate current url
    When The user wait 3 second
    Then The user verify old values
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum Contracting olarak güncellendi'
    When The user open 'Contracting' info in form
    Then The user verify old values
    When The user open 'Contracting' info in form

    Given The user logout
    Given The user login
      | username | semasipay  |
      | password | Sipay2025. |
    When The user search created form
    When The user click related record button at row 0
    When The user go to other tab
    When The user wait 8 second
    When The user click 'Tamamla' button
    Then The user verify warning 'Lütfen tüm zorunlu alanları doldurun'
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user click 'Güncelle' button
    Then The user verify warning 'Durum OnBoardingRiskDraft olarak güncellendi'
    Then The user verify old values
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum OnboardingRisk olarak güncellendi'
    Then The user verify 'Onboarding Operation Formu' form is open
    When The user open 'Onboarding Bilgileri' info in form
    Then The user verify old values
    When The user open 'Onboarding Bilgileri' info in form

    Given The user logout
    Given The user login
      | username | operatıon_sema |
      | password | Sipay2025.     |
    When The user search created form
    When The user click related record button at row 0
    When The user go to other tab
    When The user wait 8 second
    When The user click 'Tamamla' button
    Then The user verify warning 'pleaseFillRequiredFields'
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user click 'Güncelle' button
    Then The user verify warning 'Durum OnBoardingOperationDraft olarak güncellendi'
    Then The user verify old values
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum Implementation olarak güncellendi'
    When The user navigate current url
    When The user wait 3 second
    Then The user verify old values
    Given The user assert all



  Scenario: Prod Item And Association Control
    Given The user login
      | username | TestSipay  |
      | password | Sipay2025. |
    When The user go to 'Prospect' on navbar
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user select2 'Ulaşılamadı' in 'prospectDurum'
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum Prospect olarak güncellendi'
    Then The user verify 'Lead Formu' form is open
    When The user select2 'Fiziki POS' in 'product'
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum Lead olarak güncellendi'
    Then The user verify 'Pitched Kayıt Formu' form is open
    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When THe user fill date 'offerDate' in form
    When THe user fill date 'expectedActivationMonth' in form
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum Pitched olarak güncellendi'
    Then The user verify 'Contracting Kayıt Formu' form is open

    When The user fill select prospect form sales and field
    When The user fill inputs prospect form sales and field
    When The user select2 'Adi Ortaklık' in 'companyType'
#    Then The user verify required documents
    When The user upload documents
    When The user click 'Tamamla' button
    Then The user verify warning 'Durum Contracting olarak güncellendi'
    When The user take form id
    Given The user opens 'test-fletum' environment
    And   The User inputs a valid username "sahaUser"
    And   The User inputs a valid password "sahaPassword"
    And   The User clicks the Submit button
    Then  The User waits until the Analysis element is visible with a timeout of 120 seconds
    When The user navigate the deal item
    And The user clicks "Dijital Varlıklar" tab
    And The user select "Evet" in "IsAssociated" select filter
    Then The user verify "İlişkili" select filter with value "Evet" in "association-table"
    And The user clicks "İş Ortakları" tab
    And The user select "Evet" in "IsAssociated" select filter
    Then The user verify "İlişkili" select filter with value "Evet" in "association-table"
    When The user go in the filtered item
    When The user go to other tab
    And The user clicks "Dijital İçerikler" tab
    And The user select "Evet" in "IsAssociated" select filter
    Then The user verify "İlişkili" select filter with value "Evet" in "association-table"
    Then The user verify deal and documents


  Scenario: Team Dashboard
    Given The user opens 'prod-fletum' environment
    And   The User inputs a valid username "sahaUser"
    And   The User inputs a valid password "sahaPassword"
    And   The User clicks the Submit button
    Then  The User waits until the Analysis element is visible with a timeout of 120 seconds
    When The user go to team dashboard
    When The user take screenshot

  Scenario: Prod Document Download Control
    Given The user login
      | username | semasipay  |
      | password | Sipay2025. |
    When The user select 'Contracting' in deal status filter
    When The user click related record button at row 0
    When The user go to other tab
    Then The user verify 'Onboarding Risk Formu' form is open
    When The user click uploaded document
    When The user verify document is download







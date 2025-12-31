package Efectura.stepDefs;

import Efectura.pages.BasePage;
import Efectura.pages.SearchPage;
import Efectura.utilities.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static Efectura.utilities.BrowserUtils.isElementDisplayed;

public class SearchStepDefs extends BaseStep {
    SoftAssert softAssert = new SoftAssert();

    @When("The user enter {string} to search input")
    public void theUserEnterSenaToSearchInput(String searchInput) {
        pages.searchPage().getSearchInput().sendKeys(searchInput + Keys.ENTER);
        BrowserUtils.wait(2);
    }

    @Then("The user verify record titles with {string}")
    public void theUserVerifyRecordTitlesWithSena(String searchValue) {

        System.out.println(pages.searchPage().getSearchPageAllRecordsValues().stream()
                .map(WebElement::getText).collect(Collectors.toList()));

        boolean allMatch = pages.searchPage().getRecordTitles().stream()
                .map(WebElement::getText)
                .allMatch(title -> title.toLowerCase().contains(searchValue.toLowerCase()));

//        Assert.assertTrue("Tüm Title'lar ilgili arama kelimesini içermiyor",allMatch);

    }

    @When("The user select {string} in deal status filter")
    public void theUserSelectProspectInDealStatusFilter(String expectedDealStatusValue) {
        pages.searchPage().getDealStatusSelect().click();
        pages.searchPage().getDealStatusSearchInput().sendKeys(expectedDealStatusValue);

        pages.searchPage().getDealStatusOptions().stream()
                .filter(el -> el.getText().trim().equals(expectedDealStatusValue))
                .findFirst()
                .ifPresent(WebElement::click);

        BrowserUtils.wait(1);
        BrowserUtils.waitForVisibility(pages.searchPage().getGoToRelatedRecordButtons().get(0), 45);

    }

    @Then("The user verify deal status filter with {string}")
    public void theUserVerifyDealStatusFilterWithProspect(String expectedStatus) {
        BrowserUtils.wait(2);

        System.out.println(pages.searchPage().getDealStatusValuesResult().stream()
                .map(WebElement::getText).collect(Collectors.toList()));

        boolean allMatch = pages.searchPage().getDealStatusValuesResult().stream()
                .map(WebElement::getText)
                .allMatch(title -> title.toLowerCase().contains(expectedStatus.toLowerCase()));

        Assert.assertTrue("Deal Durumları Filtredeki Aramayla Eşleşmiyor", allMatch);
    }

    @When("The user click deal status reset button")
    public void theUserClickDealStatusResetButton() {
        pages.searchPage().getDealStatusResetButton().click();
    }

    @And("The user verify Reset button func for deal status filter")
    public void theUserVerifyResetButtonFuncForDealStatusFilter() {
        BrowserUtils.wait(2);
        System.out.println("Reset Sonrası Deal filter: " + pages.searchPage().getSelectedDealStatusText().getText());
        Assert.assertEquals("Deal Durumu Resetlenmedi", "Deal Durumu",
                pages.searchPage().getSelectedDealStatusText().getText());
    }

    @When("The user click create date sort button")
    public void theUserClickCreateDateSortButton() {
        pages.searchPage().getCreateDateSortButton().click();
        BrowserUtils.wait(3);
    }

    @Then("The user verify create date descending sort")
    public void theUserVerifyCreateDateDescendingSort() {
        List<String> createDates = pages.searchPage().getCreateDateValues().stream()
                .map(WebElement::getText).toList();

        System.out.println("Desc: " + createDates);
        Assert.assertTrue(SearchPage.isSortedDescending(createDates, "dd-MM-yyyy HH:mm:ss"));

    }

    @Then("The user verify create date ascending sort")
    public void theUserVerifyCreateDateAscendingSort() {
        List<String> createDates = pages.searchPage().getCreateDateValues().stream()
                .map(WebElement::getText).toList();

        System.out.println("Asc: " + createDates);

        Assert.assertTrue(SearchPage.isSortedAscending(createDates, "dd-MM-yyyy HH:mm:ss"));
    }

    LocalDate today = LocalDate.now();

    int year = today.getYear();
    int month = today.getMonthValue();
    int day = today.getDayOfMonth();
    @When("The user select date filter")
    public void theUserSelectDateFilter() {
//        BrowserUtils.wait(1);
//        DateFilterUtils df = new DateFilterUtils(Driver.getDriver(), Duration.ofSeconds(10));
//        String locate = "//button[.//span[normalize-space()='Oluşturulma Tarihi']]";
//        df.selectCreatedAt(LocalDate.of(year, month, day), locate);
        BrowserUtils.wait(2);

        Driver.getDriver().findElement(By.xpath("//div[3]/div[2]/div[2]/div//button")).click();
        BrowserUtils.wait(1);
        pages.searchPage().getTodayDateOption().click();

//        Locale tr = new Locale("tr", "TR");
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d MMMM uuuu", tr);
//        String today = LocalDate.now(ZoneId.of("Europe/Istanbul")).format(fmt);

        BrowserUtils.wait(2);

    }

    @Then("The user verify create date filter")
    public void theUserVerifyCreateDateFilter() {
        List<String> createDates = pages.searchPage().getCreateDateValues().stream()
                .map(WebElement::getText).toList();

        boolean allMatch = false;

        for (String createDate : createDates) {
            System.out.println(createDate.split(" ")[2]);

            LocalDate expectedDate = LocalDate.of(year, month, day);

            String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            LocalDate actualDate = LocalDate.parse(createDate.split(" ")[2], formatter);

            if (formattedDate.equals(createDate.split(" ")[2])) {
                allMatch = true;
            }

        }
        Assert.assertTrue("Tarih Filtresi Hatalı",allMatch);

    }

    @When("The user click create date reset button")
    public void theUserClickCreateDateResetButton() {
        BrowserUtils.wait(1);
        pages.searchPage().getCreateDateResetFilter().click();
    }

    @Then("The user verify Reset button func for create date filter")
    public void theUserVerifyResetButtonFuncForCreateDateFilter() {
        System.out.println("Reset Sonrası Create Date: " + pages.searchPage().getSelectedCreateDateText().getText());

        Assert.assertEquals("Create Date Sıfırlanmadı",
                "Oluşturulma Tarihi", pages.searchPage().getSelectedCreateDateText().getText());
    }

    @When("The user click create new record button")
    public void theUserClickCreateNewRecordButton() {
        pages.searchPage().getCreateNewRecordButton().click();
    }

    String randomValue;

    @When("The user fill and save the form")
    public void theUserFillAndSaveTheForm() {
        SelectFilterUtils sf = new SelectFilterUtils(Driver.getDriver(), Duration.ofSeconds(10));

        randomValue = UUID.randomUUID().toString();
        System.out.println(randomValue);

        for (WebElement select : pages.formsPage().getFormSelects()) {
            if (select.getAttribute("id").equals("salesRep"))
                continue;
            sf.chooseRandom(select);
        }

        for (WebElement input : pages.formsPage().getFormInputs()) {
            input.sendKeys(randomValue);
        }

        pages.formsPage().getFormSaveButton().click();
        BrowserUtils.wait(1);
        Driver.getDriver().get("https://sipayapp.efectura.com/login");
        BrowserUtils.wait(2);

    }

    @Then("The user verify record is created")
    public void theUserVerifyRecordIsCreated() {
        BrowserUtils.wait(2);
        System.out.println("Filtre Öncesi random: " + randomValue);


        for (char c : randomValue.toCharArray()) {
            pages.searchPage().getSearchInput().sendKeys(Character.toString(c));
//            BrowserUtils.wait((int) 0.5); // 50ms gecikme1
        }

        BrowserUtils.wait(1);
        pages.searchPage().getSearchButton().click();
        BrowserUtils.wait(6);

        System.out.println(pages.searchPage().getRecordTitles().stream()
                .map(WebElement::getText).collect(Collectors.toList()));

        boolean allMatch = pages.searchPage().getRecordTitles().stream()
                .map(WebElement::getText)
                .allMatch(title -> title.toLowerCase().contains(randomValue.toLowerCase()));

        boolean isCreated = pages.searchPage().getRecordTitles().size() > 0;

        Assert.assertTrue("Title'lar içinde yeni oluşturulan bulunamadı", allMatch && isCreated);

    }

    @When("The user click related record button at row {int}")
    public void theUserClickRelatedRecordButtonAtRow(int rowNumber) {
        pages.searchPage().getGoToRelatedRecordButtons().get(rowNumber).click();
    }

    String formNamex = "Prospect";
    @Then("The user verify {string} form is open")
    public void theUserVerifyFormIsOpen(String formName) {
        BrowserUtils.wait(3);
        BrowserUtils.waitForVisibility(Driver.getDriver().findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/div[2]//fieldset")), 45);
        Assert.assertEquals(formName, Driver.getDriver().
                findElement(By.xpath("//*[@id='root']/div/main/div/div/div[1]//h3")).getText());
        formNamex = formName;

    }

    @Then("The user verify table no result")
    public void theUserVerifyTableNoResult() {
        BrowserUtils.wait(3);
        BrowserUtils.waitForVisibility(pages.searchPage().getSearchInput(), 30);

        for (char c : randomValue.toCharArray()) {
            pages.searchPage().getSearchInput().sendKeys(Character.toString(c));
        }
        pages.searchPage().getSearchButton().click();
        BrowserUtils.wait(2);

        Assert.assertEquals("Kendisine atalı olmayan kaydı görüyor",
                "Kayıt bulunamadı", pages.generalPage().getTableNoContentText().getText());

    }

    @Then("The user verify table with record")
    public void theUserVerifyTableWithRecord() {

        BrowserUtils.wait(3);

        for (char c : randomValue.toCharArray()) {
            pages.searchPage().getSearchInput().sendKeys(Character.toString(c));
        }
        pages.searchPage().getSearchButton().click();
        BrowserUtils.wait(4);

        System.out.println(pages.searchPage().getRecordTitles().stream()
                .map(WebElement::getText).collect(Collectors.toList()));

        boolean allMatch = pages.searchPage().getRecordTitles().stream()
                .map(WebElement::getText)
                .allMatch(title -> title.toLowerCase().contains(randomValue.toLowerCase()));

        Assert.assertTrue("Kendine Atalı Kaydı Göremiyor!! ", allMatch);

    }

    @Then("The user verify table no result for created case")
    public void theUserVerifyTableNoResultForCreatedCase() {
        BrowserUtils.wait(3);
        BrowserUtils.waitForVisibility(pages.searchPage().getSearchInput(), 30);

        for (char c : randomValue.toCharArray()) {
            pages.searchPage().getSearchInput().sendKeys(Character.toString(c));
        }
        pages.searchPage().getSearchButton().click();
        BrowserUtils.wait(3);

        Assert.assertEquals("Kendisinin oluşturmadığı kaydı benim oluşturduklarım listesinde görüyor",
                "Kayıt bulunamadı", pages.generalPage().getTableNoContentText().getText());
    }

    @When("The user fill the form partially")
    public void theUserFillTheFormPartially() {

        SelectFilterUtils sf = new SelectFilterUtils(Driver.getDriver(), Duration.ofSeconds(10));

        randomValue = UUID.randomUUID().toString();
        System.out.println(randomValue);

        for (WebElement select : pages.formsPage().getFormSelects()) {
            if (select.getAttribute("id").equals("salesRep") || select.getAttribute("id").equals("source"))
                continue;
            sf.chooseRandom(select);
        }

        for (WebElement input : pages.formsPage().getFormInputs()) {
            input.sendKeys(randomValue);
        }

    }

    String validPhone;
    String validEmail;
    String address;

    @When("The user fill the prospect form")
    public void theUserFillTheProspectForm() {
        SelectFilterUtils sf = new SelectFilterUtils(Driver.getDriver(), Duration.ofSeconds(1));

        randomValue = UUID.randomUUID().toString();
        System.out.println(randomValue);

        for (WebElement select : pages.formsPage().getFormSelects()) {
            if (select.getAttribute("id").equals("salesRep") || select.getAttribute("id").equals("prospectDurum"))
                continue;
            sf.chooseRandom(select);
        }

        sf.choose(Driver.getDriver().findElement(By.id("prospectDurum")), "Ulaşılamadı");

        List<String> needFormatInputs = List.of("phone", "email", "estVolume");

        for (WebElement input : pages.formsPage().getFormInputs()) {
            if (!needFormatInputs.contains(input.getAttribute("id"))) {
                input.sendKeys(randomValue);
            }
            if (input.getAttribute("id").equals("phone")) {
                validPhone = BrowserUtils.generateTurkishMobileNumber();
                input.sendKeys(validPhone);
            }
            if (input.getAttribute("id").equals("email")) {
                validEmail = BrowserUtils.generateRandomEmail();
                input.sendKeys(validEmail);
            }
            if (input.getAttribute("id").equals("estVolume")) {
                input.sendKeys("10000000");
            }

        }

    }

    @When("The user fill the prospect form with invalid mail and phone")
    public void theUserFillTheProspectFormWithInvalidMailAndPhone() {

        SelectFilterUtils sf = new SelectFilterUtils(Driver.getDriver(), Duration.ofSeconds(10));

        randomValue = UUID.randomUUID().toString();
        System.out.println(randomValue);

        for (WebElement select : pages.formsPage().getFormSelects()) {
            if (select.getAttribute("id").equals("salesRep"))
                continue;
            sf.chooseRandom(select);
        }

        List<String> needFormatInputs = List.of("phone", "email", "estVolume");

        for (WebElement input : pages.formsPage().getFormInputs()) {
            if (!needFormatInputs.contains(input.getAttribute("id"))) {
                input.sendKeys(randomValue);
            }
            if (input.getAttribute("id").equals("phone")) {
                input.sendKeys(BrowserUtils.generateTurkishMobileNumber() + "x");
            }
            if (input.getAttribute("id").equals("email")) {
                input.sendKeys(BrowserUtils.generateRandomEmail().split("@")[0]);
            }
            if (input.getAttribute("id").equals("estVolume")) {
                input.sendKeys("10000");
            }

        }

    }

    @Then("The user verify invalid email and phone warning")
    public void theUserVerifyInvalidEmailAndPhoneWarning() {

        Assert.assertEquals("Geçersiz Mail Uyarısı Yazmadı", "Lütfen geçerli bir e-posta adresi giriniz",
                pages.generalPage().getInvalidEmailWarning().getText());

        Assert.assertEquals("Geçersiz Telefon Uyarısı Yazmadı", "Lütfen geçerli bir telefon numarası giriniz",
                pages.generalPage().getInvalidPhoneWarning().getText());

    }

    String formStatus;
    String currentUrl;
    @When("The user click {string} button")
    public void theUserClickButton(String buttonName) {
        BrowserUtils.adjustScreenSize(50,Driver.getDriver());
        currentUrl = Driver.getDriver().getCurrentUrl();
        WebElement button = Driver.getDriver().
                findElement(By.xpath("//button[contains(.,'" + buttonName + "')]"));

        System.out.println("Button text: " + button.getText());

        BrowserUtils.wait(1);
        BrowserUtils.waitForVisibility(button, 45);
        BrowserUtils.waitForClickability(button, 45);
//        BrowserUtils.adjustScreenSize(70,Driver.getDriver());

        button.click();
//        BrowserUtils.safeClick(button);
        BrowserUtils.wait(2);
        if (buttonName.equals("Kaydet"))
            formStatus = "save";
        if (buttonName.equals("Tamamla"))
            formStatus = "complete";
    }

    @Then("The user verify old values")
    public void theUserVerifyOldValues() {
        BrowserUtils.wait(5);
        for (Map.Entry<String, String> entry : attributeAndValues.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());

            if (BrowserUtils.isElementDisplayed(By.id(entry.getKey()))) {
                System.out.println(entry.getKey() + " = " + Driver.getDriver().findElement(By.id(entry.getKey())).getText());
                if (entry.getKey().equals("prospectDurum")) {
                    if (formStatus.equals("save")) {
                        softAssert.assertEquals(
                                Driver.getDriver().findElement(By.id(entry.getKey())).getText(),
                                entry.getValue(),
                                entry.getKey() + " id'li attribute formda farklı. Form: " + formNamex
                        );

                    } else if (formStatus.equals("complete")) {
                        softAssert.assertEquals(
                                Driver.getDriver().findElement(By.id(entry.getKey())).getText(),
                                "Onaylandı",
                                entry.getKey() + " id'li attribute formda farklı. Form: " + formNamex
                        );
                    }
                    continue;
                }

                if (entry.getKey().equals("estVolume")) {
                    softAssert.assertEquals(
                            BrowserUtils.getValueInInputBox(Driver.getDriver().findElement(By.id(entry.getKey()))).replace(".", ""),
                            entry.getValue(),
                            entry.getKey() + " id'li attribute formda farklı. Form: " + formNamex
                    );
                    continue;
                }

                if (entry.getKey().equals("accountantEmail")) {
                    softAssert.assertEquals(
                            BrowserUtils.getValueInInputBox(Driver.getDriver().findElement(By.id(entry.getKey()))),
                            entry.getValue(),
                            entry.getKey() + " id'li attribute formda farklı. Form: " + formNamex
                    );
                    continue;
                }

                if (entry.getKey().equals("estRevenue")) {
                    softAssert.assertEquals(
                            entry.getValue(),
                            monthlyPos * 12 + "",
                            entry.getKey() + " id'li attribute formda farlı. Form: " + formNamex
                    );
                }

                if (Driver.getDriver().findElement(By.id(entry.getKey())).getTagName().equals("input")) {
                    if (!entry.getKey().equals("email")) {
                        softAssert.assertEquals(
                                BrowserUtils.getValueInInputBox(Driver.getDriver().findElement(By.id(entry.getKey()))).replace(".", ""),
                                entry.getValue(),
                                entry.getKey() + " id'li attribute formda farklı. Form: " + formNamex
                        );
                    }
                    continue;
                }

                if (entry.getKey().equals("segment")) {
                    softAssert.assertEquals(
                            Driver.getDriver().findElement(By.id(entry.getKey())).getText(),
                            entry.getValue(),
                            entry.getKey() + " id'li attribute formda farklı. Form: " + formNamex
                    );
                    continue;
                }

                softAssert.assertEquals(
                        Driver.getDriver().findElement(By.id(entry.getKey())).getText(),
                        entry.getValue(),
                        entry.getKey() + " id'li attribute formda farklı. Form: " + formNamex
                );
            }
        }
    }


    @Then("The user verify prospect values")
    public void theUserVerifyProspectValues() {
        BrowserUtils.wait(5);
        for (Map.Entry<String, String> entry : prospectData.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());

            if (BrowserUtils.isElementDisplayed(By.id(entry.getKey()))) {
                System.out.println(entry.getKey() + " = " + Driver.getDriver().findElement(By.id(entry.getKey())).getText());
                if (entry.getKey().equals("prospectDurum")) {
                    if (formStatus.equals("save")) {
                        softAssert.assertEquals(
                                Driver.getDriver().findElement(By.id(entry.getKey())).getText(),
                                entry.getValue(),
                                entry.getKey() + " id'li attribute farklı. Form: " + formNamex
                        );

                    } else if (formStatus.equals("complete")) {
                        softAssert.assertEquals(
                                Driver.getDriver().findElement(By.id(entry.getKey())).getText(),
                                "Onaylandı",
                                entry.getKey() + " id'li attribute farklı. Form: " + formNamex
                        );
                    }
                    continue;
                }

                if (entry.getKey().equals("estVolume")) {
                    softAssert.assertEquals(
                            BrowserUtils.getValueInInputBox(Driver.getDriver().findElement(By.id(entry.getKey()))).replace(".", ""),
                            entry.getValue(),
                            entry.getKey() + " id'li attribute farklı. Form: " + formNamex
                    );
                    continue;
                }

                if (entry.getKey().equals("estRevenue")) {
                    softAssert.assertEquals(
                            entry.getValue(),
                            monthlyPos * 12 + "",
                            entry.getKey() + " id'li attribute farlı. Form: " + formNamex
                    );
                }

                if (Driver.getDriver().findElement(By.id(entry.getKey())).getTagName().equals("input")) {
                    if (!entry.getKey().equals("email")) {
                        softAssert.assertEquals(
                                BrowserUtils.getValueInInputBox(Driver.getDriver().findElement(By.id(entry.getKey()))).replace(".", ""),
                                entry.getValue(),
                                entry.getKey() + " id'li attribute farklı. Form: " + formNamex
                        );
                    }
                    continue;
                }

                if (entry.getKey().equals("segment")) {
                    softAssert.assertEquals(
                            Driver.getDriver().findElement(By.id(entry.getKey())).getText(),
                            entry.getValue(),
                            entry.getKey() + " id'li attribute farklı. Form: " + formNamex
                    );
                    continue;
                }

                softAssert.assertEquals(
                        Driver.getDriver().findElement(By.id(entry.getKey())).getText(),
                        entry.getValue(),
                        entry.getKey() + " id'li attribute farklı. Form: " + formNamex
                );
            }
        }
    }


    String selectOption;

    @When("The user select {string} in {string}")
    public void theUserSelectOptionInSelectElement(String option, String selectId) {
        selectOption = option;
        SelectFilterUtils sf = new SelectFilterUtils(Driver.getDriver(), Duration.ofSeconds(3));

        sf.choose(Driver.getDriver().findElement(By.id(selectId)), option);

    }

    @Then("The user verify other attributes display")
    public void theUserVerifyOtherAttributesDisplay() {
        List<String> expectedIds = List.of("singlePaymentRate", "installmentUsageRate",
                "debitUsageRate", "creditUsageRate", "frequentlyUsedInstallment");

        for (String id : expectedIds) {
            BrowserUtils.wait(1);
            Assert.assertTrue(id + "'li element gelmedi",
                    BrowserUtils.isElementDisplayed(Driver.getDriver().findElement(By.id(id))));

            if (selectOption.equals("Fiziki POS")) {
                Assert.assertTrue("vb507 idli element gelmedi",
                        BrowserUtils.isElementDisplayed(Driver.getDriver().findElement(By.id("vb507"))));
            }

        }
        BrowserUtils.wait(1);

    }

    @When("The user fill the lead form")
    public void theUserFillTheLeadForm() {
        // frequentlyUsedInstallment
        // rivalInfo, memberCompanyType
        List<String> requiredLeadSelectIds = List.of("rivalInfo", "memberCompanyType");

        SelectFilterUtils sf = new SelectFilterUtils(Driver.getDriver(), Duration.ofSeconds(2));

        randomValue = UUID.randomUUID().toString();
        System.out.println(randomValue);

        for (String selectId : requiredLeadSelectIds) {
            sf.chooseRandom(Driver.getDriver().findElement(By.id(selectId)));
        }

        if (BrowserUtils.isElementDisplayed(Driver.getDriver().findElement(By.id("frequentlyUsedInstallment")))) {
            sf.chooseRandom(Driver.getDriver().findElement(By.id("frequentlyUsedInstallment")));
        }
        if (BrowserUtils.isElementDisplayed(Driver.getDriver().findElement(By.id("vb507")))) {
            sf.chooseRandom(Driver.getDriver().findElement(By.id("vb507")));
        }

        for (WebElement input : pages.formsPage().getFormInputs()) {
            try {
                input.sendKeys("50");
            } catch (org.openqa.selenium.ElementNotInteractableException e) {
                break;
            }

        }


    }

    @When("The user upload documents")
    public void theUserUploadDocuments() {
        BrowserUtils.wait(5);
        pages.generalPage().getRequiredDocumentsButton().click();
        SelectFilterUtils sf = new SelectFilterUtils(Driver.getDriver(), Duration.ofSeconds(3));
        BrowserUtils.adjustScreenSize(40, Driver.getDriver());

        List<String> actualReqDocuments = BrowserUtils.convertWebElementListToStringList(
                pages.generalPage().getActualRequiredDocuments()
        );

        // 1) Proje kökünü al
        String projectRoot = System.getProperty("user.dir");

        // 2) Relative path ile birleştir (OS bağımsız)
        Path docPath = Paths.get(projectRoot, "src", "test", "resources", "features", "testDocument.xlsx");

        // 3) Selenium'a vereceğimiz kesin (absolute) string
        String absoluteFilePath = docPath.toFile().getAbsolutePath();

        System.out.println("Uploading file from: " + absoluteFilePath);

        for (String actualReqDocument : actualReqDocuments) {
            BrowserUtils.wait(1);

            // <input type="file"> alanına MUTLAKA absolute path veriyoruz
            pages.generalPage().getDocumentInput().sendKeys(absoluteFilePath);

            BrowserUtils.wait(1);
            sf.choose(Driver.getDriver().findElement(By.id("BELGE_TURU")), actualReqDocument);
            pages.generalPage().getAddDocumentButton().click();
            BrowserUtils.wait(1);

            if (isElementDisplayed(By.xpath("//button[contains(text(),'Mevcut Dosyayı Kullan')]"))) {
                pages.generalPage().getUseAlreadyAddedFileButton().click();
            }

            BrowserUtils.waitForVisibility(pages.generalPage().getWarningElement(), 20);
            Assert.assertEquals("Döküman başarıyla eklendi", pages.generalPage().getWarningElement().getText());
        }
    }

    @When("The user click {string}")
    public void theUserClickHasPosUsage(String id) {
        WebElement checkbox = Driver.getDriver().findElement(By.cssSelector("#" + id + "+div"));
        checkbox.click();
        BrowserUtils.isElementDisplayed(checkbox);
    }

    @Then("The user verify {string} is displayed")
    public void theUserVerifyOtherPosInfoIsDisplayed(String id) {
        Assert.assertTrue(id + " id'li element görünmedi!!",
                BrowserUtils.isElementDisplayed(Driver.getDriver().findElement(By.id(id))));
    }

    @When("The user enter {string} into {string}")
    public void theUserEnterKARAIntoLastName(String value, String id) {
        WebElement input = Driver.getDriver().findElement(By.id(id));

        input.sendKeys(value);

    }

    Map<String,String> attributeAndValues = new HashMap<>();
    Map<String,String> attributeCodesAndLabels = new HashMap<>();
    // SELECT/COMBOBOX DOLDURMA
    @When("The user fill select prospect form sales and field")
    public void theUserFillSelectProspectFormSalesAndField() {
        attributeAndValues.clear();
        Random random = new Random();

        BrowserUtils.adjustScreenSize(40, Driver.getDriver());
        BrowserUtils.wait(1);

        for (WebElement select : pages.formsPage().getFormSelects()) {
            String id = select.getAttribute("id");
            System.out.println("id: " + id);

            // Genel istisnalar (mevcut kuralın korunması)
            if ("virtualPosRequest".equals(id) || "product".equals(id) || "segment".equals(id)
                    || "salesRep".equals(id) || "communicationTracking".equals(id) || "companyType".equals(id) ||
                    "prospectDurum".equals(id) || "installationInfo".equals(id)) {
                continue;
            }

            // --- Onboarding Risk Formu'na özel istisnalar ---
            // disabled olan seçim alanı -> atla
            if ("riskRepresentative".equals(id)) {
                continue;
            }


            attributeAndValues.put("segment", "Small");
            attributeCodesAndLabels.put("segment","Segment");

            if (BrowserUtils.isElementDisplayed(By.id("salesRep"))) {
                attributeAndValues.put("salesRep",Driver.getDriver().findElement(By.id("salesRep")).getText());
                attributeCodesAndLabels.put("salesRep","Atanan Satış Temsilcisi");
            }


            if (BrowserUtils.isElementDisplayed(select)) {
                // Radix combobox yapısı -> click + search input üzerinden enter
                Driver.getDriver().findElement(By.id(id)).click();
                BrowserUtils.waitForVisibility(pages.generalPage().getSelectOptions().get(0), 40);

                int randomIndex = random.nextInt(pages.generalPage().getSelectOptions().size());
                String optionText = pages.generalPage().getSelectOptions().get(randomIndex).getText();

                // Bazı combobox'larda direkt yaz/enter akışı
                pages.generalPage().getSelectSearchInput()
                        .sendKeys(optionText + Keys.ENTER);

                attributeAndValues.put(id, optionText);
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.xpath("//*[@id='" + id + "']/parent::*//label")).getText());
            }
        }

        // --- Onboarding Risk Formu: "Kesinleşen Valör" güvence altına alma ---
        // Eğer listedeki döngü yakalayamazsa yedek hedefleme:
//        try {
//            WebElement valor = Driver.getDriver().findElement(By.id("valorSelectOperation"));
//            if (BrowserUtils.isElementDisplayed(valor)) {
//                valor.click();
//                BrowserUtils.waitForVisibility(pages.generalPage().getSelectOptions().get(0), 20);
//                String pick = pages.generalPage().getSelectOptions().get(0).getText();
//                pages.generalPage().getSelectSearchInput().sendKeys(pick + Keys.ENTER);
//                attributeAndValues.put("valorSelectOperation", pick);
//            }
//        } catch (Exception ignored) {}
    }


    // INPUT + ÖZEL ALANLAR DOLDURMA
    int monthlyPos;
    String name;

    @When("The user fill inputs prospect form sales and field")
    public void theUserFillInputsProspectFormSalesAndField() {
        randomValue = UUID.randomUUID().toString() + "Ç";
        System.out.println(randomValue);

        // Özel format gerektiren alan listesi (genel adımın korunmuş hâli)
        List<String> needFormatInputs = List.of(
                "phone", "email", "estVolume", "location", "singlePaymentRate",
                "installmentUsageRate", "debitUsageRate", "creditUsageRate", "currentRate",
                "estRevenue", "commissionRate", "estimatedIncome", "ownerTC", "partnerTC",
                "authorizedTC", "authorized","walletMasterId","installmentCount","deviceQty","accountantEmail",
                "accountantPhone","mccCode","installationAddress"
        );

        for (WebElement input : pages.formsPage().getFormInputs()) {
            if (!BrowserUtils.isElementDisplayed(input)) continue;

            String id = input.getAttribute("id");

            // Genel mantık: özel gerektirmeyen input'lara randomValue
            if (!needFormatInputs.contains(id)) {
                input.sendKeys(randomValue);
                attributeAndValues.put(id, randomValue);
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.xpath("//*[@id='" + id + "']/parent::*//label")).getText());
                if ("firstName".equals(id)) name = randomValue;
            }

            // --- Onboarding Risk Formu'na özel alanlar ---
            // MCC Kodu (text) -> örnek bir MCC
//            if ("mccCode".equals(id)) {
//                String mcc = "5311"; // örnek market/mağaza MCC
//                input.clear();
//                input.sendKeys(mcc);
//                attributeAndValues.put(id, mcc);
//                attributeCodesAndLabels.put(id,
//                        Driver.getDriver().findElement(By.xpath("//*[@id='" + id + "']/parent::*//label")).getText());
//            }

            // Taksit Sayısı (number)
            if ("installmentCount".equals(id)) {
                String count = "12";
                input.clear();
                input.sendKeys(count);
                attributeAndValues.put(id, count);
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.xpath("//*[@id='" + id + "']/parent::*//label")).getText());
            }

            // Diğer sayfalardan gelen genel özel alan kuralları (telefon, email, tutar, oran, TC vb.)
            if ("phone".equals(id)) {
                validPhone = BrowserUtils.generateTurkishMobileNumber();
                input.sendKeys(validPhone);
                attributeAndValues.put(id, validPhone);
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.xpath("//*[@id='" + id + "']/parent::*//label")).getText());
            }

            if ("accountantPhone".equals(id)) {
                validPhone = BrowserUtils.generateTurkishMobileNumber();
                input.sendKeys(validPhone);
                attributeAndValues.put(id, validPhone);
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.xpath("//*[@id='" + id + "']/parent::*//label")).getText());
            }

            if ("email".equals(id)) {
                validEmail = BrowserUtils.generateRandomEmail();
                input.sendKeys(validEmail);
                attributeAndValues.put(id, validEmail);
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.xpath("//*[@id='" + id + "']/parent::*//label")).getText());
            }

            if ("accountantEmail".equals(id)) {
                validEmail = BrowserUtils.generateRandomEmail();
                String mail = validEmail.split("\\.")[0] + validEmail.split("\\.")[1];
                input.sendKeys(validEmail);
                attributeAndValues.put(id, validEmail);
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.xpath("//*[@id='" + id + "']/parent::*//label")).getText());
            }

//            if ("installationAddress".equals(id)) {
//                address = UUID.randomUUID().toString();
//                input.sendKeys(address);
//                attributeAndValues.put(id, address);
//                attributeCodesAndLabels.put(id,
//                        Driver.getDriver().findElement(By.xpath("//*[@id='" + id + "']/parent::*//label")).getText());
//            }

            if ("mccCode".equals(id)) {
                input.sendKeys("67670");
                attributeAndValues.put(id, "67670");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.xpath("//*[@id='" + id + "']/parent::*//label")).getText());
            }


            if ("estVolume".equals(id)) {
                String pos = "10000000";
                input.sendKeys(pos);
                attributeAndValues.put(id, pos);
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
                monthlyPos = Integer.parseInt(pos);
            }
            if ("singlePaymentRate".equals(id)) {
                input.sendKeys("10");
                attributeAndValues.put(id, "10");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("installmentUsageRate".equals(id)) {
                input.sendKeys("20");
                attributeAndValues.put(id, "20");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("debitUsageRate".equals(id)) {
                input.sendKeys("30");
                attributeAndValues.put(id, "30");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("creditUsageRate".equals(id)) {
                input.sendKeys("40");
                attributeAndValues.put(id, "40");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("currentRate".equals(id)) {
                input.sendKeys("50");
                attributeAndValues.put(id, "50");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("commissionRate".equals(id)) {
                input.sendKeys("60");
                attributeAndValues.put(id, "60");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("estimatedIncome".equals(id)) {
                input.sendKeys("6000");
                attributeAndValues.put(id, "6000");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("ownerTC".equals(id)) {
                input.sendKeys("12777888111");
                attributeAndValues.put(id, "12777888111");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("partnerTC".equals(id)) {
                input.sendKeys("12777888222");
                attributeAndValues.put(id, "12777888222");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("authorizedTC".equals(id)) {
                input.sendKeys("12777888333");
                attributeAndValues.put(id, "12777888333");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("walletMasterId".equals(id)) {
                input.sendKeys("56456");
                attributeAndValues.put(id, "56456");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("authorized".equals(id)) {
                attributeAndValues.put(id, name + " " + name);
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
            if ("deviceQty".equals(id)) {
                input.sendKeys("9");
                attributeAndValues.put(id, "9");
                attributeCodesAndLabels.put(id,
                        Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
            }
        }

        // --- Onboarding Risk Notları (contenteditable) ---
        try {
            WebElement notesEditable = Driver.getDriver().findElement(By.cssSelector("div[contenteditable='true']"));
            if (BrowserUtils.isElementDisplayed(notesEditable)) {
                notesEditable.click();
                notesEditable.sendKeys("Automated risk note - " + randomValue);
                attributeAndValues.put("onboardingRiskNotes", "Automated risk note - " + randomValue);
            }
        } catch (Exception ignored) {}

//        // --- Risk Dökümanları (opsiyonel) ---
//        try {
//            WebElement fileInput = Driver.getDriver().findElement(By.id("riskDocument"));
//            if (BrowserUtils.isElementDisplayed(fileInput)) {
//                // Test kaynağındaki örnek dosya yolunu kendi projenle değiştir.
//                String path = Paths.get("src/test/resources/files/dummy.pdf").toAbsolutePath().toString();
//                fileInput.sendKeys(path);
//                attributeAndValues.put("riskDocument", "dummy.pdf");
//            }
//        } catch (Exception ignored) {}
    }


    @When("The user select2 {string} in {string}")
    public void theUserSelectAsdInAsd(String option,String id) {
        BrowserUtils.wait(1);
        Driver.getDriver().findElement(By.id(id)).click();
        pages.generalPage().getSelectSearchInput().sendKeys(option + Keys.ENTER);
        BrowserUtils.wait(3);
        selectOption = option;
        attributeAndValues.put(id,option);
        attributeCodesAndLabels.put(id,
                Driver.getDriver().findElement(By.xpath("//*[@id='" + id + "']/parent::*//label")).getText());

    }

    @Then("The user verify duplicate")
    public void theUserVerifyDuplicate() {
        BrowserUtils.wait(2);

        for (char c : randomValue.toCharArray()) {
            pages.searchPage().getSearchInput().sendKeys(Character.toString(c));
//            BrowserUtils.wait((int) 0.5); // 50ms gecikme1
        }

        BrowserUtils.wait(1);
        pages.searchPage().getSearchButton().click();
        BrowserUtils.wait(1);
        Assert.assertEquals("2 Kaydedilen form duplicate oldu", pages.searchPage().getRecordTitles().size(),1);
    }

    @When("THe user fill date {string} in form")
    public void theUserFillDateIdInForm(String id) {
        Driver.getDriver().findElement(By.id(id)).click();
        pages.searchPage().getTodayDateOption().click();

        Locale tr = new Locale("tr", "TR");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d MMMM uuuu", tr);
        String today = LocalDate.now(ZoneId.of("Europe/Istanbul")).format(fmt);

        if (id.equals("newAppointment")) {
            attributeAndValues.put(id,today + " saat 00:00");
        } else {
            attributeAndValues.put(id,today);
        }


//        attributeAndValues.put(id,today);
        attributeCodesAndLabels.put(id,
                Driver.getDriver().findElement(By.cssSelector("label[for='" + id + "']")).getText());
    }


    @Then("The user verify required documents")
    public void theUserVerifyRequiredDocuments() {
        BrowserUtils.wait(2);
        BrowserUtils.adjustScreenSize(40,Driver.getDriver());
        SelectFilterUtils sf = new SelectFilterUtils(Driver.getDriver(), Duration.ofSeconds(3));
        WebElement select = Driver.getDriver().findElement(By.id("companyType"));
        select.click();

        List<String> optionTexts = pages.generalPage().getCompanyTypeOptions().stream()
                .map(WebElement::getText).toList();


//        sf.chooseRandom(select);
        BrowserUtils.wait(5);
        pages.generalPage().getRequiredDocumentsButton().click();

//        for (String option : optionTexts) {
//            select.click();
//            pages.generalPage().getSelectSearchInput().sendKeys(option + Keys.ENTER);
//            BrowserUtils.wait(3);
//
//            List<String> actualReqDocuments = BrowserUtils.convertWebElementListToStringList
//                    (pages.generalPage().getActualRequiredDocuments());
//
//
//            if (option.equals("Adi Ortaklık") || option.equals("XB")) {
//                Assert.assertTrue(BrowserUtils.areListsEqualIgnoreOrder(
//                        actualReqDocuments,pages.generalPage().getAdiAndXbRequiredDocuments()));
//            }
//            if (option.equals("Şahıs")) {
//                Assert.assertTrue(BrowserUtils.areListsEqualIgnoreOrder(
//                        actualReqDocuments,pages.generalPage().getSahisRequiredDocuments()));
//            }
//            if (option.equals("Limited") || option.equals("Anonim")) {
//                Assert.assertTrue(BrowserUtils.areListsEqualIgnoreOrder(
//                        actualReqDocuments,pages.generalPage().getLimitedandAnonimRequiredDocuments()));
//            }
//            if (option.equals("Dernek")) {
//                Assert.assertTrue(BrowserUtils.areListsEqualIgnoreOrder(
//                        actualReqDocuments,pages.generalPage().getDernekRequiredDocuments()));
//            }
//            if (option.equals("Vakıf")) {
//                Assert.assertTrue(BrowserUtils.areListsEqualIgnoreOrder(
//                        actualReqDocuments,pages.generalPage().getVakifRequiredDocuments()));
//            }
//
//        }

        select.click();
        pages.generalPage().getSelectSearchInput().sendKeys("Adi Ortaklık" + Keys.ENTER);
        BrowserUtils.wait(1);
//        selectOption = option;
        attributeAndValues.put("companyType","Adi Ortaklık");

    }

    @When("The user navigate current url")
    public void theUserNavigateCurrentUrl() {
        Driver.getDriver().get(currentUrl);
        BrowserUtils.wait(3);
    }

    @When("The user search created form")
    public void theUserSearchCreatedForm() {

        for (char c : name.toCharArray()) {
            pages.searchPage().getSearchInput().sendKeys(Character.toString(c));
        }

        BrowserUtils.wait(1);
        pages.searchPage().getSearchButton().click();
        BrowserUtils.wait(3);
    }

    @Then("The user verify warning2 {string}")
    public void theUserVerifyWarning2(String warning) {
        BrowserUtils.wait(1);
        BrowserUtils.waitForVisibility(pages.searchPage().getRecordWarning(),45);
        Assert.assertTrue(pages.searchPage().getRecordWarning() + " Uyarısı Gelmedi",
                isElementDisplayed(pages.generalPage().getWarningElement()));

        Assert.assertEquals("Mesaj farklı",warning,pages.generalPage().getWarningElement().getText());
    }

    @Given("The user assert all")
    public void theUserAssertAll() {
        softAssert.assertAll();
    }

    String reviseExplanation;
    @When("The user enter revise explanation")
    public void theUserEnterReviseExplanation() {
        reviseExplanation = pages.searchPage().getFormNameText().getText() + "--" + UUID.randomUUID();
        pages.searchPage().getReviseExplanationTextInput().sendKeys(reviseExplanation);
    }

    @Then("The user verify risk revise reason and explanation")
    public void theUserVerifyRiskReviseReasonAndExplanation() {
        softAssert.assertEquals(Driver.getDriver().findElement(By.id("reviseReason")).getText(),"Bilgi Talebi",
                "Risk Revize sebebi seçilenden farklı");


        softAssert.assertEquals(pages.searchPage().getRiskReviseReason().getText(),reviseExplanation,
                "Revize Açıklaması Girilenle aynı değil.");

    }

    @When("The user go to form")
    public void theUserGoToContractForm() {
        String[] parts = currentUrl.split("/");
        pages.searchPage().getSearchInput().sendKeys(parts[parts.length - 1] + Keys.ENTER);
    }

    @When("The user select {string} for search area")
    public void theUserSelectIDForSearchArea(String searchOption) {
        pages.searchPage().getSearchAreaButton().click();
        BrowserUtils.wait(1);
        pages.searchPage().getSearchAreaOptions().
                stream().filter(e -> e.getText().equals(searchOption)).findFirst().ifPresent(WebElement::click);
        BrowserUtils.wait(1);

    }

    @Then("The user verify search area count {string}")
    public void theUserVerifySearchAreaCount(String buttonName) {
        WebElement button = Driver.getDriver().
                findElement(By.xpath("//button[contains(.,'" + buttonName + "')]"));
        softAssert.assertEquals(pages.searchPage().getSelectedSearchAreas().size(),pages.searchPage().getSelectedSearchAreaIcons().size());
        softAssert.assertEquals(button.getText().replace("(","").replace(")","").split(" ")[2],
                pages.searchPage().getSelectedSearchAreaIcons().size(),"Arama alanlarında seçililerin sayısı farklı yazıyor");
    }

    @When("The user switch language to {string}")
    public void theUserSwitchLanguageTo(String language) {
        if (pages.searchPage().getLanguageButton().getText().equals(language)) {
            pages.searchPage().getLanguageButton().click();
        }
        BrowserUtils.wait(1);
    }

    String lang;
    @Then("The user verify page language switched to {string}")
    public void theUserVerifyPageLanguageSwitchedToEN(String language) {
        lang = language;
        if (language.equals("EN")) {
            softAssert.assertEquals(pages.generalPage().getAssignedRecordsButton().getText(),"Assigned Records",
                    "Dil İngilizce olmadı");
        } else if (language.equals("TR")) {
            softAssert.assertEquals(pages.generalPage().getAssignedRecordsButton().getText(),"Atanmış Kayıtlar",
                    "Dil Türkçe Olmadı");
        }
    }

    Map<String,String> prospectData = new HashMap<>();
    @When("The user keep prospect data")
    public void theUserKeepProspectData() {
        prospectData.putAll(attributeAndValues);
        System.out.println("Kaydedilen Prospect Bilgileri: ");
        prospectData.forEach((k, v) -> System.out.println(k + " = " + v));
    }

    Map<String,String> leadData = new HashMap<>();
    @When("The user keep lead data")
    public void theUserKeepLeadData() {
        leadData.putAll(attributeAndValues);
    }

    Map<String,String> pitchedData = new HashMap<>();
    @When("The user keep pitched data")
    public void theUserKeepPitchedData() {
        pitchedData.putAll(attributeAndValues);
    }

    Map<String,String> contractData = new HashMap<>();
    @When("The user keep contract data")
    public void theUserKeepContractData() {
        contractData.putAll(attributeAndValues);
    }

    Map<String,String> riskData = new HashMap<>();
    @When("The user keep risk data")
    public void theUserKeepRiskData() {
        System.out.println("Risk Data: " + riskData);
        riskData.putAll(attributeAndValues);
    }

    Map<String,String> operationData = new HashMap<>();
    @When("The user keep operation data")
    public void theUserKeepOperationData() {
        operationData.putAll(attributeAndValues);
    }

    @Then("The user verify history values")
    public void theUserVerifyHistoryValues() {
        System.out.println("attributeCodesAndLabels: " + attributeCodesAndLabels);

        Map<String, String> reversedMap = attributeCodesAndLabels.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));


        BrowserUtils.adjustScreenSize(50,Driver.getDriver());
        for (WebElement historyAttribute : pages.searchPage().getHistoryAttributes()) {

            if (BrowserUtils.isElementDisplayed(By.xpath("//span[normalize-space(text())='" + historyAttribute.getText() + "']"))) {
                String actualValue = Driver.getDriver().findElement
                                (By.xpath("//span[normalize-space(text())='" + historyAttribute.getText() + "']/following-sibling::div")).getText();

                if (actualValue.equals("-")) {
                    actualValue = actualValue.replace("-","");
                }

                softAssert.assertEquals(actualValue,allFormsData.get(reversedMap.get(historyAttribute.getText())),
                        historyAttribute.getText() + " değeri history'de farklı");

            }
        }
        Driver.getDriver().findElement(By.xpath("/html/body/div[3]/button")).click();
        BrowserUtils.wait(1);
    }

    Map<String,String> allFormsData = new HashMap<>();
    @When("The user combine all forms data")
    public void theUserCombineAllFormsData() {
        allFormsData.putAll(prospectData);
        allFormsData.putAll(leadData);
        allFormsData.putAll(pitchedData);
        allFormsData.putAll(contractData);
        allFormsData.putAll(riskData);
        allFormsData.put("salesRep","Test Sipay");
        System.out.println("allFormsData: " + allFormsData);
    }

    String retReason;
    @When("The user cancel the form")
    public void theUserCancelTheForm() {
        BrowserUtils.wait(1);
        Random random = new Random();

        // Radix combobox yapısı -> click + search input üzerinden enter
        pages.searchPage().getRedReasonSelectButton().click();
        BrowserUtils.waitForVisibility(pages.searchPage().getRedReasonSelectOptions().get(0), 40);

        int randomIndex = random.nextInt(pages.searchPage().getRedReasonSelectOptions().size());
        String optionText = pages.searchPage().getRedReasonSelectOptions().get(randomIndex).getText();

        // Bazı combobox'larda direkt yaz/enter akışı
        pages.searchPage().getRedReasonSelectInput().sendKeys(optionText + Keys.ENTER);
        pages.searchPage().getRedReasonSelectOptions().get(0).click();

        retReason = optionText;

    }

    @Then("The user verify {string} with value {string}")
    public void theUserVerifyKeyWithValue(String key, String expectedValue) {
        BrowserUtils.adjustScreenSize(80,Driver.getDriver());

        String keyValue = Driver.getDriver().
                findElement(By.xpath("//span[normalize-space(.)='" + key + ":']/parent::div")).getText();

        String actualValue = keyValue.split(":")[keyValue.split(":").length - 1].trim();

        System.out.println("Actual Value: " + actualValue);

        softAssert.assertEquals(actualValue,expectedValue,key + " Değeri Arama Sayfasında Beklendiği Gibi Değil");
    }

    @Then("The user verify ret reason")
    public void theUserVerifyRetReason() {
        //div[span[normalize-space(translate(., ':', ''))='Reddetme Nedeni']]
        String key = "Reddetme Nedeni";
        String keyValue = Driver.getDriver().
                findElement(By.xpath("//div[span[normalize-space(translate(., ':', ''))='" + key + "']]")).getText();

        String actualValue = keyValue.split(":")[keyValue.split(":").length - 1].trim();

        softAssert.assertEquals(actualValue,retReason,key + " Değeri Arama Sayfasında Beklendiği Gibi Değil");

    }

    @Then("The user verify only risk records visible")
    public void theUserVerifyOnlyRiskRecordsVisible() {
        BrowserUtils.adjustScreenSize(65,Driver.getDriver());
        List<WebElement> dealStatuses = Driver.getDriver().findElements(By.xpath("//span[normalize-space(.)='Deal Durumu:']/parent::div"));

        boolean allMatch = dealStatuses.stream().allMatch(el -> {
            String text = el.getText().split(":")[el.getText().split(":").length - 1].trim();
            System.out.println("Deal Status: " + text);
            return text.equals("OnBoardingRiskDraft") || text.equals("Contracting") || text.equals("OnboardingRisk");
        });


        softAssert.assertTrue(allMatch,"Risk Rolü Contracting ve OnBoardingRiskDraft statüsü dışında kayıt görüyor listede");


    }

    @Then("The user verify table info no record")
    public void theUserVerifyTableInfo() {
        boolean isNoRecordInfoVisible = BrowserUtils.isElementDisplayed(By.xpath("//div[contains(text(),'Kayıt bulunamadı')]"));
        softAssert.assertTrue(isNoRecordInfoVisible,"Kayıt Bulunamadı Uyarısı Gelmedi");

    }

    @Then("The user verify only operation records visible")
    public void theUserVerifyOnlyOperationRecordsVisible() {
        BrowserUtils.adjustScreenSize(65,Driver.getDriver());
        List<WebElement> dealStatuses = Driver.getDriver().findElements(By.xpath("//span[normalize-space(.)='Deal Durumu:']/parent::div"));

        boolean allMatch = dealStatuses.stream().allMatch(el -> {
            String text = el.getText().split(":")[el.getText().split(":").length - 1].trim();
            System.out.println("Deal Status: " + text);
            return text.equals("OnboardingRisk") || text.equals("OnBoardingOperationDraft");
        });

        softAssert.assertTrue(allMatch,"Operation Rolü OnboardingRisk ve OnBoardingOperationDraft statüsü dışında kayıt görüyor listede");
    }

    String formId;
    @When("The user take form id")
    public void theUserTakeFormId() {
        // https://sipayapp.efectura.com/onboarding/5207
        // https://sipay-ui.efectura.com/Enrich/EditItem/5312

        formId = Driver.getDriver().getCurrentUrl().split("/")[4];
        System.out.println("formId : " + formId);
    }

    @When("The User inputs a valid username {string}")
    public void the_user_inputs_a_valid_username(String username) {
        pages.formsPage().setUsernameField(username);
    }

    @When("The User inputs a valid password {string}")
    public void the_user_inputs_a_valid_password(String password) {
        pages.formsPage().setPasswordField(password);

    }
    @When("The User clicks the Submit button")
    public void the_user_clicks_the_submit_button() {
        pages.formsPage().clickLoginButton();
    }

    @Then("The User waits until the Analysis element is visible with a timeout of {int} seconds")
    public void theUserWaitsUntilTheAnalysisElementIsVisibleWithATimeoutOfSeconds(Integer timeout) {
        pages.formsPage().verifyAnalysisElement(timeout);

    }

    String environment;
    @Given("The user opens {string} environment")
    public void theUserOpensEnvironment(String env) {

        Driver.getDriver().get(ConfigurationReader.getProperty(env));

        environment = env;
//        if (env.equals("PreProd")) {
//            Driver.getDriver().get("https://mediamarkt-ui-preprod.efectura.com/");
//        } else if (env.equals("Prod")) {
//            Driver.getDriver().get("https://mm-admin.efectura.com/");
//        }
    }


    @When("The user navigate the deal item")
    public void theUserNavigateTheDealItem() {

        if (environment.contains("prod")) {
            Driver.getDriver().get("https://crm-ui.spwgpf.com/Enrich/EditItem/" + formId);
        } else if (environment.contains("test")) {
            Driver.getDriver().get("https://sipay-ui.efectura.com/Enrich/EditItem/" + formId);
        }
        BrowserUtils.wait(3);
    }

    @Then("The user clicks {string} tab")
    public void the_user_clicks_tab(String tabName) {
        pages.formsPage().clickEditItemTab(tabName);
        BrowserUtils.wait(3);
    }

    @And("The user select {string} in {string} select filter")
    public void theUserSelectInSelectFilter(String selectOption, String selectFilter) {
        BrowserUtils.adjustScreenSize(70,Driver.getDriver());
        BrowserUtils.wait(3);
        pages.generalPage().selectOptionInSelectFilter(selectOption,selectFilter);
        BrowserUtils.wait(5);
    }

    @When("The user go in the filtered item")
    public void theUserGoInToFilteredItem() {
        pages.formsPage().getFilteredItemLink().click();
    }

    @When("The user verify deal and documents")
    public void theUserVerifyDealAndDocuments() {
        WebElement table = Driver.getDriver().findElement(By.id("association-table"));
        List<String> tableAssocValues = BasePage.getColumnData(table,"İlişkili");
        Assert.assertEquals(4,tableAssocValues.size());

//        int documentCount = Collections.frequency(familyValues, "Afiş Kapağı");
//        int dealCount = Collections.frequency(familyValues, "DEAL");

//        Assert.assertEquals("İlişkili Deal Sayısı 1'den farklı",1,dealCount);
//        Assert.assertEquals("İlişkili Document Sayısı yüklenenden farklı",3,documentCount);

    }


    String uniqueChatValue;
    @When("The user mention {string}")
    public void theUserMention(String mentionValue) {
        BrowserUtils.wait(2);
        uniqueChatValue = UUID.randomUUID().toString();
        System.out.println("uniqueChatValue: " + uniqueChatValue);
        pages.formsPage().getAppChatIcon().click();
        pages.formsPage().getAppChatInputBox().sendKeys(mentionValue);
        BrowserUtils.wait(4);
        pages.formsPage().getMentionOption().click();
        pages.formsPage().getAppChatInputBox().sendKeys(" " + uniqueChatValue);
        pages.formsPage().getAppChatMsgSubmitButton().click();
    }

    int notificationIndex;
    @When("The user verify notification")
    public void theUserVerifyNotification() {
        pages.formsPage().getNotificationIcon().click();
        BrowserUtils.wait(3);

        System.out.println(pages.formsPage().getNotificationValues());

        for (WebElement value : pages.formsPage().getNotificationValues()) {
            System.out.println(value.getText());
        }

        boolean contains = pages.formsPage().getNotificationValues().stream()
                .anyMatch(e -> e.getText().contains(uniqueChatValue.substring(0,15)));

        Assert.assertTrue("Notification gelmedi",contains);

        notificationIndex = -1;
        for (int i = 0; i < pages.formsPage().getNotificationValues().size(); i++) {
            if (pages.formsPage().getNotificationValues().get(i).getText().contains(uniqueChatValue.substring(0,15))) {
                notificationIndex = i;
                break;
            }
        }
        System.out.println("İçeren elemanın index'i: " + notificationIndex);

    }

    @When("The user verify link")
    public void theUserVerifyLink() {
        pages.formsPage().getMentionLinks().get(notificationIndex).click();
        BrowserUtils.wait(5);
        String navigatedItemId = Driver.getDriver().getCurrentUrl().split("/")[5];

        Assert.assertEquals("Link yönlendirmesi yanlış",formId,navigatedItemId.split("\\?")[0]);

    }


    @When("The user go to team dashboard")
    public void theUserGoToTeamDashboard() {
        if (environment.contains("prod")) {
            Driver.getDriver().get("https://crm-ui.spwgpf.com/Enrich/ReportCardAnalysis");
        } else if (environment.contains("test")) {
            Driver.getDriver().get("");
        }
        BrowserUtils.wait(15);
    }

    @When("The user take screenshot")
    public void theUserTakeScreenshot() {
//        BrowserUtils.setZoom(Driver.getDriver(),60);
        String chatId = "-4570445477";
        String testChatId = "-1002156506449";
        BrowserUtils.wait(20);
        String path = BrowserUtils.getScreenshot("dashboard");
        System.out.println("Path: " + path);
        BrowserUtils.sendFileToTelegram(path,chatId);

        Driver.getDriver().switchTo().frame(pages.formsPage().getCardReportDashboard());
        Driver.getDriver().switchTo().frame(0);

        for (int i = 1; i < 5; i++) {
            pages.formsPage().getTeamDashboardTabs().get(i).click();
            BrowserUtils.wait(4);

            path = BrowserUtils.getScreenshot(pages.formsPage().getTeamDashboardTabs().get(i).getText());
            System.out.println("Path: " + path);
            BrowserUtils.sendFileToTelegram(path,chatId);

        }


        Driver.getDriver().switchTo().parentFrame(); // içten dışa
        Driver.getDriver().switchTo().defaultContent();


    }

    @Then("The user verify {string} added")
    public void theUserVerifyAreaAdded(String filterName) {
        boolean allMatch = pages.searchPage().getSelectedSearchAreas().stream()
                .map(WebElement::getText)
                .allMatch(title -> title.toLowerCase().contains(filterName.toLowerCase()));
    }

    @When("The user click history in app")
    public void theUserClickHistoryInApp() {
        BrowserUtils.wait(5);
        pages.generalPage().getUserMenuButton().click();
        BrowserUtils.wait(2);
        pages.generalPage().getHistoryButton().click();
        BrowserUtils.wait(2);
    }

    List<WebElement> firstRowOfAppHistoryValues;
    List<WebElement> firstRowOfDealHistoryValues;
    List<WebElement> firstRowOfAccountHistoryValues;
    @When("The user get first row of {string} history")
    public void theUserGetFirstRowOfAppHistory(String historyType) {
        if (historyType.equalsIgnoreCase("app"))
            firstRowOfAppHistoryValues = pages.generalPage().getHistoryFirstRowValues();

        if (historyType.equalsIgnoreCase("deal"))
            firstRowOfDealHistoryValues = pages.generalPage().getHistoryFirstRowValues();

        if (historyType.equalsIgnoreCase("account"))
            firstRowOfAccountHistoryValues = pages.generalPage().getHistoryFirstRowValues();
    }

    @Then("The user verify item history values")
    public void theUserVerifyItemHistoryValues() {

        boolean appAndDeal = IntStream.range(0, firstRowOfAppHistoryValues.size())
                .allMatch(i -> firstRowOfAppHistoryValues.get(i).getText().trim()
                        .equals(firstRowOfDealHistoryValues.get(i).getText().trim()));

        boolean appAndAccount = IntStream.range(0, firstRowOfAppHistoryValues.size())
                .allMatch(i -> firstRowOfAppHistoryValues.get(i).getText().trim()
                        .equals(firstRowOfAccountHistoryValues.get(i).getText().trim()));

        Assert.assertTrue("App Tarihçe'de sorun var",appAndDeal || appAndAccount);


    }

    String downloadedFileName;
    @When("The user click uploaded document")
    public void theUserClickUploadedDocument() {
        BrowserUtils.wait(6);
        BrowserUtils.adjustScreenSize(55,Driver.getDriver());

        int index = IntStream.range(0, pages.searchPage().getUploadedDocumentNames().size())
                .filter(i -> pages.searchPage().getUploadedDocumentNames().get(i).getText().contains("xlsx"))
                .findFirst()
                .orElse(-1);

        downloadedFileName = pages.searchPage().getUploadedDocumentNames().
                get(index).getText().substring(1, pages.searchPage().getUploadedDocumentNames().get(index).getText().length() - 1);

        pages.searchPage().getUploadedDocumentLinks().get(index).click();
        BrowserUtils.wait(1);
    }

    @When("The user verify document is download")
    public void theUserVerifyDocumentIsDownload() {
        Assert.assertTrue("Dosya indirilemedi!",
                BrowserUtils.isNewExcelDownloaded(System.getProperty("user.home") + "/Downloads",8));

        String latestExcelFile = BrowserUtils.getLatestExcelFileName(System.getProperty("user.home") + "/Downloads");


//        Assert.assertEquals("indirlen dosyanın ismi eşleşmedi",downloadedFileName,latestExcelFile);

        downloadedFileName = downloadedFileName.replaceAll("\\s+", " ").trim();
        latestExcelFile = latestExcelFile.replaceAll("\\s+", " ").trim();

        Assert.assertTrue("indirilen dosya ismi eşleşmedi\nexpectedName: " + downloadedFileName + "\nActaulName: " + latestExcelFile,
                latestExcelFile.contains(downloadedFileName.split("\\.")[0]));


    }

    @Then("The user verify last updated list")
    public void theUserVerifyLastUpdatedList() {
        Assert.assertEquals("Oluşturulan form son güncellenenlere gelmedi",
                name,pages.searchPage().getLastUpdatedListTitles().get(0).getText());
    }

    String selectedRecordMemberName;
    String fullName;
    @When("The user get member name")
    public void theUserGetMemberName() {
        selectedRecordMemberName = pages.searchPage().getRecordTitles().get(0).getText();
        fullName = pages.searchPage().getFullNameValues().get(0).getText().split(":")[1].trim();
        System.out.println(fullName);

    }

    @Then("The user verify member name")
    public void theUserVerifyMemberName() {
        BrowserUtils.wait(1);
//        String actualMemberName = Driver.getDriver().findElement(By.id("memberName")).getText();
        String actualMemberName = BrowserUtils.getValueInInputBox(Driver.getDriver().findElement(By.id("memberName")));
        String actualName = BrowserUtils.getValueInInputBox(Driver.getDriver().findElement(By.id("firstName"))) + " " +
                BrowserUtils.getValueInInputBox(Driver.getDriver().findElement(By.id("LastName")));

        Assert.assertEquals("Girilen formun üye işyeri adı farklı",
                selectedRecordMemberName,actualMemberName);

        Assert.assertEquals("Girilen formun ad alanı farklı",
                fullName,actualName);

    }

    @When("The user click randevular tab")
    public void theUserClickRandevularTab() {
        pages.generalPage().getAppointmentsTab().click();
        BrowserUtils.wait(2);
    }

    @Then("The user verify randevu list contains the form")
    public void theUserVerifyRandevuListContainsTheForm() {
        boolean containsText = pages.searchPage().getRecordTitles().stream()
                .map(WebElement::getText)
                .anyMatch(text -> text.equalsIgnoreCase(name));

        boolean isFormDisplayed = BrowserUtils.isElementDisplayed
                (By.xpath("//div[contains(text(),'" + name + "')]"));

        Assert.assertTrue("Randevu listesi tarihi bugün olan bir kaydı içermiyor",
                isFormDisplayed);


    }

    @When("The user click other records button")
    public void theUserClickOtherRecordsButton() {
        pages.searchPage().getOtherRecordsButton().click();
        BrowserUtils.wait(1);
    }

    @When("The user click create new record button in table")
    public void theUserClickCreateNewRecordButtonInTable() {
        pages.searchPage().getCreateNewRecordButtons().get(0).click();
        BrowserUtils.wait(1);
    }

    String contractNote;
    @When("The user fill note area")
    public void theUserFillNoteArea() {
        WebElement noteInputBox = Driver.getDriver().findElement(By.cssSelector("div[contenteditable='true']"));
        contractNote = UUID.randomUUID().toString();
        noteInputBox.sendKeys(contractNote);
        System.out.println("NOTE: " + BrowserUtils.getValueInInputBox
                (Driver.getDriver().findElement(By.xpath("//div/div/div/div[2]/div/div/p"))));
        System.out.println("expected contract note: " + contractNote);
    }

    @Then("The user verify contract note")
    public void theUserVerifyContractNote() {
        String actual = Driver.getDriver().findElement(By.xpath("//div/div/div/div[2]/div/div/p")).getText();
        System.out.println("actual note: " + actual);
        softAssert.assertTrue(actual.contains(contractNote),
                "Contract note girilenden farklı formda");

//        Assert.assertEquals(actual, contractNote);

    }
}

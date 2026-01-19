package Efectura.stepDefs;

import Efectura.pages.BasePage;
import Efectura.utilities.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

import static Efectura.utilities.BrowserUtils.isElementDisplayed;

public class GeneralStepDefs extends BaseStep {
    SoftAssert softAssert = new SoftAssert();
    WebDriver driver = Driver.getDriver();

    String env;
    @Given("The user go to {string} environment")
    public void the_user_go_to_environment(String environment) {
        env = environment;
        Driver.getDriver().get(ConfigurationReader.getProperty(environment));
    }

    @Given("The user login")
    public void theUserLogin(DataTable dataTable) {
        Map<String,String> mapParam = dataTable.asMap(String.class, String.class);
        pages.generalPage().getUserNameInput().sendKeys(mapParam.get("username"));
        pages.generalPage().getPasswordInput().sendKeys(mapParam.get("password"));
        pages.generalPage().getLoginButton().click();
        BrowserUtils.waitForVisibility(pages.generalPage().getUserMenuButton(),40);
//        pages.generalPage().getWarningCloseButton().click();
        BrowserUtils.wait(5);
    }

    @Then("The user verify warning {string}")
    public void theUserVerifyWarning(String warningText) {
        BrowserUtils.wait(1);
        BrowserUtils.waitForVisibility(pages.generalPage().getWarningElement(),45);
        String url = Driver.getDriver().getCurrentUrl();
        System.out.println("Url: " + url);
        Assert.assertTrue("Url: " + url + "\nUyarı Gelmedi", isElementDisplayed(pages.generalPage().getWarningElement()));

        Assert.assertEquals("Mesaj farklı",warningText,pages.generalPage().getWarningElement().getText());
    }

    @When("The user click assigned records tab")
    public void theUserClickAssignedRecordsTab() {
        pages.generalPage().getAssignedRecordsButton().click();
        BrowserUtils.wait(5);
    }


    @When("The user select {string} as salesRep")
    public void theUserSelectEmreUzunAsSalesRep(String salesRepOption) {
        BrowserUtils.wait(3);
        SelectFilterUtils sf = new SelectFilterUtils(Driver.getDriver(), Duration.ofSeconds(10));

        WebElement select = Driver.getDriver().findElement(By.id("salesRep"));

        sf.choose(select,salesRepOption);

    }

    @Given("The user logout")
    public void theUserLogout() {
        BrowserUtils.wait(5);
        pages.generalPage().getUserMenuButton().click();
        BrowserUtils.wait(2);
        pages.generalPage().getLogoutButton().click();
        BrowserUtils.wait(2);
        BrowserUtils.waitForVisibility(pages.generalPage().getUserNameInput(),60);
    }

    @Then("The user verify previous page button passive")
    public void theUserVerifyPreviousPageButtonPassive() {
        Assert.assertFalse("İlk Sayfada previous page butonu aktif!!",
                BrowserUtils.isButtonActive(pages.generalPage().getPreviousPageButton()));
    }

    @When("The user click next page button")
    public void theUserClickNextPageButton() {
        pages.generalPage().getNextPageButton().click();
    }

    @Then("The user verify previous page button active")
    public void theUserVerifyPreviousPageButtonActive() {
        Assert.assertTrue("İlk Sayfada değilken previous page butonu pasif!!",
                BrowserUtils.isButtonActive(pages.generalPage().getPreviousPageButton()));
    }

    @Then("The user verify next button active")
    public void theUserVerifyNextButtonActive() {
        BrowserUtils.wait(1);
        Assert.assertTrue("İlk Sayfadayken next page butonu pasif!!",
                BrowserUtils.isButtonActive(pages.generalPage().getNextPageButton()));
    }

    @When("The user go to last page")
    public void theUserGoToLastPage() {

        String paginateInfo = pages.generalPage().getPaginateInfoText().getText();
        int first = Integer.parseInt(paginateInfo.split(" ")[1]);
        int last = Integer.parseInt(paginateInfo.split(" ")[3]);

        while (first != last) {
            pages.generalPage().getNextPageButton().click();
            BrowserUtils.waitForVisibility(pages.generalPage().getPaginateInfoText(),60);
            paginateInfo = pages.generalPage().getPaginateInfoText().getText();
            first = Integer.parseInt(paginateInfo.split(" ")[1]);
        }

    }

    @Then("The user verify next button passive")
    public void theUserVerifyNextButtonPassive() {
        Assert.assertFalse("Son Sayfadayken next page butonu aktif!!",
                BrowserUtils.isButtonActive(pages.generalPage().getNextPageButton()));
    }

    @When("The user go to {string} on navbar")
    public void theUserGoToProspectOnNavbar(String tabName) {
        pages.generalPage().getNavBarTabs().stream()
                .filter(el -> el.getText().equals(tabName))
                .findFirst()
                .ifPresent(WebElement::click);
    }


    @Then("The user verify form inputs clear")
    public void theUserVerifyFormInputsClear() {
        List<String> skipInputs = new ArrayList<>(List.of("location"));
        for (WebElement input : pages.formsPage().getFormInputs()) {
            if (skipInputs.contains(input.getAttribute("id"))) {
                continue;
            }
            Assert.assertEquals(input.getAttribute("id") + " input tewmizlenmemiş",
                    "", BrowserUtils.getValueInInputBox(input));

        }

    }

    @When("The user go to other tab")
    public void theUserGoToOtherTab() {
//        Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().stream()
//                .filter(h -> !h.equals(Driver.getDriver().getWindowHandle()))
//                .findFirst().get());

        // Tüm sekme pencerelerinin ID'lerini al
        List<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());

// Yeni sekmeye geçiş yap (örneğin ikinci sekme)
        Driver.getDriver().switchTo().window(tabs.get(1));

// İşlem tamamlandıktan sonra, önceki sekmeye (ilk sekme) dön ve kapat
        Driver.getDriver().switchTo().window(tabs.get(0));
        Driver.getDriver().close();

// Geri kalan açık sekmeye geç
        Driver.getDriver().switchTo().window(tabs.get(1));


        BrowserUtils.wait(3);

    }

    @When("The user open {string} info in form")
    public void theUserOpenProspectInfoInForm(String infoName) {
        BrowserUtils.wait(6);
        BrowserUtils.adjustScreenSize(40,Driver.getDriver());
        WebElement infoTab = Driver.getDriver().findElement(By.xpath("//button[contains(.,'" + infoName + "')]"));
//        BrowserUtils.safeClick(infoTab);
        infoTab.click();
    }


    @When("The user select {int} {int} {int} in {string}")
    public void theUserSelectInOfferDate(int day, int month, int year, String elementId) {
        BrowserUtils.wait(1);
        DateFilterUtils df = new DateFilterUtils(Driver.getDriver(), Duration.ofSeconds(10));
        df.selectCreatedAt(LocalDate.of(year, month, day),"//*[@id='" + elementId + "']");
        BrowserUtils.wait(2);

    }

    @When("The user wait {int} second")
    public void theUserWaitSecond(int secondAmount) {
        BrowserUtils.wait(secondAmount);
    }

    @When("The user enter {string} to taksit input")
    public void theUserEnterToTaksitInput(String value) {
        Driver.getDriver().findElement(By.id("installmentCount")).sendKeys(value);
    }

    @Then("The user verify minimum value warning")
    public void theUserVerifyMinimumValueWarning() {
        Assert.assertTrue("Eksi değerde uyarı gelmedi",
                BrowserUtils.isElementDisplayed(pages.generalPage().getMinimumValueWarning()));
    }

    @When("The user refresh the page")
    public void theUserRefreshThePage() {
        Driver.getDriver().navigate().refresh();
        BrowserUtils.wait(3);
        BrowserUtils.waitForVisibility(Driver.getDriver().findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/div[2]/fieldset")),45);
    }

    @Then("The user verify revise reason {string} for {string}")
    public void theUserVerifyReviseReason(String expectedValue, String page) {

        if (page.equalsIgnoreCase("risk")) {
            Assert.assertEquals("revize nedeni kaydedilenden farklı",
                    expectedValue,pages.generalPage().getReviseReasonSelectedValueForRisk().getText());
        }
        if (page.equalsIgnoreCase("operation")) {
            Assert.assertEquals("revize nedeni kaydedilenden farklı",
                    expectedValue,pages.generalPage().getReviseReasonSelectedValueForOperation().getText());
        }


    }

    @When("The user enter {string} into explanation in {string}")
    public void theUserEnterIntoExplanation(String value, String page) {
        if (page.equalsIgnoreCase("risk")) {
            pages.generalPage().getExplanationInputForRisk().sendKeys(value);
        }
        if (page.equalsIgnoreCase("operation")) {
            pages.generalPage().getExplanationInput().sendKeys(value);
        } else if (page.equalsIgnoreCase("prospect")) {
            pages.generalPage().getExplanationForProspect().sendKeys(value);
        }

    }

    @Then("The user verify explanation {string} for {string}")
    public void theUserVerifyExplanationTestNotu(String expectedValue, String page) {

        if (page.equalsIgnoreCase("risk")) {
            Assert.assertEquals("Note kısmı kaydedilenden farklı",
                    expectedValue,pages.generalPage().getExplanationInputForRisk().getText());
        }
        if (page.equalsIgnoreCase("operation")) {
            Assert.assertEquals("Note kısmı kaydedilenden farklı",
                    expectedValue,pages.generalPage().getExplanationInput().getText());
        }

    }

    @When("The user select date {int} {int} {int} in {string}")
    public void theUserSelectDateInNewAppointment(int day, int month, int year,String id) {
        Driver.getDriver().findElement(By.id(id)).click();
        BrowserUtils.wait(1);
        DateFilterUtils df = new DateFilterUtils(Driver.getDriver(), Duration.ofSeconds(10));
        String locate = "//*[@id='" + id + "']";
        df.selectCreatedAt(LocalDate.of(year, month, day), locate);
        BrowserUtils.wait(2);
    }


    @When("The user navigate to {string}")
    public void theUserNavigateToHttpsSipayappEfecturaCom(String url) {
        Driver.getDriver().navigate().to(url);
        BrowserUtils.wait(2);
    }

    @Then("The user verify {string} select filter with value {string} in {string}")
    public void theUserVerifySelectFilterWithValue(String columnName, String expectedValue, String table) {
        BrowserUtils.wait(2);
        WebElement tableElement = Driver.getDriver().findElement(By.id(table));
        List<String> values =  BasePage.getColumnData(tableElement,columnName);

        System.out.println(values);
        BrowserUtils.wait(7);
        for (String actualValue : values) {
            Assert.assertEquals(expectedValue,actualValue);
        }
    }

    @Given("The user go to {string} and measure load time")
    public void theUserGoToProdAppAndMeasureLoadTime(String pageName) {
        Duration timeDuration = BrowserUtils.navigateAndMeasure(Driver.getDriver(),ConfigurationReader.getProperty(pageName));
        double sec = timeDuration.toNanos() / 1_000_000_000.0;
        System.out.println("Sec: " + sec);
        BrowserUtils.wait(2);
        softAssert.assertTrue(sec <= 2,"Login ekrani 2 saniyeden gec geldi (" + sec + " saniye)");
    }

    @Then("The user click submit button and mesaure time")
    public void theUserClickSubmitButtonAndMesaureTime() {
        Duration timeDuration = BrowserUtils.clickAndMeasureFullNavigation(Driver.getDriver(),pages.formsPage().getLoginButton());
        double sec = timeDuration.toNanos() / 1_000_000_000.0;
        System.out.println("Sec: " + sec);
        BrowserUtils.wait(2);
        softAssert.assertTrue(sec <= 2,"Giris ekrani 2 saniyeden gec geldi (" + sec + " saniye)");
    }

    @Then("The user click {string} in quick access and measure time")
    public void theUserClickDigitalAssetInQuickAccessAndMeasureTime(String tabName) {
        WebElement target = pages.generalPage().getFletumQuickAccessTabs().stream()
                .filter(el -> el.getText().trim().equals(tabName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tab bulunamadı!"));

        Duration timeDuration = BrowserUtils.clickAndMeasureFullNavigation(Driver.getDriver(),target);
        double sec = timeDuration.toNanos() / 1_000_000_000.0;
        System.out.println("Sec: " + sec);
        BrowserUtils.wait(2);

        softAssert.assertTrue(sec <= 2,"Deal overview ekrani 2 saniyeden gec geldi (" + sec + " saniye)");

    }

    @Then("The user click edit item button and measure time")
    public void theUserClickEditItemButtonAndMeasureTime() {
        BrowserUtils.adjustScreenSize(70,Driver.getDriver());
        BrowserUtils.wait(10);
        Duration timeDuration = BrowserUtils.clickAndMeasureFullNavigation(Driver.getDriver(),pages.generalPage().getEditButton());
        double sec = timeDuration.toNanos() / 1_000_000_000.0;
        System.out.println("Sec: " + sec);
        BrowserUtils.wait(2);
        softAssert.assertTrue(sec <= 2,"Edit Item ekrani 2 saniyeden gec geldi (" + sec + " saniye)");
    }

    @Given("The user assert all 2")
    public void theUserAssertAll() {
        softAssert.assertAll();
    }

    @Then("The user verify two factor authentication")
    public void theUserVerifyTwoFactorAuthentication() {
        By twoFactorTextLocate = By.xpath("//p[contains(.,'Authentication Code')]");
        Assert.assertTrue("Two Factor Yönlendirmesi Olmadı!!",BrowserUtils.isElementDisplayed(twoFactorTextLocate));
    }

    @Given("The user navigate to import page")
    public void theUserNavigateToImportPage() {
        if (env.equalsIgnoreCase("prod-fletum")) {
            Driver.getDriver().get("https://crm-ui.spwgpf.com/Import");
        } else if (env.equalsIgnoreCase("test-fletum")) {
            Driver.getDriver().get("https://sipay-ui.efectura.com/Import");
        }
        BrowserUtils.wait(3);
    }

    @And("The user accepts import popup")
    public void theUserAcceptsImportPopup() {
        //button[contains(.,'Understood')]
        Driver.getDriver().findElement(By.xpath("//button[contains(text(),'Understood')]")).click();
    }


    @When("The user select {string} for importType")
    public void theUserSelectForImportType(String importType) {
        pages.generalPage().selectImportType(importType);
        BrowserUtils.wait(2);
    }

    @When("The user upload the {string} file")
    public void theUserUploadTheFile(String fileName) {
        Driver.getDriver().findElement(By.xpath("//button[contains(@id,'Import')]")).click();
        BrowserUtils.wait(2);
        pages.generalPage().getItemImportInput().sendKeys(CommonExcelReader.getExcelPath(fileName));
        BrowserUtils.wait(2);
        Driver.getDriver().findElement(By.xpath("//button[@id='import-file-btn']")).click();
    }

    @When("The user upload {string} file")
    public void theUserUploadFile(String fileName) {
        pages.generalPage().uploadExcelFile(fileName);

    }

    @When("The user import the uploaded file")
    public void theUserImportTheUploadedFile() {
        BrowserUtils.wait(2);
        BrowserUtils.waitForVisibility(pages.generalPage().getImportButton(),10);
        pages.generalPage().getImportButton().click();
        BrowserUtils.wait(4);
    }

    @When("The user go to attribute page")
    public void theUserGoToAttributePage() {

        if (env.contains("prod")) {
            Driver.getDriver().get("https://crm-ui.spwgpf.com/Settings/Attributes");
        } else if (env.contains("test")) {
            Driver.getDriver().get("https://sipay-ui.efectura.com/Settings/Attributes");
        }
        BrowserUtils.wait(3);
    }

    @When("The user click import button")
    public void theUserClickImportButton() {
        BrowserUtils.wait(5);
        pages.generalPage().getItemImportButton().click();
    }

    @When("The user upload the {string} file2")
    public void theUserUploadTheFile2(String fileName) {
        Driver.getDriver().findElement(By.xpath("//button[contains(@id,'Import')]")).click();
        BrowserUtils.wait(2);
        pages.generalPage().getItemImportInput().sendKeys(CommonExcelReader.getExcelPath(fileName));
        BrowserUtils.wait(2);
    }

    @When("The user import attribute file")
    public void theUserImportAttributeFile() {
        pages.generalPage().getItemImportStep2NextButton().click();
        BrowserUtils.wait(2);
        Driver.getDriver().findElement(By.xpath("//button[@id='import-step-edit']")).click();
        BrowserUtils.wait(1);
        pages.generalPage().getApplyImportValidationButton().click();
        BrowserUtils.wait(10);
    }

    @Then("The user verifies that attributes are created")
    public void theUserVerifiesThatAttributesAreCreated() {
        boolean isCreated = pages.generalPage().isAttributeCreated();
    }

    @Then("The user tear down all changes in Attribute Case")
    public void theUserTearDownAllChangesInAttributeCase() {
        pages.generalPage().deleteTestAttributes();
    }


    @Given("The user go to {string} overview page")
    public void theUserGoToFileOverviewPage(String itemName) {
        pages.generalPage().goToItemOverviewPage(itemName);
    }

    String firstItemId;
    @When("The user get first item id")
    public void theUserGetFirstItemId() {
        WebElement table = Driver.getDriver().findElement(By.id("items"));
        List<String> contactIds = BasePage.getColumnData(table,"Fletum Kimlik");
        System.out.println("İlk: " + contactIds.get(0));
        firstItemId = contactIds.get(0);
    }

    @When("The user select first item")
    public void theUserSelectFirstItem() {
        Driver.getDriver().findElement(By.xpath("//input[@name='" + firstItemId + "']/following-sibling::label")).click();
    }

    String secondItemId;
    @When("The user add association to first item")
    public void theUserAddAssociationToFirstItem() {
        WebElement table = Driver.getDriver().findElement(By.id("representative-table"));
        Driver.getDriver().findElement(By.xpath("//button[@id='addAssociation']")).click();
        BrowserUtils.wait(1);
        Driver.getDriver().findElement(By.xpath("//a[contains(text(),'Sharing')]")).click();
        BrowserUtils.wait(5);
        List<String> dealIds = BasePage.getColumnData(table,"Öğe Kimliği");
        System.out.println("deal id: " + dealIds.get(0));
        secondItemId = dealIds.get(0);
        Driver.getDriver().findElement(By.xpath("//input[@value='" + secondItemId + "']/following-sibling::label")).click();
        Driver.getDriver().findElement(By.xpath("//button[@id='confirm-representative']")).click();
    }

    @Then("The user verifies info {string} appears")
    public void theUserVerifiesInfoAppears(String expectedMessage) {
        BrowserUtils.wait(1);
        BrowserUtils.waitForVisibility(pages.generalPage().getInfoMessage(),60);
        Assert.assertTrue(pages.generalPage().getInfoMessage().getText().contains(expectedMessage));
        BrowserUtils.wait(1);
    }

    @Then("The user tear down all changes in bulk assoc")
    public void theUserTearDownAllChangesInBulkAssoc() {
        pages.generalPage().deleteTestAssoc(firstItemId,secondItemId);
    }

    @Then("The user verify assoc is ok")
    public void theUserVerifyAssocIsOk() {
        pages.generalPage().verfiyAssocIsOk(firstItemId,secondItemId);
    }

    @Given("The user go to {string} page")
    public void theUserGoToPage(String pageName) {
        BrowserUtils.wait(2);
        Driver.getDriver().get(ConfigurationReader.getProperty(pageName));
    }

    @Given("The user go in {string} flow")
    public void theUserGoInFlow(String formName) {
        pages.generalPage().goInFlow(formName);
    }

    @Given("The user select {string} as form type")
    public void theUserSelectAsFormType(String type) {
        driver.findElement(By.xpath("//span[@id='select2-senaryoTipi-container']")).click();
        driver.findElement(By.xpath("//li[.='" + type + "']")).click();
    }

    @When("The user fill description")
    public void theUserFillDescription() {
        driver.findElement(By.xpath("//textarea[@id='descriptionArea']")).
                sendKeys("Açıklama - " + UUID.randomUUID().toString());
    }

    @When("The user select doc type")
    public void theUserSelectDocType() {
        WebElement docSelect = driver.findElement(By.xpath("//select[@id='docType']"));
        BrowserUtils.selectDropdownOptionByVisibleText(docSelect,"Dilekçe");
    }

    @When("The user upload file")
    public void theUserUploadFile() {
        String projectRoot = System.getProperty("user.dir");
        Path docPath = Paths.get(projectRoot, "src", "test", "resources", "features", "testDocument.xlsx");
        String absoluteFilePath = docPath.toFile().getAbsolutePath();
        driver.findElement(By.xpath("//input[@id='fileInput']")).sendKeys(absoluteFilePath);
    }

    @When("The user verify file is uploaded")
    public void theUserVerifyFileIsUploaded() {
        Assert.assertTrue(BrowserUtils.isElementDisplayed(By.xpath("//tr/td[.='Dilekçe']")));
    }

    @When("The user click submit form")
    public void theUserClickSubmitForm() {
        BrowserUtils.adjustScreenSize(65,driver);
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Given("The user click {string} flow")
    public void theUserClickFlow(String flow) {
        driver.findElement(By.xpath("//td[.='" + flow + "']")).click();
    }

    @Given("The user click first flow")
    public void theUserClickFirstFlow() {
        BrowserUtils.wait(1);
        BrowserUtils.adjustScreenSize(60,driver);
        driver.findElement(By.xpath("//*[@id=\"process-detail\"]/tbody/tr[1]/td[10]/div/a[1]")).click();
        BrowserUtils.wait(1);
        BrowserUtils.switchToTabByTitleAndCloseOld("SIPAY: ConfirmationForm");
        System.out.println("Title: " + Driver.getDriver().getTitle());
    }

    @Given("The user submit the task")
    public void theUserSubmitTheTask() {
        BrowserUtils.wait(2);
        pages.generalPage().submitTask();
//        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
//        BrowserUtils.wait(12);
    }
}

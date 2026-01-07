package Efectura.pages;

import Efectura.utilities.*;
import Efectura.utilities.Driver;
import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.*;
import java.util.List;

import static Efectura.utilities.CommonExcelReader.getExcelPath;

@Getter
public class GeneralPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userNameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//main/div/div/div[1]/button[2]")
    private WebElement assignedRecordsButton;

    @FindBy(xpath = "/html/body/div/div/main/div/div/div[1]/button[3]")
    private WebElement appointmentsTab;

    @FindBy(xpath = "//img[@alt='Sipay Logo']")
    private WebElement sipayLogo;

    //div[contains(@class,'Toastify__toast--')]
    @FindBy(xpath = "//div[contains(@class,'Toastify__toast--')]")
    private WebElement warningElement;

    @FindBy(xpath = "/html/body/div/div/main/section/div/div/button")
    private WebElement warningCloseButton;

    @FindBy(xpath = "//button[@id='user-menu-button']/following::button[contains(text(),'Çıkış Yap')][1]")
    private WebElement logoutButton;

    @FindBy(xpath = "//button[@id='user-menu-button']/following::button[contains(text(),'Geçmiş')][1]")
    private WebElement historyButton;

    @FindBy(xpath = "//*[@id='user-menu-button']/span[1]")
    private WebElement userMenuButton;

    @FindBy(xpath = "//div[contains(@id,'-content-')]/div[3]/div[3]/div")
    private WebElement tableNoContentText;

    @FindBy(xpath = "//*[contains(@id,'-content-')]/div[3]/div[4]/div[2]/button[1]")
    private WebElement previousPageButton;

    @FindBy(xpath = "//*[contains(@id,'-content-')]/div[3]/div[4]/div[2]/button[2]")
    private WebElement nextPageButton;

    @FindBy(xpath = "//*[contains(@id,'-content-')]/div[3]/div[4]/div[2]/span")
    private WebElement paginateInfoText;

    @FindBy(xpath = "//*[@id='root']/div/header/div/nav/a")
    private List<WebElement> navBarTabs;

    @FindBy(xpath = "//input[@id='email']/following-sibling::p")
    private WebElement invalidEmailWarning;

    @FindBy(xpath = "//input[@id='phone']/following-sibling::p")
    private WebElement invalidPhoneWarning;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/div[2]/fieldset/div/div[5]/div/div/div/div[2]/div/div/p")
    private WebElement explanationInput;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/div[2]/form/fieldset/div/div[13]/div/div/div/div[2]/div/div/p")
    private WebElement explanationForProspect;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/div[2]/fieldset/div/div[4]/div/div/div/div[2]/div/div")
    private WebElement explanationInputForRisk;

    @FindBy(xpath = "//input[@id='customerInfoForm']")
    private WebElement documentInput;

    @FindBy(xpath = "//button[@id='addDocumentButton']")
    private WebElement addDocumentButton;

    @FindBy(xpath = "//button[contains(text(),'Mevcut Dosyayı Kullan')]")
    private WebElement useAlreadyAddedFileButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div/div/div")
    private List<WebElement> companyTypeOptions;

    @FindBy(xpath = "//div[2]/fieldset/div[4]/div[2]/div/div/div[1]/ul/li")
    private List<WebElement> actualRequiredDocuments;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/div[2]/fieldset/div[4]/div[1]/button/span")
    private WebElement requiredDocumentsButton;

    @FindBy(xpath = "//p[contains(text(),'Validasyon Minimum Değer')]")
    private WebElement minimumValueWarning;

    @FindBy(xpath = "//*[@id=\"reviseReasonOperation\"]/span")
    private WebElement reviseReasonSelectedValueForOperation;

    @FindBy(xpath = "//*[@id=\"reviseReason\"]/span")
    private WebElement reviseReasonSelectedValueForRisk;

    @FindBy(xpath = "//input[@placeholder='Ara...']")
    private WebElement selectSearchInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div/div/div/span")
    private List<WebElement> selectOptions;

    @FindBy(xpath = "//tbody/tr[1]/td")
    private List<WebElement> historyFirstRowValues;

    @FindBy(xpath = "//div[@class='home-page-item']/span")
    private List<WebElement> fletumQuickAccessTabs;

    @FindBy(xpath = "//tbody/tr/td[25]/a[4]")
    private WebElement editButton;

    @FindBy(xpath = "//span[@id='select2-ItemType-container']")
    private WebElement selectImportTypeElement;

    @FindBy(xpath = "//span/span/span[1]/input")
    private WebElement selectImportTypeInputBox;

    @FindBy(xpath = "/html/body/span/span/span[2]/ul/li")
    private List<WebElement> filteredImportTypeOptions;

    @FindBy(xpath = "//input[@id='file-import']")
    private WebElement itemImportInput;

    @FindBy(id = "addcsvfile")
    private WebElement addCsvInputElement;

    @FindBy(xpath = "//button[@id='cancelUploadFile']/following-sibling::button[1]")
    private WebElement saveChangesButtonInAreYouSureModal;

    @FindBy(xpath = "//button[@id='tableDetailImport-Import']")
    private WebElement importButton;

    @FindBy(xpath = "//button[@id='importUser']")
    private WebElement itemImportButton;

    @FindBy(xpath = "//button[@id='import-step-two']")
    private WebElement itemImportStep2NextButton;

    @FindBy(xpath = "//button[@id='import-step-three']")
    private WebElement itemImportStep3NextButton;

    @FindBy(xpath = "//button[@id='open-match-columns']")
    private WebElement matchColumnsButton;

    @FindBy(xpath = "//button[@id='save-match-columns']")
    private WebElement saveMatchColumnsButton;

    @FindBy(xpath = "//button[@id='apply-details-button']")
    private WebElement applyImportValidationButton;

    @FindBy(xpath = "//button[@id='start-import']")
    private WebElement lastImportButton;

    @FindBy(xpath = "//div[@class='notyf__message']")
    private WebElement infoMessage;

    public void uploadExcelFile(String fileName) {
        BrowserUtils.wait(2);
        addCsvInputElement.sendKeys(getExcelPath(fileName));
        BrowserUtils.wait(2);
        Driver.getDriver().findElement(By.xpath("//button[@title='İçe aktarım']")).click();
        saveChangesButtonInAreYouSureModal.click();
    }


    private final List<String> adiAndXbRequiredDocuments = List.of("Kimlik","Üye İşyeri Bilgi Formu","Oran Şablonu");
    private final List<String> sahisRequiredDocuments = List.of("Kimlik","Vergi Levhası","İmza Beyannamesi","İkametgah Belgesi","Üye İşyeri Bilgi Formu","Oran Şablonu");
    private final List<String> limitedandAnonimRequiredDocuments = List.of("Kimlik","Vergi Levhası","İmza Sirküleri","Ticaret Sicil Gazetesi","Üye İşyeri Bilgi Formu","Oran Şablonu");
    private final List<String> dernekRequiredDocuments = List.of("Kimlik","Vergi Kimlik Numarası","İmza Sirküleri","Dernek Tüzüğü","Üye İşyeri Bilgi Formu","Karar Defteri","Oran Şablonu");
    private final List<String> vakifRequiredDocuments = List.of("Kimlik","Vergi Kimlik Numarası","İmza Sirküleri","Vakıf Senedi","Üye İşyeri Bilgi Formu","Karar Defteri");


    // you can use selectFilter as IsAssociated, Family, ItemStatuses in feature file
    public void selectOptionInSelectFilter(String selectOption, String selectFilter) {

        WebElement selectElement = driver.findElement(By.xpath("//select[contains(@id,'-" + selectFilter + "')]"));
        BrowserUtils.selectDropdownOptionByVisibleText(selectElement,selectOption);
        BrowserUtils.wait(3);
    }

    public void selectImportType(String importType) {
        BrowserUtils.wait(3);
        selectImportTypeElement.click();
        selectImportTypeInputBox.sendKeys(importType);
        BrowserUtils.wait(2);
        for (WebElement option : filteredImportTypeOptions) {
            if (option.getText().equals(importType)) {
                option.click();
                break;
            }
        }
    }


    public boolean isAttributeCreated() {
        String query = "SELECT * FROM Attributes\n" +
                "WHERE Code LIKE '%Test Automation%'";

        String code = null;
        int i = 0;

        try (Connection conn = DatabaseManager.getConnection(DbConfigs.PREPROD_SQLSERVER, DbConfigs.PREPROD_SQLSERVER_USERNAME, DbConfigs.DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                code = rs.getString("Code");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Code: " + code);

        return i != 0;

    }

    public void deleteTestAttributes() {
        String query = "DELETE FROM Attributes WHERE Code LIKE '%TestAutomation%'";

        try (Connection conn = DatabaseManager.getConnection(DbConfigs.PREPROD_SQLSERVER, DbConfigs.PREPROD_SQLSERVER_USERNAME, DbConfigs.DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

//            ps.setString(1, "%Test Automation%");

            int affectedRows = ps.executeUpdate();
            System.out.println("Silinen kayıt sayısı: " + affectedRows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goToItemOverviewPage(String item) {
        driver.get(ConfigurationReader.getProperty("itemLinkWithoutItemNamePreprod") + item);
    }

    public void deleteTestAssoc(String firstItemId, String secondItemId) {
        String query = "DELETE FROM Associations WHERE FirstItemId = " + firstItemId + "  AND SecondItemId =   " + secondItemId;

        try (Connection conn = DatabaseManager.getConnection(DbConfigs.PREPROD_SQLSERVER, DbConfigs.PREPROD_SQLSERVER_USERNAME, DbConfigs.DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

//            ps.setString(1, "%Test Automation%");

            int affectedRows = ps.executeUpdate();
            System.out.println("Silinen kayıt sayısı: " + affectedRows);
            Assert.assertEquals("Silinen Kayıt birden farklı!",1,affectedRows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verfiyAssocIsOk(String firstItemId, String secondItemId) {
        String query = "SELECT AssociationTypeId FROM Associations WHERE FirstItemId = " + firstItemId + "  AND SecondItemId =   " + secondItemId;

        int assocTypeId = 0;

        try (Connection conn = DatabaseManager.getConnection(DbConfigs.PREPROD_SQLSERVER, DbConfigs.PREPROD_SQLSERVER_USERNAME, DbConfigs.DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                assocTypeId = rs.getInt("AssociationTypeId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("AssociationTypeId: " + assocTypeId);

        Assert.assertEquals("AssociationTypeId 282'den farklı ya da assoc yok",282,assocTypeId);

    }
}

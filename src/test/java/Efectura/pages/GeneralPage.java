package Efectura.pages;

import Efectura.utilities.BrowserUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(xpath = "//div[2]/div/div[1]/div[3]/div[4]/div[2]/button[1]")
    private WebElement previousPageButton;

    @FindBy(xpath = "//div[2]/div/div[1]/div[3]/div[4]/div[2]/button[2]")
    private WebElement nextPageButton;

    @FindBy(xpath = "//div[2]/div/div[1]/div[3]/div[4]/div[2]/span")
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



}

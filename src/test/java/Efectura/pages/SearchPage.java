package Efectura.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

@Getter
public class SearchPage extends BasePage {

    @FindBy(xpath = "//input[contains(@id,'earch')]")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@title='Clear Search']")
    private WebElement searchInputResetButton;

    @FindBy(xpath = "//*[contains(@id,'content-')]/div[3]/div[3]/div/div[1]/div[1]")
    private List<WebElement> recordTitles;

    @FindBy(xpath = "//*[contains(@id,'content-')]/div[3]/div[2]/div[1]/button")
    private WebElement dealStatusSelect;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[1]/input")
    private WebElement dealStatusSearchInput;

    @FindBy(xpath = "//span[@class='truncate']")
    private List<WebElement> dealStatusOptions;

    @FindBy(xpath = "//*[contains(@id,'-content-')]/div[3]/div[3]/div/div/div[2]/div[2]/div[2]")
    private List<WebElement> dealStatusValuesResult;

    @FindBy(xpath = "//*[contains(@id,'-content-')]/div[3]/div[2]/div[1]/button[2]")
    private WebElement dealStatusResetButton;

    @FindBy(xpath = "//*[contains(@id,'-content-')]/div[3]/div[2]/div[1]/button/span")
    private WebElement selectedDealStatusText;

    @FindBy(xpath = "//*[contains(@id,'content-search')]/div[3]/div[1]/div/button[1]")
    private WebElement createDateSortButton;

    @FindBy(xpath = "//*[contains(@id,'-content-')]/div[3]/div[3]//div[1]/div[2]/div[1]/div[4]")
    private List<WebElement> createDateValues;

    @FindBy(xpath = "//div[3]/div[2]/div[2]/div/button/div")
    private WebElement createDateResetFilter;

    @FindBy(xpath = "//*[contains(@id,'-content-')]/div[3]/div[2]/div[2]//div/button/span")
    private WebElement selectedCreateDateText;

    @FindBy(xpath = "//*[@id='root']/div/main/div/div/div[1]/div/button")
    private WebElement createNewRecordButton;

    @FindBy(xpath = "//*[contains(@id,'-content-')]/div[1]/div/div/div[1]/button[2]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[contains(@id,'content-')]/div[3]/div[3]/div/div[2]/button[1]")
    private List<WebElement> goToRelatedRecordButtons;

    @FindBy(xpath = "//*[contains(@id,'content-')]/div[3]/div[2]/div[2]/div//button")
    private WebElement createDateFilter;

    @FindBy(xpath = "//button[contains(@aria-label,'Today')]")
    private WebElement todayDateOption;

    @FindBy(xpath = "/html/body/div/div/main/section/div/div[2]")
    private WebElement recordWarning;

    @FindBy(xpath = "//div[@class='grid grid-cols-1 md:grid-cols-2 md:gap-1']/div/div")
    private List<WebElement> searchPageAllRecordsValues;

    @FindBy(xpath = "//div[@role='textbox']/p[@data-placeholder='Revize açıklamasını giriniz...']")
    private WebElement reviseExplanationTextInput;

    @FindBy(xpath = "//label[normalize-space(text())='Operasyon Revize Açıklaması']/following-sibling::div//div//div//div//div//p")
    private WebElement operationReviseReason;

    @FindBy(xpath = "//label[normalize-space(text())='Risk Revize Açıklaması']/following-sibling::div//div//div//div//div//p")
    private WebElement riskReviseReason;

    @FindBy(xpath = "//div/div[1]/div[1]/div/h3")
    private WebElement formNameText;

    @FindBy(xpath = "//button[contains(.,'Arama Alanları')]")
    private WebElement searchAreaButton;

    @FindBy(xpath = "//div[2]/div/div/div[3]/div/button")
    private WebElement searchAreaCloseButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div/div[2]/span")
    private List<WebElement> searchAreaOptions;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div[2]/div")
    private List<WebElement> selectedSearchAreaIcons;

    @FindBy(xpath = "//nav/div/div/div[2]/button")
    private WebElement languageButton;

    @FindBy(xpath = "//div[1]/div[1]/div/div/div[2]/div/span")
    private List<WebElement> selectedSearchAreas;

    @FindBy(xpath = "/html/body/div[3]/div[2]/div/div/div[2]/div/span[1]")
    private List<WebElement> historyAttributes;

    @FindBy(xpath = "//div[3]/div[2]/div/button")
    private WebElement redReasonSelectButton;

    @FindBy(xpath = "//div[2]/div/div/div[1]/div/input")
    private WebElement redReasonSelectInput;

    @FindBy(xpath = "//div/div/div[2]/button")
    private List<WebElement> redReasonSelectOptions;

    @FindBy(xpath = "//div[contains(text(),'Kayıt bulunamadı')]")
    private WebElement tableInfo;

    @FindBy(xpath = "//div[2]/div/div[1]/div[3]/div[1]/h2")
    private WebElement searchResultHeader;

    @FindBy(xpath = "//div[2]/div[11]/div[4]/div[1]/div/div/a")
    private List<WebElement> uploadedDocumentLinks;

    @FindBy(xpath = "//div[2]/div[11]/div[4]/div[1]/div/div/span")
    private List<WebElement> uploadedDocumentNames;


    public static boolean isSortedAscending(List<String> dateStrings, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return IntStream.range(0, dateStrings.size() - 1)
                .allMatch(i -> {
                    LocalDateTime current = LocalDateTime.parse(dateStrings.get(i).split(" ")[2] + " "
                            + dateStrings.get(i).split(" ")[3], formatter);
                    LocalDateTime next = LocalDateTime.parse(dateStrings.get(i + 1).split(" ")[2] + " "
                            + dateStrings.get(i + 1).split(" ")[3], formatter);
                    return !current.isAfter(next);
                });
    }

    public static boolean isSortedDescending(List<String> dateStrings, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return IntStream.range(0, dateStrings.size() - 1)
                .allMatch(i -> {
                    LocalDateTime current = LocalDateTime.parse(dateStrings.get(i).split(" ")[2] + " "
                            + dateStrings.get(i).split(" ")[3], formatter);
                    LocalDateTime next = LocalDateTime.parse(dateStrings.get(i + 1).split(" ")[2] + " "
                            + dateStrings.get(i + 1).split(" ")[3], formatter);
                    return !current.isBefore(next);
                });
    }

}

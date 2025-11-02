package Efectura.pages;

import Efectura.utilities.BrowserUtils;
import Efectura.utilities.ConfigurationReader;
import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class FormsPage extends BasePage {

    @FindBy(xpath = "//fieldset/div/div/div/button[@aria-haspopup='dialog']")
    private List<WebElement> formSelects;

    @FindBy(xpath = "//div/div/input[contains(@placeholder,'Değer Giriniz')]")
    private List<WebElement> formInputs;

    @FindBy(xpath = "//*[@id='root']/div/main/div/div/div[2]/form/div[2]/button[2]")
    private WebElement formSaveButton;

    @FindBy(xpath = "//*[@id='root']/div/main/div/div/div[2]/form/div[2]/button[3]")
    private WebElement formCompleteButton;

    @FindBy(id = "Username")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@id='password-field']")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@id='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@id='analysisTab']")
    private WebElement analysis;

    @FindBy(xpath = "//div[contains(@class,'new_container')]/ul[contains(@class,'nav nav-tabs')]/li/a[@data-toggle='tab']")
    private List<WebElement> editItemTabs;

    @FindBy(xpath = "//tbody/tr/td[1]/a")
    private WebElement filteredItemLink;

    @FindBy(xpath = "/html/body/div/div/div/div")
    private WebElement appChatIcon;

    @FindBy(xpath = "//input[@placeholder='Mesajınızı girin']")
    private WebElement appChatInputBox;

    @FindBy(xpath = "//div[2]/div[3]/div/button")
    private WebElement appChatMsgSubmitButton;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div[3]/div[1]")
    private WebElement fletumChatIcon;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div[3]/div[2]/div[3]/div/input")
    private WebElement fletumChatInputBox;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div[3]/div[2]/div[3]/button")
    private WebElement fletumChatMsgSubmitButton;

    @FindBy(xpath = "/html/body/div[2]/nav/div/div[3]/form/ul/li[2]/a")
    private WebElement notificationIcon;

    @FindBy(xpath = "//a[contains(@href,'/Enrich/EditItem/')]")
    private List<WebElement> mentionLinks;

    @FindBy(xpath = "//div[contains(@id,'notification')]/div/div/div/p")
    private List<WebElement> notificationValues;

    @FindBy(xpath = "//div[text()='Test Sipay']")
    private WebElement mentionOption;

    @FindBy(xpath = "//div[@role='tab']")
    private List<WebElement> teamDashboardTabs;

    @FindBy(xpath = "//iframe[@id='cardReportDashboard']")
    private WebElement cardReportDashboard;

    @FindBy(xpath = "//*[@id=\"my-superset-container\"]/iframe")
    private WebElement cardReportSubIframe;


    public void setUsernameField(String username) {
        if (username.equalsIgnoreCase("validUsername")) {
            usernameField.sendKeys(ConfigurationReader.getProperty("validUsername"));
        } else if (username.equalsIgnoreCase("invalidUsername")) {
            usernameField.sendKeys(ConfigurationReader.getProperty("invalidUsername"));
        } else {
            usernameField.sendKeys(ConfigurationReader.getProperty(username));
        }
    }

    public void setPasswordField(String password) {
        // driver.switchTo().frame(iframe);
        if (password.equalsIgnoreCase("validPassword")) {
            passwordField.sendKeys(ConfigurationReader.getProperty("validPassword"));
        } else if (password.equalsIgnoreCase("invalidPassword")) {
            passwordField.sendKeys(ConfigurationReader.getProperty("invalidPassword"));
        } else {
            passwordField.sendKeys("");
            passwordField.sendKeys(ConfigurationReader.getProperty(password));
        }
        //  driver.switchTo().defaultContent();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void verifyAnalysisElement(int timeout) {
        BrowserUtils.waitForVisibility(analysis, timeout);
        Assert.assertTrue(analysis.isDisplayed());
        analysis.click();
    }

    public void clickEditItemTab(String tabName) {
        BrowserUtils.wait(5);
        for (WebElement editItemTab : editItemTabs) {
            if (editItemTab.getText().contains(tabName)) {
                editItemTab.click();
                BrowserUtils.wait(2);
            }
        }
        BrowserUtils.adjustScreenSize(75,driver);
    }

}

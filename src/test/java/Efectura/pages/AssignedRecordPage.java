package Efectura.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AssignedRecordPage extends BasePage {

    @FindBy(xpath = "//*[contains(@id,'content-')]/div[1]/div/div[1]/div/div/label/div[2]")
    private WebElement assignedOrCreatedCheckBox;

    @FindBy(xpath = "//div[2]/div/div[2]/div[1]/div/div[1]/div/div/button[1]/span")
    private WebElement myCreatedRecordsButton;

    @FindBy(xpath = "//div[2]/div/div[2]/div[1]/div/div[1]/div/div/button[2]/span")
    private WebElement assignedToMeButton;

}

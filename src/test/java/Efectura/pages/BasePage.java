package Efectura.pages;

import Efectura.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public BasePage() {
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static List<String> getColumnData(WebElement table, String header) {
        List<String> columnData = new ArrayList<>();

        // Get all headers
        List<WebElement> headers = table.findElements(By.xpath(".//thead/tr[1]/th"));

        // Find the index of the desired header
        int columnIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equals(header)) {
                columnIndex = i + 1; // XPath indexes start from 1
                break;
            }
        }

        if (columnIndex == -1) {
            throw new RuntimeException("Header not found: " + header);
        }

        // Get all rows
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

        // Extract data from the specified column
        for (WebElement row : rows) {
            WebElement cell = row.findElement(By.xpath(".//td[" + columnIndex + "]"));
            columnData.add(cell.getText().trim());
        }

        return columnData;
    }

}

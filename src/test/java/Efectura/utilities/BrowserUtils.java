package Efectura.utilities;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v110.emulation.Emulation;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;


/**
 * A utility class containing helper methods for common browser related operations.
 */

public class BrowserUtils {
    /**
     * Geçerli web sayfasının ekran görüntüsünü alır ve ekran görüntüsünün dosya yolunu döndürür.
     *
     * @param name Ekran görüntüsü dosyasına verilecek ad.
     * @return Ekran görüntüsünün dosya yolu.
     */
    public static String getScreenshot(String name) {
        // Adding date and time to the screenshot name to make it unique
//        name = new Date().toString().replace(" ", "_").replace(":", "-") + "_" + name;
        String path;
        // Determining the file path based on the operating system
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            path = System.getProperty("user.dir") + "/test-output/screenshots/" + name + ".png";
        } else {
            path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + name + ".png";
        }
        TakesScreenshot screenshot = (TakesScreenshot) Driver.getDriver();
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(path);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    /**
     * Belirtilen web öğesinin sayfada görüntülenip görüntülenmediğini doğrular.
     * Eğer öğe görüntülenmiyorsa, test verilen mesajla başarısız olacaktır.
     *
     * @param element Görünürlüğü kontrol edecek web elementi.
     * @param message Elemanın görünmemesi durumunda görüntülenecek mesaj.
     */
    public static void verifyElementDisplayed(WebElement element, String message) {
        try {
            assertTrue(message, element.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail(message);
        }
    }

    /**
     * Verilen web öğesine çift tıklama gerçekleştirir.
     *
     * @param element Çift tıklanacak web elementi.
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }
    /**
     * Kaynak elemandan hedef elemana sürükle bırak işlemi gerçekleştirir.
     *
     * @param source Sürüklenecek kaynak öğe.
     * @param target Üzerine bırakılacak hedef öğe.
     */
    public static void dragAndDrop(WebElement source, WebElement target) {
        //bir öğenin bir başka öğene sürüklenmesi işlemidir.
        Actions actions = new Actions(Driver.getDriver());
        actions.dragAndDrop(source, target).build().perform();
    }
    /**
     * Belirtilen web öğesi üzerinde fareyle üzerine gelme eylemi gerçekleştirir.
     *
     * @param element Üzerine gelinecek web elementi.
     */
    public static void hoverOver(WebElement element) {
        //üzerine gelmek veya odaklanmak için kullanılIR
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).build().perform();
    }
    /**
     * mouse  işaretçisini belirtilen web öğesine taşır.
     *
     * @param element Fare işaretçisinin taşınacağı web öğesi.
     */
    public static void moveToElement(WebElement element) {
        //üzerine gelmek veya odaklanmak için kullanılIR
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).build().perform();
    }
    /**
     * Geçerli iş parçacığını belirli bir saniye boyunca duraklatır.
     *
     * @param secs İş parçacığının duraklatılacağı saniye sayısı.
     */
    public static void wait(int secs) {
        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Geçerli iş parçacığını belirli bir saniye boyunca duraklatır.
     *
     * @param seconds İş parçacığının duraklatılacağı saniye sayısı.
     * Eğer iş parçacığı uyurken kesilirse @, InterruptedException'ı atar
     */
    public static void waitSeconds(int seconds) throws InterruptedException {
        long millis = seconds * 1000L;
        long startTime = System.currentTimeMillis();
        long remainingTime = millis;

        while (remainingTime > 0) {
            try {
                Thread.sleep(remainingTime);
            } catch (InterruptedException e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                remainingTime = millis - elapsedTime;
                // Re-interrupt the thread to propagate the interruption
                Thread.currentThread().interrupt();
                throw e;
            }
            remainingTime = millis - (System.currentTimeMillis() - startTime);
        }
    }
    /**
     * Başlığına göre bir hedef pencereye gider
     *
     * @param targetTitle hedef pencerenin başlığı
     */
    public static void navigateToWindow(String targetTitle) {
        //arklı tarayıcı pencereleri veya sekmeleri arasında geçiş yapmak için kullanılan bir işlemdir.
        String currentWindow = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(currentWindow);
    }
    /**
     * Belirtilen öğenin görünür olmasını bekler
     *
     * @param element - Beklenecek WebElement
     * @param timeToWaitInSec - Saniye cinsinden beklenecek süre
     * @return Görünür WebElement
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        //bir web sayfasındaki bir öğenin görünürlüğünü beklemek için kullanıLIR
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), ofSeconds(timeToWaitInSec) );
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * Bir öğenin görünür olmasını bekler
     *
     * @param locator - öğe bulucu
     * @param timeToWaitInSec - saniye cinsinden bekleme süresi
     * @return WebElement - görünür öğe
     */
    public static WebElement waitForVisibility(By locator, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    /**
     * Öğenin tıklanabilir olmasını bekler
     *
     * @param element - tıklanacak WebElement
     * @param timeToWaitInSec - saniye cinsinden zaman aşımı
     * @return WebElement
     */
    public static WebElement waitForClickability(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * Bir öğenin tıklanabilir olmasını bekler
     *
     * @param locator
     * @param timeToWaitInSec
     * @return WebElement
     */
    public static WebElement waitForClickable(By locator, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    /**
     * Selects an option from a dropdown by visible text.
     *
     * @param dropdownElement The dropdown WebElement.
     * @param optionToSelect  The option to select by visible text.
     */
    public static void selectDropdownOptionByVisibleText(WebElement dropdownElement, String optionToSelect) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(optionToSelect);
    }
    /**
     * Selects an option from a dropdown by index.
     *
     * @param dropdownElement The dropdown WebElement.
     * @param index           The index of the option to select.
     */
    public static void selectDropdownOptionByIndex(WebElement dropdownElement, int index) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }
    /**
     * Selects an option from a dropdown by value attribute.
     *
     * @param dropdownElement The dropdown WebElement.
     * @param value           The value attribute of the option to select.
     */
    public static void selectDropdownOptionByValue(WebElement dropdownElement, String value) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }
    /**
     * Adına veya kimliğine göre belirtilen çerçeveye geçer.
     *
     * @param frameNameOrId geçiş yapılacak çerçevenin adı veya kimliği
     */
    public static void switchToFrame(String frameNameOrId) {
        Driver.getDriver().switchTo().frame(frameNameOrId);
    }

    /**
     * Belirtilen metinle başlayan dinamik bir öğeye tıklar.
     *
     * @param searchText başlangıcında aranacak metni yazın
     */

    public static void clickDynamicElementStartsWith(String searchText) {
        String xpath = "//*[starts-with(text(),'" + searchText + "')]";
        WebElement element = Driver.getDriver().findElement(By.xpath(xpath));
        element.click();
    }
    /**
     * belirtilen nitelik değerini içeren dinamik bir öğeye tıklayın.
     *
     * @param attributeName aranacak özelliğinin adını belirtir
     * @param attributeValue aranacak özelliğin değerinin
     */
    public static void clickDynamicElementByAttribute(String attributeName, String attributeValue) {
        String xpath = "//*[@"+attributeName+"='"+attributeValue+"']";
        WebElement element = Driver.getDriver().findElement(By.xpath(xpath));
        element.click();
    }
    /**
     * Bir uyarıyı kabul eder ve metnini döndürür.
     *
     * @return uyarı metnini
     */
    public static String handleAlertAcceptByReturningMessage() {
        Alert alert = Driver.getDriver().switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }
    /**
     * Bir uyarıyı kabul eder
     *
     * @return uyarı metnini
     */
    public static void handleAlertAccept() {
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.accept();
    }
    /**
     * Bir uyarıyı reddeder ve metnini döndürür.
     *
     * @return uyarı metnini
     */
    public static String handleAlertDismissByReturningMessage() {
        Alert alert = Driver.getDriver().switchTo().alert();
        String text = alert.getText();
        alert.dismiss();
        return text;
    }/**
     * Bir uyarıyı reddeder ve metnini döndürür.
     *
     * @return uyarı metnini
     */
    public  static void handleAlertDismiss() {
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.dismiss();
    }
    /**
     * Belirtilen başlığa sahip tarayıcı sekmesine geçiş yapar.
     *
     * @param title Geçiş yapılacak tarayıcı sekmesinin başlığı.
     */
    public static void switchToTab(String title) {
        WebDriver driver = Driver.getDriver();
        // Tüm açık pencere tanıtıcılarının listesini alın
        for (String windowHandle : driver.getWindowHandles()) {
            // Her pencere tanıtıcısına geçin ve başlığını kontrol edin
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(title)) {
                // Başlık eşleşirse döngüden çık
                break;
            }
        }
    }
    public static void selectOption(WebElement selectElement, String value) {
        Select select = new Select(selectElement);
        select.selectByValue(value);
    }

    // Seçenek seçilmiş mi kontrol etme metodunu tanımla
    public static boolean isOptionSelected(WebElement selectElement, String value) {
        Select select = new Select(selectElement);
        return select.getFirstSelectedOption().getAttribute("value").equals(value);
    }


    /**
     * Checks if the given WebElement is displayed on the page.
     *
     * @param element the WebElement to check
     * @return true if the element is displayed, false if the element is not found or not displayed
     */
    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementDisplayed(By locator) {
        try {
            return Driver.getDriver().findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }



    public static boolean isButtonActive(WebElement button) {
        String classAttribute = button.getAttribute("class");
        return !classAttribute.contains("disabled");
    }

    public static void scrollToRightEnd(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Sayfanın toplam genişliği kadar sağa kaydır
        js.executeScript("window.scrollTo(document.body.scrollWidth, 0)");
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Elementi hem yatayda hem dikeyde görünür hale getirir
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // JavaScript kullanarak zoom seviyesini %80 yap
    public static void adjustScreenSize(int size, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + size + "%'");
    }

    public static String getValueInInputBox(WebElement inputBox) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        return (String) js.executeScript("return arguments[0].value;", inputBox);
    }

    private static final String TELEGRAM_BOT_TOKEN = "6538211561:AAEVRYoo03lBKnqhTUUU3lne9nfvpRGHa08";
    private static final String TELEGRAM_CHAT_ID = "-4194828120";
    public static void sendFileToTelegram(String filePath, String chatId) {
        String boundary = "===" + System.currentTimeMillis() + "===";
        String LINE_FEED = "\r\n";

        try {
            String urlString = "https://api.telegram.org/bot" + TELEGRAM_BOT_TOKEN + "/sendDocument";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setUseCaches(false);
            connection.setDoOutput(true); // POST yapacağımız için
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setRequestMethod("POST");

            try (OutputStream outputStream = connection.getOutputStream();
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)) {

                // chat_id ekle
                writer.append("--").append(boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"chat_id\"").append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=UTF-8").append(LINE_FEED);
                writer.append(LINE_FEED).append(chatId).append(LINE_FEED);
                writer.flush();

                // Dosya ekle
                File uploadFile = new File(filePath);
                String fileName = uploadFile.getName();
                writer.append("--").append(boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"document\"; filename=\"").append(fileName).append("\"").append(LINE_FEED);
                writer.append("Content-Type: application/octet-stream").append(LINE_FEED);
                writer.append(LINE_FEED);
                writer.flush();

                // Dosyayı byte olarak gönder
                try (FileInputStream inputStream = new FileInputStream(uploadFile)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.flush();
                }

                writer.append(LINE_FEED).flush();
                writer.append("--").append(boundary).append("--").append(LINE_FEED);
                writer.close();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("File sent to Telegram successfully: " + filePath);
            } else {
                System.out.println("Failed to send file to Telegram. Status Code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fetchRandomImageBase64() {
        String imageUrl = "https://picsum.photos/300"; // Rastgele 200x200 resim

        try (InputStream in = new URL(imageUrl).openStream();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertImageUrlToBase64(String imageUrl) {
        try (InputStream inputStream = new URL(imageUrl).openStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer);
            }

            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getExcelPath(String fileName) {
        String projectDir = System.getProperty("user.dir");
        String relativePath = "src/test/resources/testData/" + fileName + ".xlsx";
        return Paths.get(projectDir, relativePath).toString();
    }
    public static String generateGeorgianMobileNumber() {
        Random random = new Random();

        // Gürcistan'da geçerli olan mobil numara prefix'leri
        int[] validPrefixes = {591, 592, 593, 595, 596, 597, 598, 599};

        // Rastgele geçerli bir prefix seç
        int prefix = validPrefixes[random.nextInt(validPrefixes.length)];

        // Kalan 6 hane (000000–999999 arası)
        int number = 100000 + random.nextInt(900000); // 6 haneli sayı

        return prefix + String.format("%06d", number); // Örnek: 595123456
    }

    public static String generateTurkishMobileNumber() {
        Random random = new Random();

        // Türkiye'de kullanılan mobil numara prefix'leri (5xx)
        int[] validPrefixes = {
                501, 505, 506, 507, 530, 531, 532, 533, 534, 535, 536,
                537, 538, 539, 540, 541, 542, 543, 544, 545, 546,
                547, 548, 549, 550, 551, 552, 553, 554, 555, 559
        };

        // Rastgele geçerli bir prefix seç
        int prefix = validPrefixes[random.nextInt(validPrefixes.length)];

        // Kalan 7 hane (0000000–9999999 arası)
        int number = 1000000 + random.nextInt(9000000);

        return prefix + String.format("%07d", number); // Örnek: 5321234567
    }

    public static String generateRandomEmail() {
        Random random = new Random();

        // Kullanıcı adı için kullanılabilecek karakterler
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";

        // Kullanıcı adını rastgele uzunlukta oluştur (ör. 6–12 karakter arası)
        int length = 6 + random.nextInt(7);
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < length; i++) {
            username.append(chars.charAt(random.nextInt(chars.length())));
        }

        // Rastgele domain seç
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};
        String domain = domains[random.nextInt(domains.length)];

        return username.toString() + "@" + domain; // Örnek: "kd92lsx@gmail.com"
    }

    public static boolean areListsEqualIgnoreOrder(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        List<String> copy1 = new ArrayList<>(list1);
        List<String> copy2 = new ArrayList<>(list2);

        Collections.sort(copy1);
        Collections.sort(copy2);

        return copy1.equals(copy2);
    }

    public static List<String> convertWebElementListToStringList(List<WebElement> webElementList) {
        return webElementList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


    // readyState COMPLETE
    public static void waitForPageLoaded(Duration timeout) {
        new WebDriverWait(Driver.getDriver(), timeout).until(d ->
                ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    // Genel overlay/spinner bekleme – kendi uygulamana göre lokatörü güncelle
    // Örn: Tailwind backdrop, loading spinner, modal açıkken body:overflow-hidden, vs.
    public static void waitOverlaysGone(Duration timeout) {
        By[] blockers = new By[] {
                By.cssSelector(".loading, .spinner, .MuiBackdrop-root, .ant-modal-mask, .Toastify__toast--loading"),
                By.cssSelector("[data-state='open']"), // Radix/HeadlessUI açık modal
                By.cssSelector("div[role='dialog']"),
                By.cssSelector(".fixed.inset-0"), // Tailwind full-screen overlay
        };
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        for (By by : blockers) {
            try { wait.until(ExpectedConditions.invisibilityOfElementLocated(by)); } catch (Exception ignore) {}
        }
        // Body scroll kilidi kalksın
        new WebDriverWait(Driver.getDriver(), timeout).until(d ->
                !((JavascriptExecutor)d).executeScript(
                        "return document.body.style.overflow || getComputedStyle(document.body).overflow"
                ).toString().contains("hidden"));
    }

    // Eleman görünür & etkin & tıklanabilir
    public static WebElement waitClickable(By by, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    // Ekrana getir
    public static void scrollIntoView(WebElement el) {
        ((JavascriptExecutor)Driver.getDriver()).executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
    }

    // Elemanın üstünde başka bir şey var mı (point hit-test)
    public static boolean isCovered(WebElement el) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        Map<String, Long> rect = (Map<String, Long>) js.executeScript(
                "const r=arguments[0].getBoundingClientRect();" +
                        "return {x: Math.floor(r.left + r.width/2), y: Math.floor(r.top + r.height/2)};", el);
        Long x = rect.get("x"), y = rect.get("y");
        WebElement top = (WebElement) js.executeScript(
                "return document.elementFromPoint(arguments[0], arguments[1]);", x, y);
        return top != el && !el.equals(top) && !el.isDisplayed();
    }


    public static void safeClick(WebElement element) {
        WebDriver driver = Driver.getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        RuntimeException last = null;

        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                waitForPageLoaded(Duration.ofSeconds(20));
                waitOverlaysGone(Duration.ofSeconds(20));
                scrollIntoView(element);

                if (isCovered(element)) {
                    waitOverlaysGone(Duration.ofSeconds(10));
                }

                try {
                    element.click(); // Normal click
                    return;
                } catch (ElementClickInterceptedException e1) {
                    // Actions click
                    try {
                        new Actions(driver).moveToElement(element).pause(Duration.ofMillis(100)).click().perform();
                        return;
                    } catch (Exception e2) {
                        // JS click
                        js.executeScript("arguments[0].click();", element);
                        return;
                    }
                }
            } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException e) {
                last = new RuntimeException(e);
                try { Thread.sleep(400L * attempt); } catch (InterruptedException ignored) {}
            }
        }
        throw last != null ? last : new RuntimeException("safeClick failed for given element.");
    }

    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        // Tarih formatı ile dosya ismini eşsizleştir
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";

        // Ekran görüntüsünü geçici dosyaya al
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Hedef klasör (örnek: proje altındaki /screenshots klasörü)
        String destDir = System.getProperty("user.dir") + "/screenshots/";
        File directory = new File(destDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Hedef dosya yolu
        Path destPath = Path.of(destDir + fileName);

        try {
            // Dosyayı kopyala
            Files.copy(srcFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved at: " + destPath);
            return destPath.toString(); // Yol döndürülüyor
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setZoom(WebDriver driver, int percent) {
        // 50 => 0.5; 100 => 1.0
        double scale = percent / 100.0;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Tüm sayfaya uygula
        js.executeScript("document.documentElement.style.zoom = arguments[0];", String.valueOf(scale));
    }

    public static void setPageScale(WebDriver driver, double scale) {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();

        // 1. Gerçek sayfa ölçek faktörü (0.5 = %50, 1.0 = %100)
        devTools.send(Emulation.setPageScaleFactor(scale));

        // 2. (Opsiyonel ama faydalı) viewport’u bildir — layout tekrar hesaplansın
        int baseW = 1920, baseH = 1080;
        devTools.send(Emulation.setDeviceMetricsOverride(
                (int)(baseW), (int)(baseH), 1.0, false,
                java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(),
                java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(),
                java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()
        ));

        ((JavascriptExecutor) driver).executeScript("window.dispatchEvent(new Event('resize'));");
    }

    public static void resetTo100(WebDriver driver) {
        driver.findElement(By.tagName("html"))
                .sendKeys(Keys.chord(Keys.CONTROL, "0"));
    }

    public static boolean isNewExcelDownloaded(String downloadDirPath, long maxAgeSeconds) {
        File dir = new File(downloadDirPath);
        File[] xlsxFiles = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".xlsx"));

        if (xlsxFiles == null || xlsxFiles.length == 0) {
            return false;
        }

        File latestFile = Arrays.stream(xlsxFiles)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);

        if (latestFile == null) return false;

        long currentTime = System.currentTimeMillis();
        long lastModified = latestFile.lastModified();

        long ageInSeconds = (currentTime - lastModified) / 1000;
        return ageInSeconds <= maxAgeSeconds;
    }

    public static String getLatestExcelFileName(String downloadDirPath) {
        File dir = new File(downloadDirPath);
        File[] xlsxFiles = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".xlsx"));

        if (xlsxFiles == null || xlsxFiles.length == 0) {
            return null;
        }

        File latestFile = Arrays.stream(xlsxFiles)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);

        return latestFile != null ? latestFile.getName() : null;
    }




}





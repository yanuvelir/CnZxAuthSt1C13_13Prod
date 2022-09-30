import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class AuthorizationCase_8 {

    // Инициализируем Webdriver при помощи метода public Static, для автоматического использования в других классах
    public static WebDriver driver;

    @Parameters({"StartUrl", "UrlAssert"})
    public void case_8(String StartUrl, String UrlAssert) throws InterruptedException {
        System.out.println("Case_8");

        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        System.setProperty("webdriver.chrome.whitelistedIps", "");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        //WebDriver driver = new FirefoxDriver(options);

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);

        // Переход на начальную страницу
        driver.get(StartUrl);
        //driver.get("https://coinzix.com/login");

        Thread.sleep(1500);
        // Развертывание страницы на весь экран
        driver.manage().window().maximize();


        // //Находим и нажимаем клавишу Log in
        WebElement PrivacyPolicy = driver.findElement(By.xpath
                ("//a[contains(text(), 'Privacy Policy')]"));
        PrivacyPolicy.click();
        Thread.sleep(3000);
        JavascriptExecutor executor = ((JavascriptExecutor) driver);
        executor.executeScript("window.location.reload(true)");

        Thread.sleep(5000);
        String GetCurrUrl = driver.getTitle();
        System.out.println(GetCurrUrl);

//        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
//        driver.switchTo().window(newTab.get(0));

        Thread.sleep(4000);

        WebElement TermsService = driver.findElement(By.xpath
                ("//a[contains(text(), 'Terms of Service')]"));
        TermsService.click();
        Thread.sleep(3000);
//        Actions actions = new Actions(driver);
//        actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).build().perform();

        Thread.sleep(4000);
        //Копируем URL текущей страницы
        String GetCurrUrl1 = driver.getTitle();
        System.out.println(GetCurrUrl1);



//        try {
//            //Добавлен сравнение адреса. При заданых параметрах логина и пароля, адрес страницы меняется
//            //Assert.assertEquals(GetCurrUrl, "https://hermesus-stage1.intary.net/spot/trading/BTCUSDT");
//            //Assert.assertEquals(GetCurrUrl, "https://hermesus.com/spot/trading/BTCUSDT");
//            Assert.assertEquals(GetCurrUrl, "https://coinzix-stage1.intary.net/spot/trading/BTCUSDT");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//
//        }
        driver.quit();
    }

}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//Тест на наличие капчи

@Test
public class AuthorizationCase_9 {

    // Инициализируем Webdriver при помощи метода public Static, для автоматического использования в других классах
    public static WebDriver driver;

    @Parameters({"StartUrl", "UrlAssert"})
    public void case_9(String StartUrl, String UrlAssert) throws InterruptedException {
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

        //Находим и вводи Email
        WebElement email = driver.findElement(By.id("email"));

        email.sendKeys("qawpt@ukr.net");

        //Находим и вводим Password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("@Coinzix17");

        // //Находим и нажимаем клавишу Log in
        WebElement logBtn = driver.findElement(By.xpath
                ("//button[@class='submit__button']"));
        logBtn.click();

        Thread.sleep(6000);
try {


        WebElement CaptchaExist = driver.findElement(By.xpath("//iframe[@title='recaptcha challenge expires in two minutes']"));
        System.out.println(CaptchaExist.getClass());
} catch (Exception e) {
    e.printStackTrace();
}
        //Копируем URL текущей страницы
        String GetCurrUrl = driver.getCurrentUrl();
        System.out.println(GetCurrUrl);

        try {
            //Добавлен сравнение адреса. При заданых параметрах логина и пароля, адрес страницы меняется
           // Assert.assertEquals(GetCurrUrl, "https://coinzix-stage1.intary.net/spot/trading/BTCUSDT");
            Assert.assertEquals(GetCurrUrl, UrlAssert);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            driver.quit();
        }
    }
}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class AuthorizationCase_6 {

    // Инициализируем Webdriver при помощи метода public Static, для автоматического использования в других классах
    public static WebDriver driver;

    @Parameters({"StartUrl", "UrlAssert"})
    public void case_6(String StartUrl, String UrlAssert) throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Переход на начальную страницу
        //driver.get("https://coinzix-stage1.intary.net/login");
        driver.get(StartUrl);

        Thread.sleep(1500);
        // Развертывание страницы на весь экран
        driver.manage().window().maximize();

        //Находим и вводи Email Email=Null
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("");

        //Находим и вводим Password. Pass=Valid
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("@Coinzix17");

        // //Находим и нажимаем клавишу Log in
        WebElement logBtn = driver.findElement(By.xpath
                ("//button[@class='submit__button']"));
        logBtn.click();
        Thread.sleep(2500);

        //Копируем URL текущей страницы
        String GetCurrUrl = driver.getCurrentUrl();
        System.out.println(GetCurrUrl);

        try {
            //Добавлен сравнение адреса. При заданых параметрах логина и пароля, адрес страницы менятся не должен
            Assert.assertEquals(GetCurrUrl, UrlAssert);
           // Assert.assertEquals(GetCurrUrl, "https://coinzix-stage1.intary.net/login");
        } catch (Exception e) {
        e.printStackTrace();
        } finally {

            driver.quit();
}
    }
}
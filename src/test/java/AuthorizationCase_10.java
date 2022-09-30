import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class AuthorizationCase_10 {

    // Инициализируем Webdriver при помощи метода public Static, для автоматического использования в других классах
    public static WebDriver driver;

    @Parameters({"StartUrl", "UrlAssert"})
    public void case_10(String StartUrl, String UrlAssert) throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        System.out.println("Case_10");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Переход на начальную страницу
        //driver.get("https://coinzix-stage1.intary.net/login");
        driver.get(StartUrl);

        Thread.sleep(1500);
        // Развертывание страницы на весь экран
        driver.manage().window().maximize();

        //Находим и вводи Email
        WebElement email = driver.findElement(By.id("email"));

        // Находим и вводим Email. Email=incorrect
        email.sendKeys("эээ!qawpt@ukr.net");

        //Находим и вводим Password. Pass=incorrect
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("!@oinzix17");

        // //Находим и нажимаем клавишу Log in
        WebElement logBtn = driver.findElement(By.xpath
                ("//button[@class='submit__button']"));
        logBtn.click();
        Thread.sleep(2500);

        //Копируем URL текущей страницы
        String GetCurrUrl = driver.getCurrentUrl();
        System.out.println(GetCurrUrl);

        // Верефицируем сообщение о некорректно введенном Email
        WebElement EmailErrMsg = driver.findElement(By.xpath("//small[contains(text(),'Enter a valid email')]"));
        System.out.println(EmailErrMsg.getText());

        // Верефицируем сообщение о некорректно введенном Password
        WebElement PsswErrMsg = driver.findElement(By.xpath
                ("//small[contains(text(),'The \"Password\" field must contain at least 6 chars: uppercase (A-Z) lowercase (a-z), digits (0-9)')]"));
        System.out.println(PsswErrMsg.getText());


        try {
            //Добавлен сравнение адреса. При заданых параметрах логина и пароля, адрес страницы менятся не должен
            Assert.assertEquals(GetCurrUrl, UrlAssert);
            //Assert.assertEquals(GetCurrUrl, "https://coinzix-stage1.intary.net/login");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            driver.quit();
        }
    }

}

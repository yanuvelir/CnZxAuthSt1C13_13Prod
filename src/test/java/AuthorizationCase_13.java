import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class AuthorizationCase_13 {

    // Инициализируем Webdriver при помощи метода public Static, для автоматического использования в других классах
    public static WebDriver driver;

    @Parameters({"StartUrl", "UrlAssert"})
    public void case_11(String StartUrl, String UrlAssert) throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Переход на начальную страницу
        //driver.get("https://coinzix-stage1.intary.net/login");
        driver.get(StartUrl);
        Thread.sleep(1500);
        // Развертывание страницы на весь экран
        driver.manage().window().maximize();


        // //Находим и нажимаем ссылку "Forgot Password"
        WebElement ForgotPsswLnk = driver.findElement(By.xpath("//a[@href='/forgot']"));
        ForgotPsswLnk.click();

        Thread.sleep(2500);
        WebElement ForgotPsswBtn = driver.findElement(By.xpath("//div/button[@type='submit']"));

        Thread.sleep(1000);
        //Эмитируем передвижения элемента в центр кнопки
        Actions action = new Actions(driver);
        action.moveToElement(ForgotPsswBtn).perform();

        Thread.sleep(2500);
        //Считываем цвет кнопки - Background color
        String BackGroundClr = ForgotPsswBtn.getCssValue("background-color");
        String HexBackGroundClr = Color.fromString(BackGroundClr).asHex();
        System.out.println(HexBackGroundClr);
        //#000000

        Thread.sleep(2500);
        try {
            //Добавлен сравнение Background color кнопки при наведении на кнопку курсора
            Assert.assertEquals(HexBackGroundClr, "#000000");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Копируем URL текущей страницы
        String GetCurrUrl = driver.getCurrentUrl();
        System.out.println(GetCurrUrl);

        try {
            //Добавлен сравнение адреса. При заданых параметрах логина и пароля, адрес страницы менятся не должен
            Assert.assertEquals(GetCurrUrl, UrlAssert);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            driver.quit();
        }
    }

}

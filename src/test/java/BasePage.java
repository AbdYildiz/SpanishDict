import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    public Actions act;
    public WebDriverWait wait;
    public SoftAssert soft;

    public List<HashMap<String,String>> getJsonData() throws IOException {
        String contents = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "/userData.json"), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(contents, new TypeReference<List<HashMap<String, String>>>(){});
        return data;
    }

    public void waitVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitInvisibility(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    @BeforeSuite public void setDriver(){
        driver = new ChromeDriver();
        act = new Actions(driver);
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        soft = new SoftAssert();

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
        driver.get("https://www.spanishdict.com/lists/1446414/verbs-all");
        driver.manage().window().maximize();
    }

    @AfterSuite public void close() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

    @FindBy(xpath = "//div[@id='main-container-video']/div[3]/div//a/div[1]") List<WebElement> palabras;

    @Test public void getPalabras() throws InterruptedException {
//        driver.findElement(By.xpath("//button[normalize-space()='Sign in']")).click();
//        Thread.sleep(20000);
//        driver.navigate().to("https://www.spanishdict.com/lists/1446414/verbs-all");

        int i = 1;
        String palabra = null;
        do {
            WebElement element = driver.findElement(By.xpath("(//div[@id='main-container-video']/div[3]/div//a/div[1])[" + i + "]"));
            act.moveToElement(element).build().perform();
            palabra = element.getText();
            System.out.print(palabra + " ");
            i++;
        } while (!palabra.isEmpty());
        System.out.println(i);
    }


}
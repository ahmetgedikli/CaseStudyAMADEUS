import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmadeusTest {
    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Desktop\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void flightAppTest1(){

        driver.get("https://flights-app.pages.dev/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//button[@id=\"headlessui-combobox-button-:R1a9lla:\"]")).click();
        driver.findElement(By.xpath("//span[text()=\"Istanbul\"]")).click();
        driver.findElement(By.xpath("//button[@id=\"headlessui-combobox-button-:R1ahlla:\"]")).click();

        //if selection "Istanbul" is also available in "To:" selection list, this should fail
        Assert.assertFalse(driver.findElement(By.xpath("//span[text()=\"Istanbul\"]")).isDisplayed());

    }

    @Test
    public void flightAppTest2(){


        driver.findElement(By.xpath("//button[@id=\"headlessui-combobox-button-:R1a9lla:\"]")).click();
        driver.findElement(By.xpath("//span[text()=\"Istanbul\"]")).click();
        driver.findElement(By.xpath("//button[@id=\"headlessui-combobox-button-:R1ahlla:\"]")).click();
        driver.findElement(By.xpath("//span[text()=\"Los Angeles\"]")).click();

        Integer countOfFoundFlights =(driver.findElements(By.xpath("//li[@class=\"overflow-hidden rounded-xl border border-gray-200\"]"))).size();

        String text =  driver.findElement(By.xpath("//p[@class=\"mb-10\"]")).getText();

        Integer countInText = Integer.valueOf(text.substring(text.indexOf(" ")+1,text.indexOf(" ",text.indexOf(" ")+1)));

        //if the number of flights found and the number in text is equal, this should pass
        Assert.assertEquals(countOfFoundFlights,countInText);


    }
}

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UITests {

    //See if webpage is accessible
    @Test
    public void t0() {
        System.setProperty("webdriver.gecko.driver","lib\\geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        wd.get("http://wellbeingeating.com");
        assertEquals("WellBeing", wd.getTitle());
        wd.quit();
    }

    //See if webpage is accessible
    @Test
    public void t1() {
        System.setProperty("webdriver.gecko.driver","~\\lib\\geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        wd.get("http://wellbeingeating.com");

        assertEquals("WellBeing", wd.getTitle());
        wd.quit();
    }

}

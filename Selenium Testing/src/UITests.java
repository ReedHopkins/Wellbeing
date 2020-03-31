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
    public void titleTest() {
    	String os = System.getProperty("os.name");
    	if(os.contains("Mac")) { 
    		System.setProperty("webdriver.gecko.driver","lib//geckodriver");
    	} else 
            System.setProperty("webdriver.gecko.driver","lib\\geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        wd.get("http://wellbeingeating.com");
        assertEquals("WellBeing", wd.getTitle());
        wd.quit();
    }

    //See if logo button works
    @Test
    public void logoTest() {
    	String os = System.getProperty("os.name");
    	if(os.contains("Mac")) { 
    		System.setProperty("webdriver.gecko.driver","lib//geckodriver");
    	} else 
            System.setProperty("webdriver.gecko.driver","lib\\geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        wd.get("http://www.wellbeingeating.com/pages/about.html");
		WebElement we = wd.findElement(By.className("navbar-brand"));
		we.click(); //click the button
        assertEquals("http://www.wellbeingeating.com/index.html", wd.getCurrentUrl());
        wd.quit();
    }

    //See if main page meal plan button  works
    @Test
    public void mealPlanButtonTest() {
    	String os = System.getProperty("os.name");
    	if(os.contains("Mac")) { 
    		System.setProperty("webdriver.gecko.driver","lib//geckodriver");
    	} else 
            System.setProperty("webdriver.gecko.driver","lib\\geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        wd.get("http://www.wellbeingeating.com/index.html");
		WebElement we = wd.findElement(By.id("mealPlanButton"));
		we.click(); //click the button
        assertEquals("http://www.wellbeingeating.com/pages/mealplan.html", wd.getCurrentUrl());
        wd.quit();
    }
    
    //See if healthy alternative button works
    @Test
    public void healthyAltButtonTest() {
    	String os = System.getProperty("os.name");
    	if(os.contains("Mac")) { 
    		System.setProperty("webdriver.gecko.driver","lib//geckodriver");
    	} else 
            System.setProperty("webdriver.gecko.driver","lib\\geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        wd.get("http://www.wellbeingeating.com/index.html");
		WebElement we = wd.findElement(By.id("healthyAltButton"));
		we.click(); //click the button
        assertEquals("http://www.wellbeingeating.com/pages/healthysubs.html", wd.getCurrentUrl());
        wd.quit();
    }
    
    //See if healthy alternative button works
    @Test
    public void paginationButtonsTest() {
    	String os = System.getProperty("os.name");
    	if(os.contains("Mac")) { 
    		System.setProperty("webdriver.gecko.driver","lib//geckodriver");
    	} else 
            System.setProperty("webdriver.gecko.driver","lib\\geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        wd.get("http://www.wellbeingeating.com/pages/ingredients.jsp");
		WebElement we = wd.findElement(By.className("page-link"));
		we.click(); //click the button
        assertNotEquals("http://www.wellbeingeating.com/pages/ingredients.jsp", wd.getCurrentUrl());
        wd.quit();
    }
}

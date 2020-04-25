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
        wd.get("http://wellbeingeating.com/about.jsp");
		WebElement we = wd.findElement(By.className("navbar-brand"));
		we.click(); //click the button
        assertEquals("http://wellbeingeating.com/index.jsp", wd.getCurrentUrl());
        wd.quit();
    }

    
    //See if healthy alternative button works
    @Test
    public void refreshButtonTest() {
    	String os = System.getProperty("os.name");
    	if(os.contains("Mac")) { 
    		System.setProperty("webdriver.gecko.driver","lib//geckodriver");
    	} else 
            System.setProperty("webdriver.gecko.driver","lib\\geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        wd.get("http://wellbeingeating.com/ModelServlet?model=Ingredient&search_term=highprotein");
		WebElement we = wd.findElement(By.className("input-group-text"));
		we.click(); //click the button
        assertEquals("http://wellbeingeating.com/ModelServlet?model=Ingredient", wd.getCurrentUrl());
        wd.quit();
    }
    
    @Test
    public void paginationButtonsTest() {
    	String os = System.getProperty("os.name");
    	if(os.contains("Mac")) { 
    		System.setProperty("webdriver.gecko.driver","lib//geckodriver");
    	} else 
            System.setProperty("webdriver.gecko.driver","lib\\geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        wd.get("http://wellbeingeating.com/ModelServlet?model=Ingredient");
		WebElement we = wd.findElement(By.className("page-link"));
		we.click(); //click the button
        assertNotEquals("http://wellbeingeating.com/ModelServlet?model=Ingredient", wd.getCurrentUrl());
        wd.quit();
    }
    
	@Test
	public void searchTest() {
		String os = System.getProperty("os.name");
    	if(os.contains("Mac")) { 
    		System.setProperty("webdriver.gecko.driver","lib//geckodriver");
    	} else 
            System.setProperty("webdriver.gecko.driver","lib\\geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        wd.get("http://wellbeingeating.com/ModelServlet?model=Ingredient");
		WebElement we = wd.findElement(By.id("search_term"));
		we.sendKeys("almonds");
		we = wd.findElement(By.id("submit_btn"));
		we.click(); //click the button
		we = wd.findElement(By.className("search_param"));
		wd.quit(); // close the browser window
	}

}

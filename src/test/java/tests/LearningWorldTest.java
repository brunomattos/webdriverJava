package tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LearningWorldTest {

    private WebDriver navegador;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        navegador.get("http://195.2.221.205:81/admin/");
        navegador.findElement(By.id("username")).sendKeys("systemAdmin");
        navegador.findElement(By.id("password")).sendKeys("siemenssystemadmin");
        navegador.findElement(By.xpath("//button[text()=\"Login\"]")).click();
        String urlDashboard = navegador.getCurrentUrl();
        assertEquals("http://195.2.221.205:81/admin/", urlDashboard);
    }
    
    @Test
    public void testEntries(){
        navegador.findElement(By.xpath("//span[text()='Entries']/..")).click();
    }

    @After
    public void tearDown(){
        //browser.quit();
    }
}

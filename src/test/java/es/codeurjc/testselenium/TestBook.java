package es.codeurjc.testselenium;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import es.urjc.code.daw.library.Application;
import io.github.bonigarcia.wdm.WebDriverManager;


@SpringBootTest(
		classes = Application.class, 
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestBook {
	
	@LocalServerPort
    int port;

	WebDriver driver;
	
	@BeforeAll
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
	}
	
	@AfterEach
	public void teardown() {
		if(driver != null) {
			driver.quit();
		}
	}
	
	@Test
	public void createBook() throws InterruptedException {
		
		driver.get("http://localhost:"+this.port+"/");
		
		driver.findElement(By.tagName("button")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.name("title")).sendKeys("Nunca acabar");
		driver.findElement(By.name("description")).sendKeys("Relata la historia de un hombre");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		driver.findElement(By.id("Back")).click();
		
		assertNotNull(driver.findElement(By.partialLinkText("acabar")));
	}
	
	@Test
	public void deleteBook() throws InterruptedException {
		
		driver.get("http://localhost:"+this.port+"/");
		
		driver.findElement(By.tagName("button")).click();
		
		driver.findElement(By.name("title")).sendKeys("La vida de un deportista");
		driver.findElement(By.name("description")).sendKeys("Relata la vida de un deportista");

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		driver.findElement(By.id("Back")).click();
		
		Thread.sleep(1000);	
		
		driver.findElement(By.partialLinkText("deportista")).click();
		
		driver.findElement(By.id("Remove")).click();
		
		Thread.sleep(5000);	
		assertThrows(NoSuchElementException.class, () -> {
			driver.findElement(By.partialLinkText("deportista"));
		});	
	}

}


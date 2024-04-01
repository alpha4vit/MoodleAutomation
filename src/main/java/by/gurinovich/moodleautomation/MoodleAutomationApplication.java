package by.gurinovich.moodleautomation;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class MoodleAutomationApplication {

	private final WebDriver driver;

	public static void main(String[] args) {
		SpringApplication.run(MoodleAutomationApplication.class, args);
	}

	@PostConstruct
	public void start() throws InterruptedException {
		driver.get("https://lms.bsuir.by/login/index.php");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement inputField = driver.findElement(By.id("username"));
		inputField.sendKeys("576197");
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys("Roman3041");
		WebElement submitButton = driver.findElement(By.id("loginbtn"));
		submitButton.click();
	}


	@Scheduled(fixedRate = 100, timeUnit = TimeUnit.SECONDS)
	public void visitTabs() {
		Integer pageId = 163978;
		Integer lastPageId = 164129;
		while (pageId.compareTo(lastPageId) != 0){
			try {
				driver.get("https://lms.bsuir.by/course/view.php?id=4833");
				var linkEl = driver.findElement(By.cssSelector(String.format("li[data-id='%d']", pageId)));
				var aEl = linkEl.findElement(By.tagName("a"));
				log.info(aEl.getAttribute("href"));
				if (!aEl.getAttribute("href").contains("resource"))
					driver.get(aEl.getAttribute("href"));
				Thread.sleep(1000);
			}
			catch (Exception e){
				log.error(e.getMessage());
			}
			finally {
				pageId++;
			}

		}
		scrollPage();
	}

	@SneakyThrows
	public void scrollPage(){
		var js = (JavascriptExecutor) driver;
		driver.get("https://lms.bsuir.by/course/view.php?id=4833");
		for (int i =0; i< 100; i ++){
			Thread.sleep(10);
			js.executeScript("window.scrollBy(0, 100)");
		}
		for (int i = 0; i < 100; ++i){
			Thread.sleep(10);
			js.executeScript("window.scrollBy(0, -100)");
		}
	}

}





package by.gurinovich.moodleautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class BeanConfig {
    @Bean
    public WebDriver driver(){
        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("download.default_directory=/dev/null");
        chromeOptions.addArguments("--headless");
        return new ChromeDriver(chromeOptions);
    }

}

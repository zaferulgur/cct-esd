package com.zafer.base.browser.types;

import com.zafer.base.TestBase;
import com.zafer.base.browser.BaseBrowser;
import com.zafer.base.browser.BrowserType;
import com.zafer.util.PropertiesReaderUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.Properties;

@Log4j2
public class BrowserChrome implements BaseBrowser {
    @SneakyThrows
    @Override
    public WebDriver run() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        Properties browserConfigProperties = new PropertiesReaderUtil(BrowserType.CHROME.toString().toLowerCase()).read();
        if (browserConfigProperties.isEmpty()) {
            log.info(BrowserType.CHROME.toString().toLowerCase() + " properties does not contains capabilities..");
        } else {
            for (String key : browserConfigProperties.stringPropertyNames()) {
                String value = browserConfigProperties.getProperty(key);
                if (value == null || value.isEmpty()) {
                    chromeOptions.addArguments(key);
                } else {
                    chromeOptions.setCapability(key, value);
                }
            }
        }
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments("--remote-allow-origins=*");
        if (TestBase.testInitializationModel.getRemote() == null || TestBase.testInitializationModel.getRemote().equalsIgnoreCase("false")) {
            log.info("returning LOCALE chrome driver");
            return new ChromeDriver(chromeOptions);
        } else {
            log.info("returning REMOTE chrome driver");
            return new RemoteWebDriver(new URL(TestBase.testInitializationModel.getGridUrl()), chromeOptions);
        }
    }
}

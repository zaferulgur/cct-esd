package com.zafer.base;

import com.zafer.base.browser.BrowserContext;
import com.zafer.base.browser.BrowserType;
import com.zafer.base.driver.DriverContext;
import com.zafer.base.page.BasePageMethods;
import com.zafer.base.page.PageContext;
import com.zafer.util.PropertiesReaderUtil;
import com.zafer.util.Retry;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Locale;
import java.util.Properties;
import static com.zafer.base.driver.DriverContext.getDriver;

@Log4j2
public class TestBase {
    public static TestInitializationModel testInitializationModel;

    @BeforeSuite(alwaysRun = true)
    @Parameters(value = {"browser"})
    public void beforeSuite(ITestContext context, @Optional("chrome") String browser) {
        try {
            log.info("I'm on before suite, setting configurations");
            log.info("initializing configuration from frameworkConfig file");
            Properties configProperties = new PropertiesReaderUtil("frameworkConfig").read();
            testInitializationModel = new TestInitializationModel();
            testInitializationModel.setBrowserType(BrowserType.valueOf(browser.toUpperCase(Locale.ROOT)));
            testInitializationModel.setGridUrl(StringUtils.EMPTY);
            testInitializationModel.setBaseUrl(configProperties.getProperty("baseUrl"));
            testInitializationModel.setRemote("false");
            testInitializationModel.setRetryCount(Integer.parseInt(configProperties.getProperty("retry")));
            log.info("configuration parameter read completed, test initialize model : {}",new Gson().toJson(testInitializationModel));
            for (ITestNGMethod method : context.getSuite().getAllMethods()) {
                method.setRetryAnalyzerClass(Retry.class);
            }
            log.info("Before Suite ended successfully...");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            log.error(ExceptionUtils.getStackTrace(e));
            Assert.fail(ExceptionUtils.getMessage(e));
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method){
        try {
            log.info("I'm on before method, setting web driver");
            DriverContext.setDriver(new BrowserContext().getBrowser(testInitializationModel.getBrowserType()).run());
            log.info("driver set action completed, navigating to url : {}",testInitializationModel.getBaseUrl());
            getDriver().navigate().to(testInitializationModel.getBaseUrl());
            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30L));
            PageContext.setCurrentPage(new BasePageMethods());
            log.info("before method completed");
        }
        catch (Exception e){
            log.error(ExceptionUtils.getMessage(e));
            log.error(ExceptionUtils.getStackTrace(e));
            Assert.fail("Error on beforeMethod, message :" + ExceptionUtils.getMessage(e));
        }
    }

    @BeforeClass(alwaysRun = true)
    public static void beforeClass() {
        log.info("I'm on before class'");
    }


    @AfterMethod(alwaysRun = true)
    public static void afterMethod(ITestResult result) {
        log.info("I'm on after method, quiting driver");
        try {
            if(getDriver() != null){
                getDriver().quit();
                log.info("Successfully quit Webdriver..");
            }
        }
        catch (Exception e){
            log.error(ExceptionUtils.getMessage(e));
            log.error(ExceptionUtils.getStackTrace(e));
            Assert.fail("Error on After Method, message : " + ExceptionUtils.getMessage(e));
        }
    }
}

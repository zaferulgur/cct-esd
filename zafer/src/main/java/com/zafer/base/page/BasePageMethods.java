package com.zafer.base.page;

import com.zafer.base.driver.DriverContext;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

@Log4j2
public class BasePageMethods extends BasePage {
    private static final long timeOutInSeconds = 60L;

    protected void waitForElementVisible(WebElement elementFindBy) {
        try {
            log.debug("waiting for element to visible : {}", elementFindBy.toString());
            FluentWait<WebDriver> wait = (new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(timeOutInSeconds))).pollingEvery(Duration.ofSeconds(5L)).withTimeout(Duration.ofSeconds(timeOutInSeconds)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(elementFindBy));
        } catch (Exception e) {
            log.error("Element: " + elementFindBy + " WebElement is not visible !!, error message : {}",ExceptionUtils.getMessage(e));
            Assert.fail("Element: " + elementFindBy + " WebElement is not visible !!");
        }
    }

    protected void waitForElementClickable(final WebElement elementFindBy) {
        try {
            log.debug("waiting for element to be clickable");
            FluentWait<WebDriver> wait = new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(timeOutInSeconds))
                    .pollingEvery(Duration.ofSeconds(5))
                    .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.elementToBeClickable(elementFindBy));
            log.debug("element is clickable");
        } catch (Exception e) {
            log.error("Element: " + elementFindBy + " WebElement is not clickable, error message : {}",ExceptionUtils.getMessage(e));
            Assert.fail("Element: " + elementFindBy + " WebElement is not clickable !!");
        }
    }

    protected void scrollElementToMiddle(WebElement element) {
        JavascriptExecutor j = (JavascriptExecutor) DriverContext.getDriver();
        j.executeScript("arguments[0].scrollIntoView(true)", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

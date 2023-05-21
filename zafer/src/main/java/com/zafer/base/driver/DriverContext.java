package com.zafer.base.driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DriverContext {
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver() {
        log.debug("getting driver");
        return driverThread.get();
    }

    public static void setDriver(WebDriver driver) {
        log.debug("setting driver");
        driverThread.set(driver);
    }
}

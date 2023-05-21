package com.zafer.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends HomePage {
    @FindBy(how = How.ID,using = "username")
    private WebElement usernameInput;
    @FindBy(how = How.ID,using = "password")
    private WebElement passwordInput;
    @FindBy(how = How.XPATH,using = ".//button[1]")
    private WebElement loginButton;

    public LoginPage fillUserName(String userName) {
        waitForElementVisible(usernameInput);
        usernameInput.sendKeys(userName);
        return this;
    }

    public LoginPage fillPassword(String password) {
        waitForElementVisible(passwordInput);
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage clickLogin() {
        loginButton.click();
        return this;
    }
}

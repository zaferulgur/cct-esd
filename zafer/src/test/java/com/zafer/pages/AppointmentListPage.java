package com.zafer.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AppointmentListPage extends HomePage {
    @FindBy(how = How.XPATH,using = "(.//button[@type='button' and contains(text(),'Action')])[1]")
    private WebElement actionsMenuButton;
    @FindBy(how = How.XPATH,using = "(.//a[contains(@href,'view_details')])[1]")
    private WebElement detailsMenuElement;
    @FindBy(how = How.XPATH,using = "(.//a[contains(@class,'delete_data')])[1]")
    private WebElement deleteMenuElement;
    @FindBy(how = How.ID,using = "confirm")
    private WebElement confirmDelete;

    public AppointmentListPage clickActionsMenu() {
        waitForElementVisible(actionsMenuButton);
        waitForElementClickable(actionsMenuButton);
        actionsMenuButton.click();
        return this;
    }

    public void clickDetailsMenuElement() {
        waitForElementVisible(detailsMenuElement);
        waitForElementClickable(detailsMenuElement);
        detailsMenuElement.click();
    }

    public void delete() {
        waitForElementVisible(deleteMenuElement);
        waitForElementClickable(deleteMenuElement);
        deleteMenuElement.click();
        waitForElementVisible(confirmDelete);
        waitForElementClickable(confirmDelete);
        confirmDelete.click();
    }
}

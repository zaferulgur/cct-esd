package com.zafer.pages;

import com.zafer.base.page.BasePageMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePageMethods  {
    @FindBy(how = How.ID,using = "appschedulesDropDon")
    private WebElement appointmentMenuElement;

    @FindBy(how = How.ID,using = "appadd")
    private WebElement appointmentAddMenuElement;

    @FindBy(how = How.ID,using = "appview")
    private WebElement appointmentViewListMenuElement;

    public HomePage clickAppointmentMenuElement() {
        waitForElementVisible(appointmentMenuElement);
        waitForElementClickable(appointmentMenuElement);
        appointmentMenuElement.click();
        return this;
    }

    public HomePage clickAppointmentAddMenuElement() {
        waitForElementVisible(appointmentAddMenuElement);
        waitForElementClickable(appointmentAddMenuElement);
        appointmentAddMenuElement.click();
        return this;
    }

    public HomePage clickAppointmentViewListMenuElement() {
        waitForElementVisible(appointmentViewListMenuElement);
        waitForElementClickable(appointmentViewListMenuElement);
        appointmentViewListMenuElement.click();
        return this;
    }

}

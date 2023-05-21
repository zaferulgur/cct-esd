package com.zafer.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class AddAppointmentPage extends HomePage {
    @FindBy(how = How.ID,using = "department_id")
    private WebElement selectDepartment;
    @FindBy(how = How.ID,using = "date")
    private WebElement dateInput;
    @FindBy(how = How.ID,using = "time")
    private WebElement timeInput;
    @FindBy(how = How.ID,using = "hospital")
    private WebElement hospitalInput;
    @FindBy(how = How.ID,using = "remarks")
    private WebElement remarksInput;
    @FindBy(how = How.XPATH,using = ".//button[@type='submit']")
    private WebElement saveButton;
    @FindBy(how = How.XPATH,using = "/html/body/footer//div[@class='text-center']/p[1]")
    private WebElement scrol;

    public AddAppointmentPage selectDepartment() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        waitForElementVisible(selectDepartment);
        Select slc = new Select(selectDepartment);
        slc.selectByIndex(1);
        return this;
    }

    public AddAppointmentPage fillDate(String date) {
        dateInput.sendKeys(date);
        return this;
    }

    public AddAppointmentPage fillTime(String time) {
        timeInput.sendKeys(time);
        return this;
    }

    public AddAppointmentPage fillHospital(String hospital) {
        hospitalInput.sendKeys(hospital);
        return this;
    }

    public AddAppointmentPage fillRemarks(String remarks) {
        remarksInput.sendKeys(remarks);
        return this;
    }

    public AddAppointmentPage clickSave() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        waitForElementClickable(saveButton);
        scrollElementToMiddle(saveButton);
        saveButton.click();
        return this;
    }
}

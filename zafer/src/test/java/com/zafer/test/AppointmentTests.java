package com.zafer.test;

import com.zafer.base.TestBase;
import com.zafer.base.page.PageContext;
import com.zafer.pages.AddAppointmentPage;
import com.zafer.pages.AppointmentListPage;
import com.zafer.pages.LoginPage;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class AppointmentTests extends TestBase {

    @Test(priority = 0)
    public void login() throws InterruptedException {
        PageContext.getCurrentPage().as(LoginPage.class)
                .fillUserName("mcooper")
                .fillPassword("mcooper123")
                .clickLogin();
        TimeUnit.SECONDS.sleep(1L);
    }

    @Test(priority = 1)
    public void addNewAppointment() throws InterruptedException {
        PageContext.getCurrentPage().as(LoginPage.class)
                .fillUserName("mcooper")
                .fillPassword("mcooper123")
                .clickLogin()
                .clickAppointmentMenuElement()
                .clickAppointmentAddMenuElement();
        PageContext.getCurrentPage().as(AddAppointmentPage.class)
                .selectDepartment()
                .fillDate("22/05/2023")
                .fillTime("15:10")
                .fillHospital("Zafer")
                .fillRemarks("Zafer Desc")
                .clickSave();
        TimeUnit.SECONDS.sleep(1L);
    }

    @Test(priority = 3)
    public void openAppointmentDetailsPage() throws InterruptedException {
        PageContext.getCurrentPage().as(LoginPage.class)
                .fillUserName("mcooper")
                .fillPassword("mcooper123")
                .clickLogin()
                .clickAppointmentMenuElement()
                .clickAppointmentViewListMenuElement();
        PageContext.getCurrentPage().as(AppointmentListPage.class)
                .clickActionsMenu()
                .clickDetailsMenuElement();
        TimeUnit.SECONDS.sleep(1L);
        TimeUnit.SECONDS.sleep(2);
    }

    @Test(priority = 4)
    public void deleteAppointmentDetailsPage() throws InterruptedException {
        PageContext.getCurrentPage().as(LoginPage.class)
                .fillUserName("mcooper")
                .fillPassword("mcooper123")
                .clickLogin()
                .clickAppointmentMenuElement()
                .clickAppointmentViewListMenuElement();
        PageContext.getCurrentPage().as(AppointmentListPage.class)
                .clickActionsMenu()
                .delete();
        TimeUnit.SECONDS.sleep(1L);
    }
}

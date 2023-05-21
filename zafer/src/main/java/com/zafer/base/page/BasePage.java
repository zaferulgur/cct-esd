package com.zafer.base.page;

import com.zafer.base.driver.DriverContext;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public <TPage extends BasePage> TPage as(Class<TPage> pageInstance) {
        try {
            TPage page = PageFactory.initElements(DriverContext.getDriver(), pageInstance);
            PageContext.setCurrentPage(pageInstance.cast(page));
            return pageInstance.cast(page);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }
}

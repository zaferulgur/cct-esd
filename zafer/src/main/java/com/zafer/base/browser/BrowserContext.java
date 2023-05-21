package com.zafer.base.browser;

import com.zafer.base.browser.types.BrowserChrome;
import lombok.SneakyThrows;
import java.util.HashMap;
import java.util.Map;

public class BrowserContext {
    private static final Map<BrowserType,BaseBrowser> browsers = new HashMap<>();

    public BrowserContext() {
        browsers.put(BrowserType.CHROME,new BrowserChrome());
    }

    @SneakyThrows
    public BaseBrowser getBrowser(BrowserType browserType) {
        if (browsers.containsKey(browserType)) {
            return browsers.get(browserType);
        } else {
            throw new Exception("Can not found browser type : " + browserType.name());
        }
    }
}

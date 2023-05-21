package com.zafer.base;

import com.zafer.base.browser.BrowserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestInitializationModel {
    private BrowserType browserType;
    private String gridUrl;
    private String remote;
    private String baseUrl;
    private Integer retryCount;
}

package com.zafer.util;

import com.zafer.base.TestBase;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int count  = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (count < TestBase.testInitializationModel.getRetryCount()) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                return true;
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

}

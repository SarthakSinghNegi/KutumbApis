package org.example.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.utils.Reporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestExecutionListener implements ITestListener {

    private ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = Reporter.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        test.log(Status.FAIL,"Test Failed");
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        test.log(Status.PASS,"Test Passed");
    }
}

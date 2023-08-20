package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporter {

    private static ExtentReports extent;

    private static void checkIfInitialised() {
        if (extent == null) {
            Date date = new Date();
            SimpleDateFormat dataFormat = new SimpleDateFormat("dd_MM_yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH_mm");
            String dateStr = dataFormat.format(date);
            String timeStr = timeFormat.format(date);

            String path = System.getProperty("user.dir") + "/reports/" + dateStr + "/" + timeStr + ".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("Kutumb API Automation Report");
            reporter.config().setDocumentTitle("API Automation Report");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Developer","Sarthak Singh Negi");
        }
    }

    public static ExtentTest createTest(String name) {
        checkIfInitialised();
        return extent.createTest(name);
    }

    public static void flush() {
        checkIfInitialised();
        extent.flush();
    }
}

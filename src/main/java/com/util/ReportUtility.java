package com.util;

import io.qameta.allure.Allure;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Properties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class ReportUtility implements IInvokedMethodListener, IExecutionListener, ITestListener {

  private static final String ALLURE_FILE = "src/main/resources/allure.properties";
  private long startTime;

  @SneakyThrows
  public static void captureScreenShotAndAttachToReport(WebDriver driver) {
    TakesScreenshot scr = (TakesScreenshot) driver;
    File src = scr.getScreenshotAs(OutputType.FILE);
    File dest = new File(
        System.getProperty("user.dir") + "/src/test/resources/screenshots/" + LocalDateTime.now()
            + ".png");
    FileUtils.copyFile(src, dest);
    Allure.addAttachment("Step", new FileInputStream(dest));
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    ReportUtility.captureScreenShotAndAttachToReport(DriverFactory.getDriver());
  }

  @Override
  public void onExecutionFinish() {
    try (FileReader reader = new FileReader(ALLURE_FILE)) {
      Properties properties = new Properties();
      properties.load(reader);
      String parentDir = properties.getProperty("allure.results.directory");
      File allureResultsDir = new File(parentDir + File.separator + "environment.properties");
      try (FileWriter writer = new FileWriter(allureResultsDir)) {
        properties.store(writer, "Execution data");
      }
    } catch (Exception e) {
      log.warn("OnExecutionFinish - Exception occurred", e);
    }
    long endTime = System.currentTimeMillis();
    log.info(
        "Test Execution finished and took around - " + (endTime - startTime) / 1000 + " seconds");
  }
}

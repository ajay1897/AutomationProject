package com.util;

import java.time.Duration;
import lombok.SneakyThrows;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {

  public static WaitUtility instance = null;

  public static WaitUtility getInstance() {
    if (instance == null) {
      instance = new WaitUtility();
    }
    return instance;
  }

  @SneakyThrows
  public void waitForPageLoaded() {
    ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript(
            "return document.readyState").toString()
        .equals("complete");
    try {
      implicitWait(1);
      WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60));
      wait.until(expectation);
    } catch (Exception error) {
      throw new Exception("Timeout while waiting for page to be loaded");
    }
  }

  @SneakyThrows
  public void waitForElementToBeVisible(WebElement webElement) {
    ExpectedCondition<WebElement> expectedCondition = ExpectedConditions.visibilityOf(webElement);
    WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60));
    wait.until(expectedCondition);
    Thread.sleep(2000);
  }

  @SneakyThrows
  public void implicitWait(long duration) {
    Thread.sleep(duration*1000);
  }
}

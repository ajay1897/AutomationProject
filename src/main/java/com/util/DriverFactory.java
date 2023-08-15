package com.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

@Slf4j
public class DriverFactory {

  public static DriverFactory instance = null;
  static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

  public static WebDriver setDriver(String browserName) throws Exception {
    WebDriver driver;
    switch (browserName.toUpperCase()) {
      case "CHROME" -> {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        log.info("Chrome browser setup successful");
      }
      case "FIREFOX" -> {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        log.info("Firefox browser setup successful");
      }
      case "SAFARI" -> {
        WebDriverManager.safaridriver();
        driver = new SafariDriver();
        log.info("Safari browser setup successful");
      }
      case "EDGE" -> {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        log.info("Edge browser setup successful");
      }
      default -> {
        log.error("Invalid browser name : " + browserName);
        throw new Exception("Invalid browser name" + browserName);
      }
    }
    webDriver.set(driver);
    return driver;
  }

  public static DriverFactory getInstance() {
    if (instance == null) {
      instance = new DriverFactory();
    }
    return instance;
  }

  public static WebDriver getDriver() {
    return webDriver.get();
  }
}

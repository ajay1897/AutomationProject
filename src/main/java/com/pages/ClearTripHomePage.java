package com.pages;

import io.qameta.allure.Step;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.util.WaitUtility;

@Slf4j
public class ClearTripHomePage {

  final WebDriver driver;
  final WaitUtility waitUtility = WaitUtility.getInstance();
  @FindBy(xpath = "(//*[@class='fw-500 fs-4 ml-2'])[1]")
  WebElement tripType;
  @FindBy(xpath = "//li[@value='oneway']")
  WebElement oneWay;
  @FindBy(xpath = "//input[@placeholder='Where from?']")
  WebElement source;
  @FindBy(xpath = "//input[@placeholder='Where to?']")
  WebElement destination;
  @FindBy(className = "airportList")
  WebElement selectCity;
  @FindBy(xpath = "//div[@class='fs-4 fw-500 c-inherit flex flex-nowrap ml-4']")
  WebElement selectDate;
  @FindBy(xpath = "(//*[@class='fw-500 fs-4 ml-2'])[2]")
  WebElement selectTravellers;
  @FindBy(xpath = "(//*[@class='current-stroke c-blue c-pointer'])[1]")
  WebElement addAdult;
  @FindBy(xpath = "(//*[@class='current-stroke c-blue c-pointer'])[3]")
  WebElement addChildren;
  @FindBy(xpath = "//span[contains(text(),'Search flights')]")
  WebElement searchButton;

  public ClearTripHomePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @SneakyThrows
  @Step("Selecting travel date")
  public void selectDates(int bookingDate) {
    waitUtility.waitForPageLoaded();
    selectDate.click();
    Date date = new Date();
    date.setDate(date.getDate() + bookingDate);
    SimpleDateFormat df1 = new SimpleDateFormat("MMM dd yyyy");
    String dateOfTravel = df1.format(date);
    log.info(dateOfTravel);
    waitUtility.implicitWait(2);
    WebElement actualTravelDate = driver.findElement(By.xpath("//*[contains(@aria-label,'"+dateOfTravel+"')]"));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", actualTravelDate);
    log.info("Selected actual travel date");
  }

  @Step("Clicking on search button")
  public void clickSearchButton() {
    searchButton.click();
    log.info("Click on search button");
  }

  @SneakyThrows
  @Step("Selecting travellers")
  public void selectTravellers(boolean returnFlight) {
    waitUtility.waitForPageLoaded();
    selectTravellers.click();
    if(returnFlight) {
      addAdult.click();
    }
    addChildren.click();
    log.info("Selected adults and children");
  }

  @SneakyThrows
  @Step("Selecting source and destination")
  public void selectSourceAndDestination(String src, String dest) {
    waitUtility.waitForPageLoaded();
    source.click();
    source.sendKeys(src);
    waitUtility.implicitWait(2);
    selectCity.click();
    destination.click();
    destination.sendKeys(dest);
    waitUtility.implicitWait(2);
    selectCity.click();
    log.info("Added source: " + src + " and destination: " + dest);
  }
}

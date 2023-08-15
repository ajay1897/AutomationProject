package com.pages;

import com.util.Constants;
import io.qameta.allure.Step;
import java.util.Set;
import lombok.SneakyThrows;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.util.WaitUtility;

public class ClearTripItineraryPage {

  final WebDriver driver;
  final WaitUtility waitUtility = WaitUtility.getInstance();

  @FindBy(xpath = "//div[@data-testid='fareCard0']")
  WebElement standardFare;
  @FindBy(xpath = "//*[@class=' c-pointer c-neutral-900']")
  WebElement loginPopup;

  @FindBy(xpath = "//button[contains(text(),'Continue')]")
  WebElement continueButton;
  @FindBy(xpath = "(//button[contains(text(),'Continue')])[3]")
  WebElement continueButton2;

  @FindBy(xpath = "//button[contains(text(),'Skip this step')]")
  WebElement skipButton;
  @FindBy(xpath = "//input[@placeholder='Mobile number']")
  WebElement mobileNumber;
  @FindBy(xpath = "//input[@placeholder='Email address']")
  WebElement email;

  @FindBy(xpath = "//input[@placeholder='First name']")
  WebElement firstNameAdult;
  @FindBy(xpath = "(//input[@placeholder='First name'])[1]")
  WebElement firstNameChild;
  @FindBy(xpath = "//input[@placeholder='Last name']")
  WebElement lastNameAdult;
  @FindBy(xpath = "(//input[@placeholder='Last name'])[1]")
  WebElement lastNameChild;
  @FindBy(xpath = "//button[contains(text(),'Continue to payment')]")
  WebElement continueToPayment;

  public ClearTripItineraryPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }


  @SneakyThrows
  @Step("Entering information in itinerary page")
  public void addInfoInItineraryPage() {
    waitUtility.waitForPageLoaded();
    Set<String> handles = driver.getWindowHandles();
    String currentHandle = driver.getWindowHandle();
    for (String handle : handles) {

      if (!handle.equals(currentHandle)) {
        driver.switchTo().window(handle);
      }
    }
    waitUtility.waitForPageLoaded();
    loginPopup.click();
    standardFare.click();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript(Constants.JS_CLICK, continueButton);
    waitUtility.waitForElementToBeVisible(skipButton);
    js.executeScript(Constants.JS_CLICK, skipButton);
    mobileNumber.sendKeys("9999999999");
    email.sendKeys("abc@xyz.com");
    waitUtility.waitForElementToBeVisible(continueButton2);
    js.executeScript(Constants.JS_CLICK, continueButton2);
    waitUtility.waitForElementToBeVisible(continueToPayment);
    js.executeScript(Constants.JS_CLICK, continueToPayment);
  }

  @Step("Adding traveller details")
  public void enterTravellerDetails() {
    firstNameAdult.sendKeys("ABC");
    firstNameChild.sendKeys("XYZ");
    lastNameAdult.sendKeys("CDE");
    lastNameChild.sendKeys("UVW");
    waitUtility.waitForElementToBeVisible(continueToPayment);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript(Constants.JS_CLICK, continueToPayment);
  }
}

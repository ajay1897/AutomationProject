package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.util.WaitUtility;

public class ClearTripSearchPage {

  final WebDriver driver;
  final WaitUtility waitUtility = WaitUtility.getInstance();
  @FindBy(xpath = "//p[contains(text(),'Non-stop')]")
  WebElement nonStopCheckBox;
  @FindBy(xpath = "//*[@class='h-10 flex-1 bg-primary-500 hover:bg-primary-600 c-white bc-transparent c-pointer w-100p py-1 px-3 h-8 fs-3 fw-600 t-all button bs-solid tp-color td-500 bw-1 br-4 lh-solid box-border']")
  WebElement bookButton;

  public ClearTripSearchPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @Step("Checking non-stop flight option")
  public void selectNonStopFlights() {
    waitUtility.waitForPageLoaded();
    nonStopCheckBox.click();
  }

  @Step("Clicking on book button for the cheapest flight")
  public void clickBookForCheapestFlight() {
    waitUtility.waitForPageLoaded();
    bookButton.click();
  }
}

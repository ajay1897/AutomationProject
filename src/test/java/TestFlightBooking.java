
import com.pages.ClearTripHomePage;
import com.pages.ClearTripItineraryPage;
import com.pages.ClearTripSearchPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.util.ReportUtility;
import com.util.WaitUtility;

@Slf4j
@Listeners(ReportUtility.class)
@Feature("Flight Booking from Clear-trip")
public class TestFlightBooking extends SetupAndTearDown {

  ClearTripHomePage clearTripHomePage;
  ClearTripItineraryPage clearTripItineraryPage;
  ClearTripSearchPage clearTripSearchPage;

  @Parameters({"source", "destination"})
  @Step("Booking flight from Bangalore to Hyderabad with 1 adult and 1 kid")
  @Test(priority = 2)
  public void bookFlightFromBengaluruToHyderabad(@Optional("Bangalore") String source,
      @Optional("Hyderabad") String destination) {
    clearTripHomePage = new ClearTripHomePage(driver);
    clearTripHomePage.selectSourceAndDestination(source, destination);
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripHomePage.selectTravellers(false);
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripHomePage.selectDates(14);
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripHomePage.clickSearchButton();
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    WaitUtility.getInstance().implicitWait(3);
    clearTripSearchPage = new ClearTripSearchPage(driver);
    clearTripSearchPage.selectNonStopFlights();
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripSearchPage.clickBookForCheapestFlight();
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripItineraryPage = new ClearTripItineraryPage(driver);
    clearTripItineraryPage.addInfoInItineraryPage();
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripItineraryPage.enterTravellerDetails();
    ReportUtility.captureScreenShotAndAttachToReport(driver);
  }

  @Parameters({"source", "destination"})
  @Step("Booking flight from Hyderabad to Bangalore with 2 adult and 1 kid")
  @Test(priority = 1)
  public void bookReturnFlightFromHyderabadToBengaluru(@Optional("Hyderabad") String source,
      @Optional("Bangalore") String destination) {
    clearTripHomePage = new ClearTripHomePage(driver);
    clearTripHomePage.selectSourceAndDestination(source, destination);
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripHomePage.selectTravellers(true);
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripHomePage.selectDates(15);
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripHomePage.clickSearchButton();
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    WaitUtility.getInstance().implicitWait(3);
    clearTripSearchPage = new ClearTripSearchPage(driver);
    clearTripSearchPage.selectNonStopFlights();
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripSearchPage.clickBookForCheapestFlight();
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripItineraryPage = new ClearTripItineraryPage(driver);
    clearTripItineraryPage.addInfoInItineraryPage();
    ReportUtility.captureScreenShotAndAttachToReport(driver);
    clearTripItineraryPage.enterTravellerDetails();
    ReportUtility.captureScreenShotAndAttachToReport(driver);
  }
}

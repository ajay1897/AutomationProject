import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.util.Constants;
import com.util.DriverFactory;

@Slf4j
public class SetupAndTearDown {
  static WebDriver driver;
  @SneakyThrows
  @Parameters("browserName")
  @BeforeMethod
  public void setup(@Optional("chrome") String browserName){
    driver = DriverFactory.setDriver(browserName);
    log.info("Browser launched");
    driver.manage().window().maximize();
    log.info("Browser window size maximized");
    driver.get(Constants.CLEAR_TRIP_HOME);
    Thread.sleep(2000);
    log.info("Navigated to " + Constants.CLEAR_TRIP_HOME + " page");
  }

  @AfterMethod
  public void tearDown(){
    driver.quit();
  }
}

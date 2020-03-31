import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstAppiumAndroidTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "R9JN1003YDJ");
        capabilities.setCapability("session-override", true);
        capabilities.setCapability("VERSION", "10.0.0");
//        capabilities.setCapability("deviceName","Emulator");
        capabilities.setCapability("platformName","Android");


        capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
// This package name of your app (you can get it from apk info app)
        capabilities.setCapability("appActivity",".Calculator"); // This is Launcher activity of your app (you can get it from apk info app)
//Create RemoteWebDriver instance and connect to the Appium server
        //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void testCal() throws Exception {
        //locate the Text on the calculator by using By.name()
        WebElement two=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"9\"]"));
        two.click();
        WebElement plus=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Plus\"]"));
        plus.click();
        WebElement four=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"6\"]"));
        four.click();
        WebElement equalTo=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Equal\"]"));
        equalTo.click();
        //locate the edit box of the calculator by using By.tagName()
        WebElement results=driver.findElement(By.xpath("//android.view.View[@content-desc=\"Calculator input field\"]"));
        //Check the calculated value on the edit box

        assert results.getText().equals("6"):"Actual value is : "+results.getText()+" did not match with expected value: 6";

    }

    @AfterClass
    public void teardown(){
        //close the app
        driver.quit();
    }
}
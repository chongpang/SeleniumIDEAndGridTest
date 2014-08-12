package com.rjc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(Parameterized.class)
public class TestCase4 {
  private RemoteWebDriver driver;
  private boolean acceptNextAlert = true;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  private DesiredCapabilities capabilities;

  @Parameters
  public static List<String[]> parameters() {
      List<String[]> browsers = new ArrayList<String[]>();
      browsers.add(new String[] { Platform.WINDOWS.toString(), "firefox", "31" });
      //browsers.add(new String[] { Platform.MAC.toString(), "safari", "6" });
      browsers.add(new String[] { Platform.WINDOWS.toString(), "chrome", "36" });
      browsers.add(new String[] { Platform.WINDOWS.toString(), "internet explorer", "11" });
      return browsers;
  }

  public TestCase4 (String platform, String browser, String version) {
      capabilities = new DesiredCapabilities();
      capabilities.setPlatform(Platform.valueOf(platform));
      capabilities.setBrowserName(browser);
      capabilities.setVersion(version);
  }

  @Before
  public void setUp() throws Exception {
      driver = new RemoteWebDriver(capabilities);
      baseUrl = "http://www.rjc.co.jp/";
      driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
  }

  @Test
  public void testCase4() throws Exception {
    driver.get(baseUrl + "/company/");
    driver.findElement(By.xpath("(//a[contains(text(),'会社概要・沿革')])[2]")).click();
    driver.findElement(By.cssSelector("#lNav > li > a")).click();
    driver.findElement(By.cssSelector("img.sp")).click();
    driver.findElement(By.cssSelector("#naviNews > a > span")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

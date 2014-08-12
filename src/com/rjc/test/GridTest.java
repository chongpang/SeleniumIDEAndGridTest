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
public class GridTest {
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
    
    public GridTest(String platform, String browser, String version) {
        capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.valueOf(platform));
        capabilities.setBrowserName(browser);
        capabilities.setVersion(version);
    }
	@Before
	public void setUp() throws Exception {
		driver = new RemoteWebDriver(capabilities);
		baseUrl = "http://www.seleniumhq.org";
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	@Test
	public void testCalc() throws Exception {
		
		System.out.println(GridTest.class.getName() + " " + capabilities.getBrowserName());
		
		driver.get(baseUrl + "/");
		
		driver.get(baseUrl + "/");
	    assertEquals("Selenium - Web Browser Automation", driver.getTitle());
	    driver.findElement(By.linkText("Projects")).click();
	    assertEquals("Selenium Projects", driver.getTitle());
	    driver.findElement(By.linkText("Selenium WebDriver")).click();
	    assertEquals("Selenium WebDriver", driver.getTitle());
	    driver.findElement(By.linkText("ChromeDriver")).click();
	    assertEquals("ChromeDriver", driver.getTitle());
		
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

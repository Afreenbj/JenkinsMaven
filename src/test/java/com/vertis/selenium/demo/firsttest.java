package com.vertis.selenium.demo;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class firsttest {
  private String baseUrl;
  private WebDriver driver;
  private ScreenshotHelper screenshotHelper;
  
  @BeforeClass
  public void openBrowser() {
    driver = new FirefoxDriver();
    driver.get("http://www.google.com");
    screenshotHelper = new ScreenshotHelper();
  }
  
  @AfterClass
  public void saveScreenshotAndCloseBrowser() throws IOException {
    screenshotHelper.saveScreenshot("screenshot.png");
    driver.quit();
  }
  
  @Test
  public void pageTitleAfterSearchShouldBeginWithDrupal() throws IOException, InterruptedException {
    WebElement searchField = driver.findElement(By.name("q"));
    searchField.sendKeys("Drupal!");
    searchField.submit();
    Thread.sleep(3000);
    System.out.println("Execution Completed!");
    
  }
  
  private class ScreenshotHelper {
  
    public void saveScreenshot(String screenshotFileName) throws IOException {
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(screenshot, new File(screenshotFileName));
    }
  }
}

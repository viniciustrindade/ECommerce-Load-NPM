package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Level;

/**
 * Created by aleftik on 6/18/14.
 */
public abstract class AcmeSessionLoadTest extends SessionLoadTest {


    public AcmeSessionLoadTest(String host, int port, int callDelay) {
        super(host, port, callDelay);
    }

    @Override
    void login() {
      WebDriver driver = getDriver();
      driver.get(getScheme() + getHost() +':' + getPort() + getLoginUrl());
      driver.findElement(By.id("textBox")).sendKeys(getUsername());
      driver.findElement(By.id("password")).sendKeys(getPassword());
      WebElement facebookHack = driver.findElement(By.id("fb"));
      facebookHack.click();
      try {Thread.currentThread().sleep(500);} catch (Exception ex){}
      driver.findElement(By.id("UserLogin_Login")).click();
      logger.info("loging into " + getScheme() + getHost() +':' + getPort() + getLoginUrl());

    }

    @Override
    void logout() {
        logger.info("loging into " + getScheme() + getHost() +':' + getPort() + getLoginUrl());
        WebDriver driver = getDriver();
        logger.info("loging into " + getScheme() + getHost() +':' + getPort() + getLoginUrl());
        driver.get(getScheme() + getHost() + ':' +  getPort() + "/appdynamicspilot/UserLogOut.action");
        WebElement facebookHack = driver.findElement(By.id("fb"));
        try {Thread.currentThread().sleep(500);} catch (Exception ex){}
        facebookHack.click();

    }

    @Override
    abstract void performLoad();

    abstract String getLoginUrl();

    abstract String getUsername();

    abstract String getPassword();

    abstract String getScheme();
}

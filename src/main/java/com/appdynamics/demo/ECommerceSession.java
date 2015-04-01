package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Level;

/**
 * Created by aleftik on 6/18/14.
 */
public abstract class ECommerceSession extends SessionLoadTest {


    public ECommerceSession(String host, int port, int callDelay) {
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
      logger.info("Logging into " + getScheme() + getHost() +':' + getPort() + getLoginUrl());

    }

    @Override
    void logout() {
        WebDriver driver = getDriver();
        driver.get(getScheme() + getHost() + ':' +  getPort() + "/appdynamicspilot/UserLogOut.action");
        logger.info("Logged out");
    }

    @Override
    abstract void performLoad();

    abstract String getLoginUrl();

    abstract String getUsername();

    abstract String getPassword();

    abstract String getScheme();
}

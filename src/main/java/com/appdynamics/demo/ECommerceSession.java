package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Level;

/**
 * Created by aleftik on 6/18/14.
 */
public abstract class ECommerceSession extends SessionLoadTest {


    public ECommerceSession(String host, int port, int angularPort, int callDelay) {
        super(host, port,angularPort, callDelay);
    }

    @Override
    void login() {
        //jsp
        WebDriver driver = getDriver();
        driver.get(getScheme() + getHost() + ':' + getPort() + getLoginUrl());
        driver.findElement(By.id("textBox")).sendKeys(getUsername());
        driver.findElement(By.id("password")).sendKeys(getPassword());
        WebElement facebookHack = driver.findElement(By.id("fb"));
        facebookHack.click();
        try {
            Thread.currentThread().sleep(500);
        } catch (Exception ex) {
        }
        driver.findElement(By.id("UserLogin_Login")).click();
        logger.info("Logging into " + getScheme() + getHost() + ':' + getPort() + getLoginUrl());

        //Angular
        WebDriver angularDriver = getDriver();
        angularDriver.get(getScheme() + getHost() + ':' + getAngularPort() + getAngularLoginUrl());
        angularDriver.findElement(By.id("username")).sendKeys(getUsername());
        angularDriver.findElement(By.id("password")).sendKeys(getPassword());
        angularDriver.findElement(By.id("btnLogin")).click();
        logger.info("Logging into Angular " + getScheme() + getHost() + ':' + getAngularPort() + getAngularLoginUrl());
    }

    @Override
    void logout() {
        WebDriver driver = getDriver();
        driver.get(getScheme() + getHost() + ':' + getPort() + "/appdynamicspilot/UserLogOut.action");
        logger.info("Logged out");

        //Angular
        WebDriver angularDriver = getDriver();
        angularDriver.get(getScheme() + getHost() + ':' + getAngularPort() + getAngularProductsUrl());
        WebElement checkoutElem = angularDriver.findElement(By.id("aLogOut"));
        if(checkoutElem != null){
            checkoutElem.click();
            logger.info("Angular - Logged out");
        }
    }

    @Override
    abstract void performLoad();

    abstract String getLoginUrl();

    abstract String getAngularLoginUrl();

    abstract String getAngularProductsUrl();

    abstract String getUsername();

    abstract String getPassword();

    abstract String getScheme();
}

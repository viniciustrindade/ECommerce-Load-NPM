package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by aleftik on 6/18/14.
 */
public abstract class ECommerceAngularSession extends SessionLoadTest {


    public ECommerceAngularSession(String host, int port, int angularPort, int callDelay) {
        super(host, port, angularPort, callDelay);
    }

    @Override
    void login() {
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
        logger.info("Logged out Angular");
    }

    @Override
    abstract void performLoad();

    abstract String getAngularLoginUrl();

    abstract String getAngularProductsUrl();

    abstract String getUsername();

    abstract String getPassword();

    abstract String getScheme();
}

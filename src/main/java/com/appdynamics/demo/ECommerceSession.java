package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aleftik on 6/18/14.
 */
public abstract class ECommerceSession extends SessionLoadTest {


    public ECommerceSession(String host, int port, int angularPort, int callDelay, String mysqlHost, String mysqlUserName,String mysqlPwd) {
        super(host, port, angularPort, callDelay, mysqlHost,mysqlUserName,mysqlPwd);
    }

    @Override
    void login() {
        //jsp
        WebDriver driver = getDriver();
        driver.get(getScheme() + getHost() + ':' + getPort() + getLoginUrl());
        HashMap<String, String> mapUserInfo = getUserInfo();
        String userName = (String) mapUserInfo.keySet().toArray()[0];
        String password = mapUserInfo.get(userName);
        driver.findElement(By.id("textBox")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        WebElement facebookHack = driver.findElement(By.id("fb"));
        facebookHack.click();
        try {
            Thread.currentThread().sleep(500);
        } catch (Exception ex) {
        }
        driver.findElement(By.id("UserLogin_Login")).click();
        logger.info("Logging into " + getScheme() + getHost() + ':' + getPort() + getLoginUrl());
    }

    @Override
    void logout() {
        WebDriver driver = getDriver();
        driver.get(getScheme() + getHost() + ':' + getPort() + "/appdynamicspilot/UserLogOut.action");
        logger.info("Logged out");
    }

    @Override
    abstract void performLoad();

    abstract String getLoginUrl();

    abstract HashMap<String,String> getUserInfo();

    abstract String getScheme();
}

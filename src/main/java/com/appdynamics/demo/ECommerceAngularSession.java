package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aleftik on 6/18/14.
 */
public abstract class ECommerceAngularSession extends SessionLoadTest {


    public ECommerceAngularSession(String host, int port, int angularPort, int callDelay, Map<Integer,Map<String,String>> mapUser) {
        super(host, port, angularPort, callDelay, mapUser);
    }

    @Override
    void login() {
        //Angular
        WebDriver angularDriver = getDriver();
        angularDriver.get(getScheme() + getHost() + ':' + getAngularPort() + getAngularLoginUrl());
        HashMap<String,String> mapUserInfo = getUserInfo();
        String userName = (String) mapUserInfo.keySet().toArray()[0];
        String password =  mapUserInfo.get(userName);
        angularDriver.findElement(By.id("username")).sendKeys(userName);
        angularDriver.findElement(By.id("password")).sendKeys(password);
        angularDriver.findElement(By.id("btnLogin")).click();

    }

    @Override
    void logout() {
        logger.info("Logged out Angular");
    }

    @Override
    abstract void performLoad();

    abstract String getAngularLoginUrl();

    abstract String getAngularProductsUrl();

    abstract HashMap<String,String> getUserInfo();

    abstract String getScheme();
}

package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by aleftik on 6/18/14.
 */
public class ECommerceAngularCheckout extends ECommerceAngularSession {

    static private final Random randomGen = new Random();

    public ECommerceAngularCheckout(String host, int port, int angularPort, int callDelay, String mysqlHost, String mysqlUserName,String mysqlPwd) {
        super(host, port, angularPort, callDelay, mysqlHost,mysqlUserName,mysqlPwd);
    }

    @Override
    void performLoad() {
        WebDriver angularDriver = getDriver();
        angularDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        angularDriver.get(getScheme() + getHost() + ':' + getAngularPort() + getAngularProductsUrl());

        try {
            //Angular
            List<WebElement> ids = angularDriver.findElements(By.id("prodid"));
            int angularNumBooks = randomGen.nextInt(5);
            for (int i = 0; i < angularNumBooks + 1; i++) {
                int bookNumber = 1 + randomGen.nextInt(ids.size() - 1);
                WebElement angularAddToCart = angularDriver.findElement(By.xpath("//div[@id='prodid' and text()=" + bookNumber + "]")).findElement(By.xpath("./following-sibling::button"));
                logger.info("Angular - Selected book # " + bookNumber);
                angularAddToCart.click();
                logger.info("Angular - Items added to Cart");
            }

            WebDriver angularCheckoutDriver = getDriver();
            angularCheckoutDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            angularCheckoutDriver.get(getScheme() + getHost() + ':' + getAngularPort() + getAngularCartUrl());

            WebElement angularSubmit = angularCheckoutDriver.findElement(By.id("btnCheckout"));
            angularSubmit.click();
            logger.info("Angular - Checkout Cart");

        } catch (Exception ex) {
            logger.warning("Ignored Exception");
        }
    }


    @Override
    HashMap<String,String> getUserInfo() {
        Map<Integer,Map<String,String>>  mapUser =  getUserInformation();
        logger.info("userInfo Size : "+ mapUser.size());
        if(mapUser != null && mapUser.size() > 0) {
            Random generator = new Random();
            Object[] values = mapUser.values().toArray();
            HashMap<String,String> randomValue = (HashMap<String,String>) values[generator.nextInt(values.length)];
            logger.info("User Name : " + randomValue.keySet().toArray()[0]);
            logger.info("Password : " + randomValue.get(randomValue.keySet().toArray()[0]));
            return randomValue;
        }
        return null;
    }

    @Override
    String getAngularLoginUrl() {
        return "/angular/#/login";
    }

    @Override
    String getAngularProductsUrl() {
        return "/angular/#/";
    }

    String getAngularCartUrl() {
        return "/angular/#/cart";
    }

    String getScheme() {
        return "http://";
    }
}

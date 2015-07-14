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
public class ECommerceCheckout extends ECommerceSession {

    static private final Random randomGen = new Random();

    public ECommerceCheckout(String host, int port, int angularPort, int callDelay,  Map<Integer,Map<String,String>> mapUser) {
        super(host, port, angularPort, callDelay, mapUser);
    }

    @Override
    void performLoad() {


        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {

            //jsp
            List<WebElement> checkBoxes = driver.findElements(By.id("selectedId"));

            int numBooks = randomGen.nextInt(5);
            for (int i = 0; i < numBooks + 1; i++) {
                int bookNumber = randomGen.nextInt(checkBoxes.size() - 1);
                WebElement randCBox = (WebElement) checkBoxes.toArray()[bookNumber];
                randCBox.click();
                logger.info("Selected book # " + bookNumber);
            }

            WebElement addToCart = driver.findElement(By.id("itemsForm_submitValue"));
            addToCart.click();
            logger.info("Items added to Cart");

            WebElement submit = driver.findElement(By.id("ViewCart_submitValue"));
            submit.click();
            logger.info("Checkout Cart");

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
    String getLoginUrl() {
        return "/appdynamicspilot/";
    }

    String getScheme() {
        return "http://";
    }
}

package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by aleftik on 6/18/14.
 */
public class ECommerceCheckout extends ECommerceSession {

    static private final Random randomGen = new Random();

    public ECommerceCheckout(String host, int port, int angularPort, int callDelay) {
        super(host, port, angularPort, callDelay);
    }

    @Override
    void performLoad() {


        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebDriver angularDriver = getDriver();
        angularDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        angularDriver.get(getScheme() + getHost() + ':' + getAngularPort() + getAngularProductsUrl());

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

            //Angular
            List<WebElement> ids = angularDriver.findElements(By.id("prodid"));

            int angularNumBooks = randomGen.nextInt(5);
            for (int i = 0; i < angularNumBooks + 1; i++) {
                int bookNumber = randomGen.nextInt(ids.size() - 1);
                WebElement angularAddToCart = angularDriver.findElement(By.xpath("div[@id='prodid' and text()=" + bookNumber + "]")).findElement(By.xpath("./following-sibling::button"));
                logger.info("Angular - Selected book # " + bookNumber);

                angularAddToCart.click();
                logger.info("Angular - Items added to Cart");
            }

            WebDriver angularCheckoutDriver = getDriver();
            angularCheckoutDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            angularCheckoutDriver.get(getScheme() + getHost() + ':' + getAngularPort() + getAngularCartUrl());

            WebElement angularSubmit = driver.findElement(By.id("btnCheckout"));
            angularSubmit.click();
            logger.info("Angular - Checkout Cart");

        } catch (Exception ex) {
            logger.warning("Ignored Exception");
        }
    }


    @Override
    String getUsername() {
        String user;

        switch (randomGen.nextInt(35)) {
            case 0: user = "amitabhbachchan@foobar.com"; break;
            case 1: user = "christopher.lee@foobar.com"; break;
            case 2: user = "emilia.clarke@foobar.com"; break;
            case 3: user = "tom.hardy@foobar.com"; break;
            case 4: user = "kate.upton@foobar.com"; break;
            case 5: user = "leonardo.dicaprio@foobar.com"; break;
            case 6: user = "brad.pitt@foobar.com"; break;
            case 7: user = "jake.gyllenhaal@foobar.com"; break;
            case 8: user = "steph.curry@foobar.com"; break;
            case 9: user = "sachin.tendulkar@foobar.com"; break;
            case 10: user = "kobe.bryant@foobar.com"; break;
            case 11: user = "lebron.james@foobar.com"; break;
            case 12: user = "michael.schumacher@foobar.com"; break;
            case 13: user = "ma.lin@foobar.com"; break;
            case 14: user = "lin.dan@foobar.com"; break;
            case 15: user = "tom.brady@foobar.com"; break;
            case 16: user = "michael.phelps@foobar.com"; break;
            case 17: user = "mark.zuckerberg@foobar.com"; break;
            case 18: user = "larry.page@foobar.com"; break;
            case 19: user = "sheryl.sandberg@foobar.com'"; break;
            case 20: user = "jeff.bezos@foobar.com"; break;
            case 21: user = "lynn.davidson@appdynamics.com"; break;
            case 22: user = "tim.cook@foobar.com"; break;
            case 23: user = "marissa.mayer@foobar.com"; break;
            case 24: user = "steve.jobs@foobar.com"; break;
            case 25: user = "bill.gates@foobar.com"; break;
            case 26: user = "sergey.brin@foobar.com"; break;
            case 27: user = "john.legend@foobar.com"; break;
            case 28: user = "bruno.mars@foobar.com"; break;
            case 29: user = "meg.whitman@foobar.com"; break;
            case 30: user = "jp.morgan@foobar.com"; break;
            case 31: user = "richard.branson@foobar.com"; break;
            case 32: user = "larry.ellison@foobar.com"; break;
            case 33: user = "jyoti.bansal@foobar.com"; break;
            case 34: user = "satya.nadella@foobar.com"; break;
            case 35: user = "Solomon@foobar.com"; break;
            default: user = "amitabhbachchan@foobar.com"; break;
        }
        logger.info("User: " + user);
        return user;
    }

    @Override
    String getPassword() {
        return "appdynamics";
    }

    @Override
    String getLoginUrl() {
        return "/appdynamicspilot/";
    }

    @Override
    String getAngularLoginUrl() {
        return "/AngularUI/#/login";
    }

    @Override
    String getAngularProductsUrl() {
        return "/AngularUI/#/";
    }

    String getAngularCartUrl() {
        return "/AngularUI/#/cart";
    }


    String getScheme() {
        return "http://";
    }
}

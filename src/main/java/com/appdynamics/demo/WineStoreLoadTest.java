package com.appdynamics.demo;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.lang.Runnable;
import java.util.logging.Logger;
import java.io.*;

/**
 * Created by aleftik on 3/27/14.
 */
public class WineStoreLoadTest extends StaticRequestLoadTest {
    private static final Logger logger = Logger.getLogger(WineStoreLoadTest.class.getName());
    private static final String [] WINE_URLS = {":3000/wines",":3000/wines/529fd77b24e87a0000000013",":3000/wines/529fd77b24e87a0000000001",":3000/#wines"};


    public WineStoreLoadTest(String host,int port, int callDelay) {
        super(host,port, callDelay);
    }

    public String [] getUrls() {
        return WINE_URLS;
    }
}

package com.appdynamics.demo;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.sql.Statement;

/**
 * Created by aleftik on 6/18/14.
 */
public class PartnerPortalLoadTest extends StaticRequestLoadTest {
    private static String [] URLS = {"/rest/items/all.php","/catalog.php"};

    public PartnerPortalLoadTest(String host, int port, int callDelay) {
        super(host, port, callDelay);
    }

    public String [] getUrls() {
        return URLS;
    }

}

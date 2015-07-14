package com.appdynamics.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by aleftik on 6/18/14.
 */
public abstract class StaticRequestLoadTest implements Runnable {
    static final Logger logger = Logger.getLogger(StaticRequestLoadTest.class.getName());
    private String host = null;
    private int callDelay = 0;
    private WebDriver driver;
    private int port = 80;
    private int angularPort = 8080;
    private String mysqlHost = null;
    private String mysqlUserName = null;
    private String mysqlPwd = null;


    public StaticRequestLoadTest(String host, int port, int angularPort, int callDelay, String mysqlHost, String mysqlUserName,String mysqlPwd) {
        this.host = host;
        this.port = port;
        this.callDelay = callDelay;
        this.angularPort = angularPort;
        this.mysqlHost = mysqlHost;
        this.mysqlUserName = mysqlUserName;
        this.mysqlPwd = mysqlPwd;
    }

    public void init() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.cache.disk.enable", false);
        profile.setPreference("browser.cache.memory.enable", false);
        profile.setPreference("browser.cache.offline.enable", false);
        profile.setPreference("network.http.use-cache", false);
        profile.setPreference("dom.enable_resource_timing", true);
        driver = new FirefoxDriver(profile);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    public void run() {
        try {
            init();
            executeTest();

        } finally {
            destroy();
        }

    }

    protected void executeTest() {
        for (int i = 0; i < getUrls().length; i++) {
            fetchUrl(getUrls()[i]);
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.currentThread().sleep(callDelay);
        } catch (InterruptedException e) {
            logger.severe(e.getMessage());
        }
    }

    private void fetchUrl(String uri) {
        if (driver != null) {
            String url = "http://" + host + uri;
            logger.info("fetching :" + url);
            System.out.println("fetching :" + url);
            driver.get(url);
        } else {
            System.out.println("*** Web driver is null nothing to fetch ***");
            logger.info("*** Web driver is null nothing to fetch ***");
        }


    }

    public void destroy() {
        if (driver != null) {
            driver.quit();
            driver.close();
        }

    }

    protected WebDriver getDriver() {
        return this.driver;
    }

    protected String getHost() {
        return this.host;
    }


    protected int getPort() {
        return this.port;
    }

    protected int getAngularPort() {
        return this.angularPort;
    }

    protected String getMySqlHost() {
        return this.mysqlHost;
    }

    protected String getMySqlUserName() {
        return this.mysqlUserName;
    }

    protected String getMySqlPwd() {
        return this.mysqlPwd;
    }

    protected Map<Integer, Map<String, String>>  getUserInformation() {
        Map<Integer, Map<String, String>> mapUser = new HashMap<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + getMySqlHost() + ":3306/appdy?"
                    + "user=" + getMySqlUserName() + "&password=" + getMySqlPwd());
            logger.info("jdbc:mysql://" + getMySqlHost() + ":3306/appdy?"
                    + "user=" + getMySqlUserName() + "&password=" + getMySqlPwd());
            PreparedStatement statement = con.prepareStatement("select * from appdy.user");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Map<String, String> mapUserPwd = new HashMap<>();
                logger.info(resultSet.getString("email") + " " + resultSet.getString("password"));
                mapUserPwd.put(resultSet.getString("email"), resultSet.getString("password"));
                mapUser.put(resultSet.getInt("id"), mapUserPwd);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapUser;
    }

    abstract String[] getUrls();
}

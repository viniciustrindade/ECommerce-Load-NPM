package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by aleftik on 6/18/14.
 */
public class AcmeCartScenarioOneTest extends  AcmeSessionLoadTest{

    public AcmeCartScenarioOneTest(String host, int port, int callDelay) {
        super(host, port, callDelay);
    }

    @Override
    void performLoad() {
        WebDriver driver = getDriver();
        logger.info("running load ");
        List<WebElement> addCartButton = driver.findElements(By.id("itemsForm_submitValue"));
        WebElement checkBox = driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/div/div"));
        WebElement obamaBook = driver.findElement(By.xpath("//*[@id=\"selectedId\"]"));
        WebElement classes = driver.findElement(By.className("itemBoxes"));
        WebElement checkBoxes = driver.findElement(By.id("selectedId"));
        WebElement facebookHack = driver.findElement(By.id("fb"));


        try {Thread.currentThread().sleep(500);} catch (Exception ex){}
        checkBoxes.click();
        addCartButton.get(0).click();
        facebookHack = driver.findElement(By.id("fb"));
        try {Thread.currentThread().sleep(500);} catch (Exception ex){}

        facebookHack.click();
        WebElement submit = driver.findElement(By.id("ViewCart_submitValue"));
        facebookHack = driver.findElement(By.id("fb"));
        try {Thread.currentThread().sleep(500);} catch (Exception ex){}
        submit.click();
        try {Thread.currentThread().sleep(500);} catch (Exception ex){}

    }



    @Override
    String getUsername() {
        return "test";
    }

    @Override
    String getPassword() {
        return "appdynamics";
    }

    @Override
    String getLoginUrl() {
        return  "/appdynamicspilot/";
    }

    String getScheme() {
        return "http://";
    }
}

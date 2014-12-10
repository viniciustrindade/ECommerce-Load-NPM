package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Random;

import java.util.List;

/**
 * Created by aleftik on 6/18/14.
 */
public class AcmeCartScenarioTwoTest extends  AcmeSessionLoadTest{

    static private final Random randomGen = new Random();

    public AcmeCartScenarioTwoTest(String host, int port, int callDelay) {
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
        List <WebElement> checkBoxes = driver.findElements(By.id("selectedId"));
        WebElement facebookHack = driver.findElement(By.id("fb"));


        try {Thread.currentThread().sleep(500);} catch (Exception ex){}
        int numBooks = randomGen.nextInt(5);
        for (int i = 0; i < numBooks; i++) {
            WebElement randCBox = (WebElement) checkBoxes.toArray()[randomGen.nextInt(checkBoxes.size() - 1)];
            randCBox.click();
        }
        addCartButton.get(0).click();
        facebookHack = driver.findElement(By.id("fb"));
        try {Thread.currentThread().sleep(500);} catch (Exception ex){}

        facebookHack.click();
        WebElement submit = driver.findElement(By.id("ViewCart_submitValue"));
        facebookHack = driver.findElement(By.id("fb"));
        try {Thread.currentThread().sleep(500);} catch (Exception ex){}
        submit.click();

    }



    @Override
    String getUsername() {
        String user;

        switch (randomGen.nextInt(5)) {
            case 0: user = "test"; break;
            case 1: user = "vikash"; break;
            case 2: user = "santo"; break;
            case 3: user = "ravi"; break;
            case 4: user = "root"; break;
            default: user = "test"; break;
        }
        return user;
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

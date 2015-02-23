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
        try {Thread.currentThread().sleep(500);} catch (Exception ex){}

    }



    @Override
    String getUsername() {
        String user;

        switch (randomGen.nextInt(26)) {
            case 0: user = "test"; break;
            case 1: user = "appdynamics"; break;
            case 2: user = "val.chibisov@appdynamics.com"; break;
            case 3: user = "bhaskar.sunkara@appdynamics.com"; break;
            case 4: user = "mark.prichard@appdynamics.com"; break;
            case 5: user = "adam.leftik@appdynamics.com"; break;
            case 6: user = "sid.choudhury@appdynamics.com"; break;
            case 7: user = "rey.ong@appdynamics.com"; break;
            case 8: user = "mahesh.gandhe@appdynamics.com"; break;
            case 9: user = "nima haddadkaveh@appdynamics.com"; break;
            case 10: user = "ariel.smollar@appdynamics.com"; break;
            case 11: user = "amod.gupta@appdynamics.com"; break;
            case 12: user = "omed.habib@appdynamics.com"; break;
            case 13: user = "ian.mcguinness@appdynamics.com"; break;
            case 14: user = "harish.nataraj@appdynamics.com"; break;
            case 15: user = "ian.withrow@appdynamics.com"; break;
            case 16: user = "shiv.loka@appdynamics.com"; break;
            case 17: user = "akankshu.dhawan@appdynamics.com"; break;
            case 18: user = "jeff.morgan@appdynamics.com"; break;
            case 19: user = "pamela.clark@appdynamics.com"; break;
            case 20: user = "steve.hetland@appdynamics.com"; break;
            case 21: user = "lynn.davidson@appdynamics.com"; break;
            case 22: user = "ellen.evans@appdynamics.com"; break;
            case 23: user = "jacquie.finney@appdynamics.com"; break;
            case 24: user = "charles.smith@appdynamics.com"; break;
            case 25: user = "eric.mackay@appdynamics.com"; break;
            case 26: user = "byron.martin@appdynamics.com"; break;
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

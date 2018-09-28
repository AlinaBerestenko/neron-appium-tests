package com.neron.sendbx.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class AskQuestionScreenTest extends BaseTest{

    private String questionText = "AUTO question to check if OK";

    private Logger logger = LoggerFactory.getLogger(AskQuestionScreenTest.class);

    @BeforeClass
    public void setUpScreen() {
        theMind.feedScreen().clickAskButton();

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(theMind.askQuestionScreen().getAskQuestionInput()));
        }


    @BeforeMethod(alwaysRun = true)
    public void logBeforeMethod(Method m, Object[] p){
        logger.info("test start " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logAfterMethod(Method m, Object[] p){
        logger.info("test finished " + m.getName());
    }


    @Test(groups = "test")
    public void testIsAskPageIsAvailable() throws InterruptedException {
        Assert.assertTrue(theMind.askQuestionScreen().isAskPageLoaded());
    }

    @Test(groups = "test")
    public void testAskQuestionInputTextIsCorrespond() throws InterruptedException{
        Assert.assertEquals(theMind.askQuestionScreen().getQuestionInputText(), "Ask theMind for an opinion on anything…");
    }

    @Test(groups = "test")
    public  void testTextIsShownAfterInput() throws InterruptedException{
        theMind.askQuestionScreen().typeSomeTextIntoTextField(questionText);
        Assert.assertEquals(questionText, theMind.askQuestionScreen().getQuestionInputText());
        Assert.assertEquals(theMind.askQuestionScreen().getRemainingNumberCharachters(), "272");

    }

    @Test(groups = "test")
    public void testElementsAreClickable() throws InterruptedException{
        boolean status;
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(theMind.askQuestionScreen().getAddImageButton()));
        wait.until(ExpectedConditions.elementToBeClickable(theMind.askQuestionScreen().getBackButton()));
        theMind.askQuestionScreen().typeSomeTextIntoTextField(questionText);
        wait.until(ExpectedConditions.elementToBeClickable(theMind.askQuestionScreen().getSendQuestionButton()));
        status = true;
        Assert.assertTrue(status);


    }

    @AfterMethod(groups = "test", alwaysRun = true)
    public void reset(){
        resetApp();
    }

}

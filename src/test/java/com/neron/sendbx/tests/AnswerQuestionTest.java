package com.neron.sendbx.tests;

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

public class AnswerQuestionTest extends BaseTest {
    private Logger logger = LoggerFactory.getLogger(AskQuestionScreenTest.class);

    private String questionText = "AUTO question to check if OK";


    @BeforeClass
    public void setUpScreen() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(theMind.feedScreen().getAskButton()));
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
    public void testUserOpenAnswer() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 30);
        int[] coordinates = theMind.feedScreen().getBubblesSpaceCoordinates();
        wait.until(ExpectedConditions.visibilityOf(theMind.feedScreen().getAskButton()));
        Assert.assertTrue(theMind.feedScreen().askButtonDisplayed());
    }

    @AfterMethod(groups = "test", alwaysRun = true)
    public void reset(){
        resetApp();
    }
}

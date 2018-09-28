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

public class ChooseImageSourceScreenTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(AskQuestionScreenTest.class);

    @BeforeClass
    public void setUpScreen() {
        theMind.feedScreen().clickAskButton();
        theMind.askQuestionScreen().clickAddImageButton();

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(theMind.chooseImageSourceScreen().getCancelButton()));
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
    public void testButtonsTextIsCorrespond() throws InterruptedException{
        Assert.assertEquals(theMind.chooseImageSourceScreen().getChooseExistingPictureButton().getText(), "Choose Existing Picture");
        Assert.assertEquals(theMind.chooseImageSourceScreen().getTakeNewPictureButton().getText(), "Take New Picture");
        Assert.assertEquals(theMind.chooseImageSourceScreen().getCancelButton().getText(), "Cancel");
    }

    @Test(groups = "test")
    public void testElementsAreClickable() throws InterruptedException{
        boolean status;
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(theMind.chooseImageSourceScreen().getCancelButton()));
        wait.until(ExpectedConditions.elementToBeClickable(theMind.chooseImageSourceScreen().getChooseExistingPictureButton()));
        wait.until(ExpectedConditions.elementToBeClickable(theMind.chooseImageSourceScreen().getTakeNewPictureButton()));
        theMind.chooseImageSourceScreen().clickCancelButton();
        wait.until(ExpectedConditions.elementToBeClickable(theMind.chooseImageSourceScreen().getAddImageButton()));
        wait.until(ExpectedConditions.elementToBeClickable(theMind.chooseImageSourceScreen().getBackButton()));
        status = true;
        Assert.assertTrue(status);
    }

    @AfterMethod(groups = "test", alwaysRun = true)
    public void reset(){
        resetApp();
    }

}

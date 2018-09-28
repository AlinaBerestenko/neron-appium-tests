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

public class CropperScreenTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(AskQuestionScreenTest.class);

    @BeforeClass
    public void setUpScreen() {
        theMind.feedScreen().clickAskButton();
        theMind.askQuestionScreen().clickAddImageButton();
        theMind.chooseImageSourceScreen().clickChooseExistingPictureButton();
        theMind.chooseImageSourceScreen().clickOnAllowButton();
        theMind.galleryScreen().clickOnFirstElementInGallery();

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(theMind.cropperScreen().getCropperCircle()));
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
    public void testTextIsCorespond() throws InterruptedException{
        Assert.assertEquals(theMind.cropperScreen().getAskTitle().getText(), "Adjust photo");
    }

    @Test(groups = "test")
    public void testElementsAreClickable() throws InterruptedException{
        boolean status;
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(theMind.cropperScreen().getBackButton()));
        wait.until(ExpectedConditions.elementToBeClickable(theMind.cropperScreen().getConfirmButton()));
        status = true;
        Assert.assertTrue(status);
    }

    @AfterMethod(groups = "test", alwaysRun = true)
    public void reset(){
        resetApp();
    }

}

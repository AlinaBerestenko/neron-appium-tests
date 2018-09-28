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

public class AskFlowIntegrationTest extends BaseTest {
    private Logger logger = LoggerFactory.getLogger(AskQuestionScreenTest.class);

    private String questionText = "AUTO question to check if OK";


    @BeforeClass
    public void setUpScreen() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(theMind.feedScreen().getAskButton()));

        theMind.feedScreen().clickAskButton();
        wait.until(ExpectedConditions.visibilityOf(theMind.askQuestionScreen().getAddImageButton()));
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
    public void testAskQuestionWithoutImage() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 30);
        theMind.askQuestionScreen().typeSomeTextIntoTextField(questionText);
        theMind.askQuestionScreen().clickSendQuestionButton();
        wait.until(ExpectedConditions.visibilityOf(theMind.cancelSendingScreen().getSuccessfulSendText()));
        wait.until(ExpectedConditions.visibilityOf(theMind.feedScreen().getAskButton()));
        Assert.assertTrue(theMind.feedScreen().askButtonDisplayed());
    }

    @Test(groups = "test")
    public void testAsqQuestionFlowWithImageFromGallery() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 30);
        theMind.askQuestionScreen().clickAddImageButton();
        theMind.chooseImageSourceScreen().clickChooseExistingPictureButton();
        theMind.chooseImageSourceScreen().clickOnAllowButton();
        theMind.galleryScreen().clickOnFirstElementInGallery();
        wait.until(ExpectedConditions.visibilityOf(theMind.cropperScreen().getCropperCircle()));
        wait.until(ExpectedConditions.visibilityOf(theMind.cropperScreen().getConfirmButton()));
        Thread.sleep(5000);
        theMind.cropperScreen().clickConfirmButton();
        wait.until(ExpectedConditions.visibilityOf(theMind.askQuestionScreen().getAskQuestionInput()));
        theMind.askQuestionScreen().typeSomeTextIntoTextField(questionText);
        theMind.askQuestionScreen().clickSendQuestionButton();
        wait.until(ExpectedConditions.visibilityOf(theMind.feedScreen().getAskButton()));
        Assert.assertTrue(theMind.feedScreen().askButtonDisplayed());
    }
    @Test(groups = "test")
    public void testSelectImageAndCancel() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 30);
        theMind.askQuestionScreen().clickAddImageButton();
        theMind.chooseImageSourceScreen().clickCancelButton();
        wait.until(ExpectedConditions.visibilityOf(theMind.chooseImageSourceScreen().getUploadImageButton()));
        Assert.assertTrue(theMind.chooseImageSourceScreen().getUploadImageButton().isDisplayed());
    }

    @Test(groups = "test")
    public void testTakeNewPicture() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 30);
        theMind.askQuestionScreen().clickAddImageButton();
        theMind.chooseImageSourceScreen().clickTakeNewPictureButton();
        theMind.chooseImageSourceScreen().clickOnAllowButton();
        Thread.sleep(5000);
        theMind.takePictureScreen().clickTakePictureButton();
        Thread.sleep(5000);
        theMind.takePictureScreen().confirmTakenNewPicture();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(theMind.cropperScreen().getConfirmButton()));
        theMind.cropperScreen().clickConfirmButton();
        wait.until(ExpectedConditions.visibilityOf(theMind.askQuestionScreen().getAddImageButton()));
        theMind.askQuestionScreen().clickAddImageButton();
        Assert.assertTrue(theMind.chooseImageSourceScreen().getChooseExistingPictureButton().isDisplayed());
    }

    @Test(groups = "test")
    public void testSelectImageFromGalleryAndRemoveIt() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 30);
        theMind.askQuestionScreen().clickAddImageButton();
        theMind.chooseImageSourceScreen().clickChooseExistingPictureButton();
        theMind.chooseImageSourceScreen().clickOnAllowButton();
        theMind.galleryScreen().clickOnFirstElementInGallery();
        wait.until(ExpectedConditions.visibilityOf(theMind.cropperScreen().getCropperCircle()));
        wait.until(ExpectedConditions.visibilityOf(theMind.cropperScreen().getConfirmButton()));
        Thread.sleep(5000);
        theMind.cropperScreen().clickConfirmButton();
        wait.until(ExpectedConditions.visibilityOf(theMind.askQuestionScreen().getAskQuestionInput()));
        theMind.askQuestionScreen().clickAddImageButton();
        wait.until(ExpectedConditions.visibilityOf(theMind.cropperScreen().getDeletePhotoButton()));
        theMind.cropperScreen().clickDeletePhotoButton();
        theMind.chooseImageSourceScreen().clickCancelButton();
        theMind.cropperScreen().clickConfirmButtonAfterDeleteImage();
        Thread.sleep(5000);
        Assert.assertTrue(theMind.askQuestionScreen().getAskQuestionInput().isDisplayed());

    }

    @AfterMethod(groups = "test", alwaysRun = true)
    public void reset(){
        resetApp();
    }


}

package com.neron.sendbx.tests;



import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class AuthScreenTest extends BaseTest{

    Logger logger = LoggerFactory.getLogger(AuthScreenTest.class);

    @BeforeMethod(alwaysRun = true)
    public void logBeforeMethod(Method m, Object[] p){
        logger.info("test start " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logAfterMethod(Method m, Object[] p){
        logger.info("test finished " + m.getName()); ;
    }



    @Test(groups = "test")
    public void testSignInScreenAccess() throws InterruptedException {
        theMind.authScreen()
                .clickSignInButton();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(theMind.signInScreen().getSubmitButton()));
        Assert.assertTrue(theMind.signInScreen().isLoginPageLoaded());
    }

    @Test(groups = "test")
    public void testSignUpScreenAccess() throws InterruptedException {

        theMind.authScreen()
                .clickSignUpButton();
       Assert.assertTrue(theMind.signUpScreen().isSignUpPageLoaded());
    }

    @Test(groups = "test")
    public void testWelcomeTextCorrectness(){
        Assert.assertEquals(theMind.authScreen().getWelcomeText(),"We can’t wait to boggle your mind…");
    }


    @Test(groups = "test")
    public void testSignInWithFacebookButtonClickable() throws InterruptedException {
        Assert.assertTrue(theMind.authScreen().isSignInWithFaceboockButtonClickable());
    }

    @Test(groups = "test")
    public void testSigninWithGoogleCleckable(){
        Assert.assertTrue(theMind.authScreen().isSignInWithGoogleButtonClickable());
    }

    @Test(groups = "test")
    public void testRedirectToTermConditionsPage(){
       Assert.assertTrue(theMind.authScreen().isTermsConditionsButtonClickRedirectToTermsPage());
    }

    @Test(groups = "test")
    public void testRedirectToPrivacyPolicyPage(){
        Assert.assertTrue(theMind.authScreen().isPrivacyPolicyButtonClickRedirectToTermsPage());
    }

    @AfterMethod(groups = "test", alwaysRun = true)
    public void reset(){
        resetApp();
    }

}

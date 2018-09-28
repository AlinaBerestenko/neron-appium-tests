package com.neron.sendbx.tests;

import com.neron.sendbx.screens.SignInScreen;
import com.neron.sendbx.util.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignInScreenTest extends BaseTest{



    @BeforeMethod(groups = "test")
    public void goToSignInScreen() throws InterruptedException {
        theMind.authScreen().clickSignInButton();
    }

    Logger logger = LoggerFactory.getLogger(SignInScreen.class);

    @BeforeMethod(alwaysRun = true)
    public void logBeforeMethod(Method m, Object[] p){
        logger.info("test start " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logAfterMethod(Method m, Object[] p){
        logger.info("test finished " + m.getName()); ;
    }


    //Signin with correct credential.
    @Test(groups = "test")

    public void testCanSuccessfulSignIn() throws InterruptedException {
        User user  = new User(getTestUserEmail(), getTestUserPassword());
        theMind.signInScreen().successfulSignIn(user);
        Thread.sleep(1000);
        assertTrue(getActivity().equals("io.themind.neuron.ui.MainActivity"));

        //check is tutorial shown
        theMind.feedScreen().closeTutorialIfItPresent();
        logout();

    }


    /**
     * group of test SignIn with incorrect data and check error text
     */

    @Test(groups = "test")

    public void testSigninWithIncorrectPassword(){
        String passwordHelperText;
        String invalidPassword = "pass" + Math.random();
        User user = new User(getTestUserEmail(), getTestUserPassword());
        theMind.signInScreen().incorrectSignin(user);
        passwordHelperText = theMind.signInScreen().getPasswordHelperText();
        assertEquals(passwordHelperText, translation.get("SIGN_IN_INCORRECT_DATA"));

    }

    @Test(groups = "test")
    public void testSigninWithEmptyPasswordField(){
        String passwordHelperText;
        User user = new User(getTestUserEmail(),"");
        theMind.signInScreen().incorrectSignin(user);
        passwordHelperText = theMind.signInScreen().getPasswordHelperText();
        assertEquals(passwordHelperText, translation.get("SIG_IN_EMPTY_PASSWORD"));

    }

    @Test(groups = "test")
    public void testTrySigninNotFillTheSignInForm(){
        String emailHelperText;
        theMind.signInScreen().submitButtonClick();
        emailHelperText = theMind.signInScreen().getEmailHelperText();
        assertEquals(emailHelperText, translation.get("SIGN_IN_EMPTY_EMAIL"));
    }

    @Test(groups = "test")

    public void testSigninWithDoesnExistEmail(){
        String emailHelperText;
        User user = new User(getTestUserEmail(), getTestUserPassword());
        theMind.signInScreen().incorrectSignin(user);
        emailHelperText = theMind.signInScreen().getEmailHelperText();
        assertEquals(emailHelperText, translation.get("SIGN_IN_USER_NOT_FOUND"));

    }


 /////////////////////////////////////////////////////////////////////////////////////



    //Check moving to Signup screen
    @Test(groups = "test")
    public void testCanGoToSignUpScreen() throws InterruptedException {
        assertTrue(theMind.signInScreen()
                .clickRedirectToSignUp()
                .isSignUpPageLoaded());

    }
    //Check forgot password button work
    @Test(groups = "test")
    public void testGoToForgotPasswordScreen(){
        assertTrue(theMind.signInScreen().forgotPasswordbuttonclick().isForgotPasswordPageLoaded());
    }



    @AfterMethod(groups = "test", alwaysRun = true)
    public void reset(){
        resetApp();
    }

}

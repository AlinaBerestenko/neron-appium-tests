package com.neron.sendbx.tests;

import com.neron.sendbx.screens.SignUpScreen;
import com.neron.sendbx.util.Application;
import com.neron.sendbx.util.DataProviders;
import com.neron.sendbx.util.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class SignUpScreenTest extends BaseTest{

    Application app = new Application();

    boolean tutorialStatus = true;


    Logger logger = LoggerFactory.getLogger(SignUpScreen.class);

    @BeforeMethod(alwaysRun = true)
    public void logBeforeMethod(Method m, Object[] p){
        logger.info("test start " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logAfterMethod(Method m, Object[] p){
        logger.info("test finished " + m.getName()); ;
    }

    @BeforeMethod(alwaysRun = true)
    public void goToSignUpScreen() throws InterruptedException {
        theMind.authScreen().clickSignUpButton();;
    }




    //Check error message when enter invalid email
    @Test(groups ="test",dataProvider = "emailFieldCheckedData", dataProviderClass = DataProviders.class)
    public void testSignUpWithInvalidEmail(String email, String translationKey){
        User user = new User(email,"");
        theMind.signUpScreen().clickSignUpWithToggle().signUpWithIncorrectCredential(user);
        String helperText = theMind.signUpScreen().getEmailHelperText();
        assertEquals(helperText,translation.get(translationKey));
    }



    @Test(groups = "test")
    public void testTheShowPasswordButton() throws InterruptedException {
        String password = "123123";
        theMind.signUpScreen()
                .clickSignUpWithToggle()
                .setPassword(password);

        //select a toggle
        theMind.signUpScreen().clickShowPasswordToggle();

        //get toggle status
        String buttonStatusCheckedTrue = theMind.signUpScreen()
                .getShowPasswordButtonStatus();


        String confirmPassword = theMind.signUpScreen()
                .getPassword();

        //unselect a toggle
        theMind.signUpScreen()
                .clickShowPasswordToggle();

        //get toggle status
        String buttonStatusCheckedFalse = theMind.signUpScreen()
                .getShowPasswordButtonStatus();

        //check that showButton selected
        assertEquals("true",buttonStatusCheckedTrue);

        //check that showed password equal to entered
        assertEquals(password,confirmPassword);

        //check that show button not selected
        assertEquals("false",buttonStatusCheckedFalse);

        //Check that password hiding
        Thread.sleep(500);
        assertEquals(theMind.signUpScreen().getPassword(),"");
    }

    /**
     * should check that entered password meets the requirements "Min 8 symbols, upper and lower case symbol"
     */
    @Test(groups = "test",dataProvider = "passwordFieldCheckedData", dataProviderClass = DataProviders.class)
    public void checkEnteredPasswordCorrectness(String password) throws InterruptedException {
        User user = new User(getTestUserEmail(), password);
        theMind.signUpScreen().clickSignUpWithToggle().signUpWithIncorrectCredential(user);
        assertTrue(theMind.signUpScreen().isSignUpPageLoaded());

    }


    //Check that can't signup with exist email and check error message text
    @Test(groups = "test")
    public void checkSignUpWithExistEmail(){
        User user = new User(getTestUserEmail(), getTestUserPassword());
        theMind.signUpScreen()
                .clickSignUpWithToggle()
                .signUpWithIncorrectCredential(user);

        //theMind.signUpScreen().sendVerificationCodeClick();

        String emailHelperText = theMind.signUpScreen().getEmailHelperText();
        assertEquals(emailHelperText,  translation.get("EMAIL_EXIST_HELPER"));

    }



    @Test(groups = "test")
    public void checkRedirectToLogInScreen(){
        assertTrue(theMind.signUpScreen().redirectToLoginScreen().isLoginPageLoaded());
    }



    //Correct sign up flow from sign Up screen to Feed  - registration by Email
    @Test(groups = "test")
    public void signUpWithEmailUseCorrectData() throws InterruptedException {
        boolean emailExist = true;
        String email = null;

        //check is email exist
        while (emailExist == true){
            email = getTestUserEmail();
            System.out.println(email);
            emailExist = app.checkIsEmailPresent(email);
        }

        String password  = getTestUserPassword();
        User user = new User(email, password);
        String activity;


        //fill the Signup form
        theMind.signUpScreen().clickSignUpWithToggle().signUpWithCorrectCredential(user);

        //get and enter verification code
        String emailVerCode = app.getEmailValidationCode(email);
        pressCode(emailVerCode);

        //create the profile on profile screen
        theMind.profileScreen().createProfile();

        //check is ask button present on a Feed
        if (theMind.feedScreen().askButtonDisplayed() == true);
        { activity = getActivity(); }

        assertEquals("io.themind.neuron.ui.MainActivity", activity);

        //check is tutorial shown
        theMind.feedScreen().closeTutorialIfItPresent();
        logout();

        //drop User from database
        app.dropUserByEmail(email);
    }



    //signUp flow go to verification Screen from signIn screen
    @Test(groups = "test")
    public void signUpWithEmailFromSignInScreen() throws InterruptedException {
        boolean emailExist = true;
        String email = null;

        //check is email exist
        while (emailExist == true){
            email = getTestUserEmail();
            System.out.println(email);
            emailExist = app.checkIsEmailPresent(email);
        }
        String password  = getTestUserPassword();
        User user = new User(email, password);
        String activity;

        //fill the Signup form
        theMind.signUpScreen().clickSignUpWithToggle().signUpWithCorrectCredential(user);

        //step back to Auth screen page from Verification screen
        pressBack();
        pressBack();
        pressBack();

        //go to sign in screen
        theMind.authScreen().clickSignInButton();

        //Signin
        theMind.signInScreen().successfulSignIn(user);

        //get and enter verification code
        String emailVerCode = app.getEmailValidationCode(email);
        pressCode(emailVerCode);

        //fill the profile
        theMind.profileScreen().createProfile();

        //check is ask button present
        if (theMind.feedScreen().askButtonDisplayed() == true);
        { activity = getActivity(); }

        assertEquals("io.themind.neuron.ui.MainActivity", activity);

        //check is tutorial shown
        theMind.feedScreen().closeTutorialIfItPresent();
        logout();

        //drop User from database
        app.dropUserByEmail(email);

    }

    @AfterMethod(groups = "test", alwaysRun = true)
    public void reset(){
        resetApp();
    }

}

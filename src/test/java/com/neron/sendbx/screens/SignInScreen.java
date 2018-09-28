package com.neron.sendbx.screens;

import com.neron.sendbx.util.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SignInScreen extends AbstractScreen {
    public SignInScreen(AppiumDriver driver) {
        super(driver);
    }


    @AndroidFindBy (id = "imageView")
    private MobileElement theMindLogo;

    @AndroidFindBy (id = "credentialsEmail")
    private  MobileElement emailField;

    @AndroidFindBy (id = "credentialsPassword")
    private  MobileElement passwordField;

    @AndroidFindBy (id = "text_input_password_toggle")
    @iOSFindBy(accessibility = "eyeOpen")
    private MobileElement showPasswordToggle;

    @AndroidFindBy (id = "credentialsPasswordHelper")
    @iOSFindBy(accessibility = "Incorrect login or password")
    private MobileElement passwordHelperText;

    @iOSFindBy (accessibility = "Provide login data")
    private MobileElement provideLoginDataText;

    public MobileElement getSubmitButton() {
        return submitButton;
    }

    @AndroidFindBy (id = "credentialsSubmit")
    //@iOSFindBy(accessibility = "Login")
    private MobileElement submitButton;

    @AndroidFindBy (id = "tvResetPassword")
    @iOSFindBy(accessibility = "Forgot password?")
    private MobileElement forgotPasswordButton;

    @AndroidFindBy (id = "tvSignInRedirectToSignUp")
    @iOSFindBy(accessibility = "Already registered? Login")
    private MobileElement redirectToSignUp;

    @AndroidFindBy (id = "textinput_error")
    private MobileElement emailHelper;

    public boolean isLoginPageLoaded() {
        return submitButton.isDisplayed() && redirectToSignUp.isDisplayed();
    }

    public void fillEmail(String email){
        emailField.click();
        emailField.sendKeys(email);
    }


    public void fillPassword (String password){
        passwordField.click();
        passwordField.sendKeys(password);
    }

    public String getPasswordHelperText(){
       String helpertext =  passwordHelperText.getText();
        return helpertext;
    }

    public String getEmailHelperText(){
        return emailHelper.getText();
    }

    public SignUpScreen clickRedirectToSignUp(){
        redirectToSignUp.click();
        return  new SignUpScreen(driver);

    }

    public VerificationScreen submitButtonClick(){
        submitButton.click();
        return new VerificationScreen(driver);
    }

    public MobileElement getForgotPasswordButton() {
        return forgotPasswordButton;
    }

    public ForgotPasswordScreen forgotPasswordbuttonclick(){
        forgotPasswordButton.click();
        return new ForgotPasswordScreen(driver);
    }

    public void incorrectSignin(User user){
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        driver.hideKeyboard();
        submitButton.click();
    }



    public FeedScreen successfulSignIn(User user){
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        driver.hideKeyboard();
        submitButton.click();
        return new FeedScreen(driver);
    }

}

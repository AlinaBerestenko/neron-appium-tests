package com.neron.sendbx.screens;

import com.neron.sendbx.util.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpScreen extends AbstractScreen {
    public SignUpScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "signUpLogo")
    private MobileElement signUpLogo;

    @AndroidFindBy(id = "credentialsEmail")
    private MobileElement emailField;

    @AndroidFindBy(id = "credentialsPassword")
    private MobileElement passwordField;

    @AndroidFindBy(id = "text_input_password_toggle")
    @iOSFindBy (accessibility = "eyeOpen")
    private MobileElement passwordToggle;

    @AndroidFindBy(id = "credentialsPasswordHelper")
    @iOSFindBy(accessibility = "Min 8 symbols, upper and lower case symbol.")
    private MobileElement passwordHelper;

    @AndroidFindBy(id = "credentialsSubmit")
    @iOSFindBy(accessibility = "Send Verification Code")
    private MobileElement submitButton;

    @AndroidFindBy(id = "signUpCredentialsToggle")
    @iOSFindBy(accessibility = "Sign Up with Phone Number")
    private MobileElement signUpWithToggle;

    @AndroidFindBy(id = "signUpRedirectToLogin")
    @iOSFindBy(accessibility =  "Already registered? Login")
    private MobileElement redirectTooLogin;

    @AndroidFindBy (id = "intl_phone_edit__phone")
    private MobileElement phoneNumberField;

    @AndroidFindBy (id = "credentialsPhoneHelper")
    private MobileElement phoneHelper;
    @AndroidFindBy(id ="textinput_error")

    private MobileElement emailHelper;


    public boolean isSignUpPageLoaded() {
        return submitButton.isDisplayed()&& redirectTooLogin.isDisplayed();
    }

    public void setEmail(String email){
        emailField.click();
        emailField.sendKeys(email);
    }

    public void setPassword(String password){
        passwordField.click();
        passwordField.sendKeys(password);
    }

    public void clickShowPasswordToggle(){
        passwordToggle.click();
    }

     public String getEmailHelperText(){
        return emailHelper.getText();
     }

    public String getPasswordHelperText() {
        return passwordHelper.getText();
    }

    public String getPhoneHelperText(){
        return phoneHelper.getText();
    }

    public String getEmail(){
        return emailField.getText();
    }

    public String getPassword(){
        return passwordField.getText();
    }

    public void setPhoneNumber(String phoneNumber){
        phoneNumberField.click();
        phoneNumberField.sendKeys(phoneNumber);
    }



    public void sendVerificationCodeClick(){
        submitButton.click();
    }

    public SignInScreen redirectToLoginScreen(){
        redirectTooLogin.click();
        return new SignInScreen(driver);
    }

    public SignUpScreen clickSignUpWithToggle(){
        signUpWithToggle.click();
        return new SignUpScreen(driver);
    }

    public String signUpWithText() {
        return signUpWithToggle.getText();
    }

    public String getShowPasswordButtonStatus(){
        return passwordToggle.getAttribute("checked");
    }

    public VerificationScreen signUpWithCorrectCredential(User user){
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        driver.hideKeyboard();
        submitButton.click();
        WebDriverWait wait  = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(new VerificationScreen(driver).getVerificationScreentitleTop()));
        return new VerificationScreen(driver);
    }

    public void signUpWithIncorrectCredential(User user){
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        driver.hideKeyboard();
        submitButton.click();
    }





}

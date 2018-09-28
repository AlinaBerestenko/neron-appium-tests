package com.neron.sendbx.util;

import com.neron.sendbx.screens.*;
import io.appium.java_client.AppiumDriver;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class TheMind {

    private AppiumDriver driver;

    public TheMind(AppiumDriver driver) {
        this.driver = driver;
    }

    public AuthScreen authScreen() {
        return new AuthScreen(driver);
    }

    public SignInScreen signInScreen() {
        return new SignInScreen(driver);
    }

    public SignUpScreen signUpScreen() {
        return new SignUpScreen(driver);
    }

    public ForgotPasswordScreen forgotPasswordScreen() {
        return new ForgotPasswordScreen(driver);
    }

    public VerificationScreen verificationScreen() {
        return new VerificationScreen(driver);
    }

    public ProfileScreen profileScreen() {
        return new ProfileScreen(driver);
    }

    public FeedScreen feedScreen() {
        return new FeedScreen(driver);
    }

    public TheMindScreen theMindScreen() {
        return new TheMindScreen(driver);
    }

    public AskQuestionScreen askQuestionScreen(){ return new AskQuestionScreen(driver); }

    public ChooseImageSourceScreen chooseImageSourceScreen(){ return new ChooseImageSourceScreen(driver); }

    public CropperScreen cropperScreen(){ return new CropperScreen(driver); }

    public GalleryScreen galleryScreen(){ return new GalleryScreen(driver); }

    public TakePictureScreen takePictureScreen(){ return new TakePictureScreen(driver); }

    public CancelSendingScreen cancelSendingScreen(){ return new CancelSendingScreen(driver); }

    public AnswerScreen answerScreen(){ return new AnswerScreen(driver); }

}

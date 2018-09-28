package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class VerificationScreen extends AbstractScreen {

    public VerificationScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "tvVerificationScreenTitleTop")
    private MobileElement verificationScreentitleTop;

    @AndroidFindBy(id = "tvVerificationScreenRecipient")
    private MobileElement verificationScreenUserEmail;

    @AndroidFindBy(id = "tvVerificationScreenTitleBottom")
    private MobileElement verificationScreentitleBottom;

    public MobileElement getVerificationScreentitleTop() {
        return verificationScreentitleTop;
    }

    @AndroidFindBy(id = "id/verificationTitle")
    private MobileElement verificationCodeTitle;

    @AndroidFindBy (id = "number1")
    private MobileElement verifNumber1;

    @AndroidFindBy (id = "number2")
    private MobileElement verifNumber2;

    @AndroidFindBy (id = "number3")
    private MobileElement verifNumber3;

    @AndroidFindBy (id = "number4")
    private MobileElement verifNumber4;

    @AndroidFindBy (id = "number5")
    private MobileElement verifNumber5;

    @AndroidFindBy (id = "number6")
    private MobileElement verifNumber6;

    @AndroidFindBy (id = "btnVerificationUpdateRecipient")
    private MobileElement upadatePhoneNumber;

    @AndroidFindBy (id = "btnSignupVerificationResend")
    private MobileElement resendSms;


    public String getVerificationScreentitleTopText(){
        return verificationScreentitleTop.getText();
    }

    public String getVerificationScreentitleBottomText(){
        return verificationScreentitleBottom.getText();
    }

    public String getVerificationUserEmail(){
        return verificationScreenUserEmail.getText();
    }

    public String getVerificationCodeTitle(){
        return verificationCodeTitle.getText();
    }












}

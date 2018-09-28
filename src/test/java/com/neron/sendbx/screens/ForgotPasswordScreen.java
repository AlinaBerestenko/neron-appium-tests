package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ForgotPasswordScreen extends AbstractScreen {
    public ForgotPasswordScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy (id = "forgotPasswordTitle")
    private MobileElement forgotPageTitle;

    @AndroidFindBy (id = "resetLogo")
    private MobileElement logo;

    @AndroidFindBy (id =  "resetEmailOrPhone")
    private MobileElement emailField;

    @AndroidFindBy (id = "resetHelperText")
    private MobileElement helperText;

    @AndroidFindBy (id = "resetPasswordSubmit")
    private MobileElement submitButton;


    public boolean isForgotPasswordPageLoaded() {
        return forgotPageTitle.isDisplayed() &&  logo.isDisplayed();
    }
}

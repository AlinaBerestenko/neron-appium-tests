package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class AuthScreen extends AbstractScreen {

    public AuthScreen(AppiumDriver driver) {
        super (driver);
    }

    WebDriver.Timeouts wait = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



    @AndroidFindBy(id = "btnLauncherTheMind")
    //@iOSFindBy(accessibility = "Sign in with theMind")
    private MobileElement signinWithTheMindButton;

    @AndroidFindBy(id = "tvCreateAccount")
    //@iOSFindBy(accessibility = "create theMind account")
    private MobileElement createTheMindButton;

    @AndroidFindBy(id = "tvLauncherWelcomeTitle")
    //@iOSFindBy(accessibility = "We can’t wait to boggle your mind…")
    private MobileElement welcomeText;

    @AndroidFindBy(id = "launcherFragmentLogo")
    //@iOSFindBy(accessibility = "loginMind")
    private MobileElement launcherLogo;

    @AndroidFindBy (id = "btnLauncherFacebook")
    //@iOSFindBy(accessibility = "Sign in with Facebook")
    private MobileElement signInWithFacebookButton;

    @AndroidFindBy(id = "btnLauncherGoogle")
    //@iOSFindBy(accessibility = "Sign in with Google")
    private MobileElement signInWithGoogle;

    @AndroidFindBy(id = "launcherTerms")
   // @iOSFindBy(accessibility = "Terms & Conditions")
    private MobileElement termsandConditions;

    @AndroidFindBy(id = "launcherPolicy")
    //@iOSFindBy(accessibility = "Privacy Policy")
    private MobileElement privacyPolicy;

    @AndroidFindBy(id = "m_login_email" )
    private MobileElement facebookScreenLoginButton;


    public boolean isAuthPageLoaded() {
        return launcherLogo.isDisplayed() && welcomeText.isDisplayed();
    }


    public SignInScreen clickSignInButton() {

        signinWithTheMindButton.click();
        return new SignInScreen(driver);
    }

    public SignUpScreen clickSignUpButton(){
        createTheMindButton.click();
        return new SignUpScreen(driver);
    }

    public String getWelcomeText(){
        return welcomeText.getText();
    }

    public boolean isLogoPresent(){
       return launcherLogo.isDisplayed();
    }

    public boolean isSignInWithFaceboockButtonClickable() throws InterruptedException {
        boolean status;
        String clickable =  signInWithFacebookButton.getAttribute("clickable");
        if (clickable.equals("true")) status = true;
        else status = false;
        return status;
    }

    public boolean isSignInWithGoogleButtonClickable(){
        boolean status;
        String clickable =  signInWithGoogle.getAttribute("clickable");
       if (clickable.equals("true")) status = true;
       else status = false;
       return status;
    }

    public boolean isTermsConditionsButtonClickRedirectToTermsPage(){
        boolean status = false;
        termsandConditions.click();
        //If Android widget is present - select browser
        try {
           WebElement element = driver.findElement(By.id("android:id/resolver_list"));
                    element.findElement(By.xpath("//android.widget.LinearLayout[1]")).click();
        }
        catch (org.openqa.selenium.NoSuchElementException ex){
            System.out.println(ex);
        }

        try {
            WebElement element = driver.findElement(By.id("com.lge:id/resolver_list_lg"));
            element.findElement(By.xpath("//android.widget.LinearLayout[1]")).click();

        }
        catch (org.openqa.selenium.NoSuchElementException ex){
            System.out.println(ex);
        }

        //check is Terms title displayed
        try {
            status =  driver.findElementByAccessibilityId("Terms of Service").isDisplayed();
        }

        catch (org.openqa.selenium.NoSuchElementException ex){
            System.out.println(ex);
        }


        if (!status){
            try {
                status = driver.findElement(By.id("root")).isDisplayed();
            }
            catch (org.openqa.selenium.NoSuchElementException ex){
                System.out.println(ex);
            }



    }
    return status;}

    public boolean isPrivacyPolicyButtonClickRedirectToTermsPage(){
        boolean status = false;
        privacyPolicy.click();
        //If Android widget is present - select browser
        try {
            WebElement element = driver.findElement(By.id("android:id/resolver_list"));
            element.findElement(By.xpath("//android.widget.LinearLayout[1]")).click();
        }

        catch (org.openqa.selenium.NoSuchElementException ex){
            System.out.println(ex);
        }

        try {
            WebElement element = driver.findElement(By.id("com.lge:id/resolver_list_lg"));
            element.findElement(By.xpath("//android.widget.LinearLayout[1]")).click();
        }
        catch (org.openqa.selenium.NoSuchElementException ex){
            System.out.println(ex);
        }

        //check is Policy title displayed
        try {
        status = driver.findElementByAccessibilityId("Privacy Policy").isDisplayed();}
        catch (org.openqa.selenium.NoSuchElementException ex){
            System.out.println(ex);
        }

        if (!status){
            try {
                status = driver.findElement(By.id("root")).isDisplayed();
            }
            catch (org.openqa.selenium.NoSuchElementException ex){
                System.out.println(ex);
            }
        }

            return status;

    }





}

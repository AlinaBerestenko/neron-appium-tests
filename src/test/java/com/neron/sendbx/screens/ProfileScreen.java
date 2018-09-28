package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileScreen extends AbstractScreen {

    public ProfileScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy (id = "profileLogo")
    private MobileElement profileLogo;

    @AndroidFindBy (id = "rbProfileGenderMale")
    private MobileElement genderMaleRadioButton;

    @AndroidFindBy (id = "rbProfileGenderFemale")
    private MobileElement genderFemaleRadioButton;

    @AndroidFindBy (id =  "btnProfileBirthdate")
    private  MobileElement birthDateButton;

    @AndroidFindBy (id = "tvProfileNickname")
    private MobileElement nickname;

    @AndroidFindBy (id =  "btnProfileSubmit")
    private MobileElement submitButton;

    @AndroidFindBy(id = "io.themind.neuron.internal:id/userDetailsLogout")
    private MobileElement logOutButton;

    //calendar--------------
    @AndroidFindBy (accessibility  = "07 April 2018")
    private MobileElement dateOfBirth;

    @AndroidFindBy (id = "android:id/button2")
    private MobileElement canselButton;

    @AndroidFindBy (id = "android:id/button1")
    private MobileElement okButton;
    //----------------------


    public MobileElement getLogOutButton() {
        return logOutButton;
    }

    public boolean isProfileScreenLoaded(){
       return !genderMaleRadioButton.isDisplayed() && !profileLogo.isDisplayed() && dateOfBirth.isDisplayed();
    }

    public String selectGenderMale(){
        genderMaleRadioButton.click();
        return genderMaleRadioButton.getAttribute("checked");
    }

    public String selectGenderFemale(){
        genderFemaleRadioButton.click();
        return genderFemaleRadioButton.getAttribute("checked");
    }

    public SignInScreen clickLogoutButton(){
        logOutButton.click();
        return new SignInScreen(driver);
    }

    public void selectDateOfBirth() {

        //create selector for set date in calendar
        String birthDate = getDate()+ " 1980";

        //click button to calendar access
        birthDateButton.click();

        //wait after calendar is loading
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/date_picker_header_date")));

        //select needed date in calendar
        driver.findElementByAccessibilityId(birthDate).click();
        okButton.click();
    }


    public FeedScreen createProfile() {
        selectGenderFemale();
        selectDateOfBirth();
        signUpButtonClick();
        return new FeedScreen(driver);
    }

    public String getDateOfBirth (){
        return birthDateButton.getText();
    }

    public void signUpButtonClick(){
        submitButton.click();
    }



    //get current date in format (19 April - for example)
    public String getDate(){
        String birthDate = null;
        DateFormat sdf = new SimpleDateFormat("dd MMMM");
        Date date = new Date();
        birthDate = sdf.format(date);
        System.out.println(birthDate);
        return birthDate;
    }


}

package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TheMindScreen extends AbstractScreen {
    public TheMindScreen(AppiumDriver driver) {
        super(driver);
    }



    @AndroidFindBy (id = "overviewProfile")
    private MobileElement profileInfoButton;

    @AndroidFindBy (id = "overviewNickname")
    private MobileElement overviewNickname;

    @AndroidFindBy (id = "overviewAvatar")
    private MobileElement overviewAvatar;

    @AndroidFindBy (id = "overviewListCaption")
    private MobileElement overviewListCaption;

    @AndroidFindBy (id = "userDetailsLogout")
    private MobileElement logout;

    @AndroidFindBy (id = "userDetailsAvatar")
    private MobileElement userDetailsAvatar;

    @AndroidFindBy (id = "userDetailsNickname")
    private MobileElement userDetailsNickname;

    @AndroidFindBy (id = "userDetailsEmail")
    private MobileElement userDetailsEmail;

    @AndroidFindBy (id = "userDetailsPassword")
    private MobileElement userDetailsPassword;

    @AndroidFindBy (id = "userDetailsBirthDate")
    private MobileElement userDetailsBirthDate;

    @AndroidFindBy (id = "userDetailsGender")
    private MobileElement userDetailsGender;


    public MobileElement getProfileInfoButton() {
        return profileInfoButton;
    }

    public MobileElement getNickName() {
        return overviewNickname;
    }

    public MobileElement getAvatarImage() {
        return overviewAvatar;
    }

    public MobileElement getOverviewListCaption() {
        return overviewListCaption;
    }

    public void profileInfoButtonClick(){
        profileInfoButton.click();
    }

    public void logout(){
        logout.click();
    }

}

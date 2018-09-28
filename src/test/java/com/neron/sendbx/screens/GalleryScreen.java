package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class GalleryScreen extends AbstractScreen {

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout")
    private MobileElement firstPictureFromGallery;

    public GalleryScreen(AppiumDriver driver) {
        super(driver);
    }

    public CropperScreen clickOnFirstElementInGallery(){
        firstPictureFromGallery.click();
        return new CropperScreen(driver);
    }

}

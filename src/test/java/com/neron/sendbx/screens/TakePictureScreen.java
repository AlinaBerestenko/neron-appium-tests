package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TakePictureScreen extends AbstractScreen{

    @AndroidFindBy(id = "com.lge.camera:id/shutter_bottom_comp")
    private MobileElement takePictureButton;

    @AndroidFindBy(id = "com.lge.camera:id/btn_cancel")
    private MobileElement cancellButton;

    @AndroidFindBy(id = "com.lge.camera:id/btn_ok")
    private MobileElement okButton;

    @AndroidFindBy(id = "com.lge.camera:id/back_button")
    private MobileElement backButton;


    public TakePictureScreen(AppiumDriver driver) {
        super(driver);
    }

    public void reTakePicture(){
        cancellButton.click();
    }

    public void clickTakePictureButton(){
        takePictureButton.click();
    }

    public CropperScreen confirmTakenNewPicture(){
        okButton.click();
        return new CropperScreen(driver);
    }

    public ChooseImageSourceScreen clickBackButton(){
        backButton.click();
        return new ChooseImageSourceScreen(driver);
    }
}

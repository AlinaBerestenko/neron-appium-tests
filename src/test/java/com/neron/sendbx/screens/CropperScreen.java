package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CropperScreen extends AbstractScreen{

    @AndroidFindBy(id = "io.themind.neuron.internal:id/askPlaceConfirm")
    private MobileElement confirmButton;

    @AndroidFindBy(id = "io.themind.neuron.internal:id/askPlaceBack")
    private MobileElement backButton;

    @AndroidFindBy(id = "io.themind.neuron.internal:id/photoDelete")
    private MobileElement deletePhotoButton;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.ImageView")
    private MobileElement cropperCircle;

    @AndroidFindBy(id = "io.themind.neuron.internal:id/askTitle")
    private MobileElement askTitle;

    @AndroidFindBy(id = "io.themind.neuron.internal:id/askPhotoConfirm")
    private MobileElement askPhotoConfirm;

    public CropperScreen(AppiumDriver driver) {
        super(driver);
    }

    public MobileElement getAskPhotoConfirm() {
        return askPhotoConfirm;
    }

    public MobileElement getAskTitle() {
        return askTitle;
    }

    public MobileElement getConfirmButton() {
        return confirmButton;
    }

    public MobileElement getBackButton() {
        return backButton;
    }

    public MobileElement getDeletePhotoButton() {
        return deletePhotoButton;
    }

    public MobileElement getCropperCircle() {
        return cropperCircle;
    }

    public AskQuestionScreen clickConfirmButtonAfterDeleteImage(){
        askPhotoConfirm.click();
        return new AskQuestionScreen(driver);
    }

    public ChooseImageSourceScreen clickBackButton(){
        backButton.click();
        return new ChooseImageSourceScreen(driver);
    }

    public AskQuestionScreen clickConfirmButton(){
        confirmButton.click();
        return new AskQuestionScreen(driver);
    }

    public ChooseImageSourceScreen clickDeletePhotoButton(){
        deletePhotoButton.click();
        return new ChooseImageSourceScreen(driver);
    }
}

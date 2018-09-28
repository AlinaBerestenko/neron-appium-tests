package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ChooseImageSourceScreen extends AbstractScreen{

    @AndroidFindBy(id = "io.themind.neuron.internal:id/btnPickImage")
    private MobileElement addImageButton;

    @AndroidFindBy(id = "io.themind.neuron.internal:id/askPhotoBack")
    private MobileElement backButton;

    @AndroidFindBy(id = "io.themind.neuron.internal:id/askPhotoConfirm")
    private MobileElement confirmButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[1]")
    private MobileElement  chooseExistingPictureButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[2]")
    private MobileElement takeNewPictureButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[3]")
    private MobileElement cancelButton;

    @AndroidFindBy(xpath = "//android.widget.TextView")
    private MobileElement alertMessageText;

    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
    private MobileElement allowButton;

    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_deny_button")
    private MobileElement denyButton;

    @AndroidFindBy(id = "android:id/message")
    private MobileElement permissionsMessage;

    @AndroidFindBy(id = "android:id/button1")
    private MobileElement permissionsOKButton;

    @AndroidFindBy(id = "android:id/button2")
    private MobileElement permissionsCancelButton;

    @AndroidFindBy(id = "io.themind.neuron.internal:id/btnPickImage")
    private MobileElement uploadImageButton;


    public ChooseImageSourceScreen(AppiumDriver driver) {
        super(driver);
    }

    public MobileElement getChooseExistingPictureButton() {
        return chooseExistingPictureButton;
    }

    public MobileElement getTakeNewPictureButton() {
        return takeNewPictureButton;
    }

    public MobileElement getCancelButton() {
        return cancelButton;
    }

    public MobileElement getUploadImageButton() {
        return uploadImageButton;
    }

    public MobileElement getAddImageButton() {
        return addImageButton;
    }

    public MobileElement getBackButton() {
        return backButton;
    }

    public MobileElement getAlertMessageText() {
        return alertMessageText;
    }

    public MobileElement getAllowButton() {
        return allowButton;
    }

    public MobileElement getDenyButton() {
        return denyButton;
    }

    public void clickOnAllowButton(){
        allowButton.click();
    }

    public void clickOnDenyButton(){
        denyButton.click();
    }

    public void clickOKOnRepeatedPermissionAlert(){
        permissionsOKButton.click();
    }

    public String getRepeatedPermissionAlertMessage(){
        return permissionsMessage.getText();
    }

    public void clickAddImageButton(){ addImageButton.click(); }

    public TakePictureScreen clickTakeNewPictureButton(){
        takeNewPictureButton.click();
        return new TakePictureScreen(driver);
    }

    public void clickChooseExistingPictureButton(){
        chooseExistingPictureButton.click();
    }

    public void clickCancelButton(){ cancelButton.click(); }

    public GalleryScreen goToSelectPhotoScreen(){
        allowButton.click();
        return new GalleryScreen(driver);
    }

    public AskQuestionScreen clickBackButton(){
        backButton.click();
        return new AskQuestionScreen(driver);
    }

    public AskQuestionScreen clickConfirmButton(){
        confirmButton.click();
        return new AskQuestionScreen(driver);
    }


}

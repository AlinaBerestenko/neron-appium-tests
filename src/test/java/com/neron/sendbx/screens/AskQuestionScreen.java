package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AskQuestionScreen extends AbstractScreen{

    public AskQuestionScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ImageButton")
    private MobileElement sendQuestionButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ImageButton")
    private MobileElement addImageButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.ImageButton")
    private MobileElement backButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.TextView")
    private MobileElement textCharactersNumber;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.TextView")
    private MobileElement pageNameText;

    @AndroidFindBy(id = "io.themind.neuron.internal:id/askQuestionInput")
    private MobileElement askQuestionInput;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[5]/android.widget.LinearLayout")
    private MobileElement profileButton;

    public MobileElement getProfileButton() {
        return profileButton;
    }

    public MobileElement getAddImageButton() {
        return addImageButton;
    }

    public MobileElement getSendQuestionButton() {
        return sendQuestionButton;
    }

    public MobileElement getBackButton() {
        return backButton;
    }

    public MobileElement getAskQuestionInput() {
        return askQuestionInput;
    }

    public boolean isAskPageLoaded() {
        return pageNameText.isDisplayed() && askQuestionInput.isDisplayed() && addImageButton.isDisplayed() && sendQuestionButton.isDisplayed() && textCharactersNumber.isDisplayed() && backButton.isDisplayed();
    }

    public FeedScreen clickBackButton(){
        backButton.click();
        return new FeedScreen(driver);
    }

    public ProfileScreen clickProfileButton(){
        profileButton.click();
        return new ProfileScreen(driver);
    }

    public ChooseImageSourceScreen clickAddImageButton(){
        addImageButton.click();
        return new ChooseImageSourceScreen(driver);
    }

    public CancelSendingScreen clickSendQuestionButton() {
        sendQuestionButton.click();
        return new CancelSendingScreen(driver);
    }


    public String getQuestionInputText(){
        return askQuestionInput.getText();
    }

    public void typeSomeTextIntoTextField(String questionText){
        askQuestionInput.sendKeys(questionText);
    }

    public String getRemainingNumberCharachters(){
        return textCharactersNumber.getText();
    }


}


